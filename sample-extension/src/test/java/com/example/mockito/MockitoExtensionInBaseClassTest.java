/*
 * Copyright 2015 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package com.example.mockito;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestName;
import org.junit.gen5.api.extension.ExtendWith;
import org.junit.gen5.junit4runner.JUnit5;
import org.junit.runner.RunWith;

/**
 * @author Johannes Link
 * @author Sam Brannen
 * @since 5.0
 */
@RunWith(JUnit5.class)
@ExtendWith(MockitoExtension.class)
//public to be picked up by IDE JUnit4 test runner
public class MockitoExtensionInBaseClassTest {

	@BeforeEach
	void initialize(@InjectMock MyType myType, @TestName String testName) {
		when(myType.getName()).thenReturn(testName);
	}

	@Test
	void simpleTestWithInjectedMock(@InjectMock MyType myType) {
		assertEquals("simpleTestWithInjectedMock", myType.getName());
	}

	@Test
	void secondTestWithInjectedMock(@InjectMock MyType myType) {
		assertEquals("secondTestWithInjectedMock", myType.getName());
	}

}
