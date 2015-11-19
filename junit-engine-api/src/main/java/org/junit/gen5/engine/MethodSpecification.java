/*
 * Copyright 2015 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.engine;

import java.lang.reflect.Method;

import lombok.Value;

@Value
public class MethodSpecification implements TestPlanSpecificationElement {

	private Class<?> testClass;
	private Method testMethod;

	public MethodSpecification(Class<?> testClass, Method testMethod) {
		this.testClass = testClass;
		this.testMethod = testMethod;
	}

	@Override
	public void accept(TestPlanSpecificationVisitor visitor) {
		visitor.visitMethodSpecification(testClass, testMethod);
	}
}