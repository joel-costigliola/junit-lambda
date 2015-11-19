/*
 * Copyright 2015 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.api.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @ExtendWith} is a {@linkplain Repeatable repeatable} annotation
 * that is used to register {@linkplain TestExtension test extensions} for
 * the annotated test class or test method.
 *
 * <h3>Supported Extension APIs</h3>
 * <ul>
 * <li>{@link MethodParameterResolver}</li>
 * </ul>
 *
 * @author Sam Brannen
 * @since 5.0
 * @see TestExtension
 * @see MethodParameterResolver
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Repeatable(Extensions.class)
public @interface ExtendWith {

	Class<? extends TestExtension>[]value();

}
