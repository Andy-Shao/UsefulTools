package com.github.andyshao.convert;

import java.util.Arrays;
import java.util.List;

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
}
