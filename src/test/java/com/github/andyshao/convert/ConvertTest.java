package com.github.andyshao.convert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.github.andyshao.util.ArrayTools;

public class ConvertTest {

	@Test
	public void testByteArray(){
		String hexString = "416e64792d5368616f";
		byte[] bs = "Andy-Shao".getBytes();
		
		{
			String hexStr = Convert.BYTES_2_HEX.convert(ArrayTools.pack_unpack(bs, Byte[].class));
			Assert.assertTrue(hexStr.equalsIgnoreCase(hexString));
		}

		{
			byte[] bs2 = ArrayTools.pack_unpack(Convert.HEX_2_BYTES.convert(hexString), byte[].class);
			Assert.assertArrayEquals(bs, bs2);
		}
	}
	
	@Test
	public void testBoolean(){
		String trueStr = "true";
		String falseStr = "false";
		
		Assert.assertTrue(Convert.OB_2_BOOLEAN.convert(trueStr));
		Assert.assertFalse(Convert.OB_2_BOOLEAN.convert(falseStr));
	}
	
	@Test
	public void testString(){
		List<Character> list = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g');
		String str = "[a, b, c, d, e, f, g]";
		
		Assert.assertEquals(str, Convert.OB_2_STR.convert(list));
	}

	@Test
	public void testFloat(){
		float f = 1.23F;
		String fStr = "1.23";
		
		float myFloat = Convert.OB_2_FLOAT.convert(fStr);
		Assert.assertThat(myFloat, Matchers.is(f));
	}
	
	@Test
	public void testInt(){
		int i = 15_000;
		String iStr = "15000";
		
		int myInt = Convert.OB_2_INT.convert(iStr);
		Assert.assertThat(myInt, Matchers.is(i));
	}
	
	@Test
	public void testLong(){
		long l = 15_000_000L;
		String lStr = "15000000";
		
		long myLong = Convert.OB_2_LONG.convert(lStr);
		Assert.assertThat(myLong, Matchers.is(l));
	}
	
	@Test
	public void testShort(){
		short s = 150;
		String sStr = "150";
		
		short myShort = Convert.OB_2_SHORT.convert(sStr);
		Assert.assertThat(myShort, Matchers.is(s));
	}
	
	@Test
	public void testDouble(){
		double d = 1.23456;
		String dStr = "1.23456";
		BigDecimal bigDecimal = new BigDecimal(dStr);
		
		double myDouble = Convert.OB_2_DOUBLE.convert(bigDecimal);
		Assert.assertThat(myDouble, Matchers.is(d));
		
		myDouble = Convert.OB_2_DOUBLE.convert(dStr);
		Assert.assertThat(myDouble, Matchers.is(d));
	}
}
