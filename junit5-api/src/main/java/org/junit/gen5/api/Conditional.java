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
 * {@code @Conditional} is used to register one or more {@link #value
 * Conditions} to be evaluated for <em>conditional test execution</em>.
 *
 * @author Sam Brannen
 * @since 5.0
 * @see Condition
 * @see Disabled
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Conditional {

	/**
	 * One or more {@link #Condition Conditions} to be evaluated for
	 * <em>conditional test execution</em>.
	 */
	Class<? extends Condition>[]value();

}
