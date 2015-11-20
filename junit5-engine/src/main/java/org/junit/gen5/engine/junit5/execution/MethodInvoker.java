/*
 * Copyright 2015 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.engine.junit5.execution;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import org.junit.gen5.api.extension.*;
import org.junit.gen5.commons.util.Preconditions;
import org.junit.gen5.commons.util.ReflectionUtils;

/**
 * {@code MethodInvoker} encapsulates the invocation of a method, including
 * support for dynamic resolution of method parameters via {@link MethodParameterResolver
 * MethodParameterResolvers}.
 *
 * @author Sam Brannen
 * @author Matthias Merdes
 * @since 5.0
 */
class MethodInvoker {

	private final Method method;

	private final Object target;

	private final Set<MethodParameterResolver> resolvers;

	MethodInvoker(Method method, Object target, Set<MethodParameterResolver> resolvers) {
		Preconditions.notNull(method, "method must not be null");
		Preconditions.notNull(target, "target object must not be null");
		Preconditions.notNull(resolvers, "resolvers must not be null");

		this.method = method;
		this.target = target;
		this.resolvers = resolvers;
	}

	Object invoke(TestExecutionContext testExecutionContext) {
		return ReflectionUtils.invokeMethod(this.method, this.target, resolveParameters(testExecutionContext));
	}

	/**
	 * Resolve the array of parameters for the configured method.
	 *
	 * @param testExecutionContext the current test execution context
	 * @return the array of Objects to be used as parameters in the method
	 * invocation; never {@code null} though potentially empty
	 * @throws ParameterResolutionException
	 */
	private Object[] resolveParameters(TestExecutionContext testExecutionContext) throws ParameterResolutionException {
		// @formatter:off
		return Arrays.stream(this.method.getParameters())
				.map(param -> resolveParameter(param, testExecutionContext))
				.toArray(Object[]::new);
		// @formatter:on
	}

	private Object resolveParameter(Parameter parameter, TestExecutionContext testExecutionContext) {
		try {
			// @formatter:off
			List<MethodParameterResolver> matchingResolvers = this.resolvers.stream()
					.filter(resolver -> resolver.supports(parameter))
					.collect(toList());
			// @formatter:on

			if (matchingResolvers.size() == 0) {
				throw new ParameterResolutionException(
					String.format("No MethodParameterResolver registered for parameter [%s] in method [%s].", parameter,
						this.method.toGenericString()));
			}
			if (matchingResolvers.size() > 1) {
				// @formatter:off
				String resolverNames = matchingResolvers.stream()
						.map(resolver -> resolver.getClass().getName())
						.collect(joining(", "));
				// @formatter:on
				throw new ParameterResolutionException(String.format(
					"Discovered multiple competing MethodParameterResolvers for parameter [%s] in method [%s]: %s",
					parameter, this.method.toGenericString(), resolverNames));
			}
			Object resolvedParameter = matchingResolvers.get(0).resolve(parameter, testExecutionContext);
			this.recordResolvedParameterForReport(parameter, testExecutionContext, resolvedParameter);

			return resolvedParameter;
		}
		catch (Exception ex) {
			if (ex instanceof ParameterResolutionException) {
				throw (ParameterResolutionException) ex;
			}
			throw new ParameterResolutionException(String.format("Failed to resolve parameter [%s] in method [%s]",
				parameter, this.method.toGenericString()), ex);
		}
	}

	private void recordResolvedParameterForReport(Parameter parameter, TestExecutionContext testExecutionContext,
			Object resolvedParameter) {
		TestReportData testReportData = testExecutionContext.getTestReportData();

		if (resolvedParameter == null)
			return;

		testReportData.getInjectedParameterItems().put(parameter.getName(), resolvedParameter.toString());
	}

}
