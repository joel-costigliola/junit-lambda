/*
 * Copyright 2015 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @Test} is used to signal that the annotated method is a
 * <em>test</em> method.
 *
 * <p>{@code @Test} methods must not be {@code private} or {@code static}.
 *
 * <p>{@code @Test} methods may optionally declare parameters to be
 * resolved by {@link org.junit.gen5.api.extension.MethodParameterResolver
 * MethodParameterResolvers}.
 *
 * @author Johannes Link
 * @author Marc Philipp
 * @author Matthias Merdes
 * @author Sam Brannen
 * @author Stefan Bechtold
 * @since 5.0
 * @see TestInstance
 * @see Name
 * @see BeforeEach
 * @see AfterEach
 * @see BeforeAll
 * @see AfterAll
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Test {
}
