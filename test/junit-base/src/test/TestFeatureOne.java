
import junit.framework.Assert;

import org.junit.Test;

public class TestFeatureOne {
	@Test
	public void testFirstFeature()
	{
		Assert.assertTrue(true);
	}
}

package com.howtodoinjava.junit;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

public class TestFeatureTwo {
	@Test
	public void testSecondFeature()
	{
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	public void testSecondFeatureIngored()
	{
		Assert.assertTrue(true);
	}
}