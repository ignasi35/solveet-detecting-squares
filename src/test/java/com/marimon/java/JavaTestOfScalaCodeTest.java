package com.marimon.java;

import junit.framework.Assert;

import org.junit.Test;

import com.marimon.javatest.ScalaCode;

public class JavaTestOfScalaCodeTest {

	@Test
	public void testScalaCode() {
		Assert.assertEquals("hello", new ScalaCode().doStuff("foo")) ;
	}

}
