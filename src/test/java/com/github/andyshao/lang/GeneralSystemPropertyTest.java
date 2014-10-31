package com.github.andyshao.lang;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class GeneralSystemPropertyTest {

	@Test
	public void test(){
		Assert.assertThat(GeneralSystemProperty.JAVA_HOME.toString(), Matchers.is(System.getProperty("java.home")));
	}
}
