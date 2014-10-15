package com.github.andyshao.lang;

import org.junit.Assert;
import org.junit.Test;

public class PublicKeyTest {

	@Test
	public void test(){
		Assert.assertFalse(PublicKey.OS_NAME.isEmpty());
		Assert.assertFalse(PublicKey.OS_ARC.isEmpty());
		Assert.assertFalse(PublicKey.OS_VERSION.isEmpty());
		Assert.assertFalse(PublicKey.FILE_SEPARATOR.isEmpty());
		Assert.assertFalse(PublicKey.PATH_SEPARATOR.isEmpty());
		Assert.assertFalse(PublicKey.LINE_SEPARATOR.isEmpty());
		Assert.assertFalse(PublicKey.USER_NAME.isEmpty());
		Assert.assertFalse(PublicKey.USER_HOME.isEmpty());
		Assert.assertFalse(PublicKey.USER_DIR.isEmpty());
	}
}
