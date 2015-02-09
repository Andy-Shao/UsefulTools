package com.github.andyshao.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.github.andyshao.util.ArrayTools;

public class ConvertTest {

    @Test
    public void testBoolean() {
        String trueStr = "true";
        String falseStr = "false";

        Assert.assertTrue(Convert.OB_2_BOOLEAN.apply(trueStr));
        Assert.assertFalse(Convert.OB_2_BOOLEAN.apply(falseStr));
    }

    @Test
    public void testByteArray() {
        String hexString = "416e64792d5368616f";
        byte[] bs = "Andy-Shao".getBytes();

        {
            String hexStr = Convert.BYTES_2_HEX.apply(ArrayTools.pack_unpack(bs , Byte[].class));
            Assert.assertTrue(hexStr.equalsIgnoreCase(hexString));
        }

        {
            byte[] bs2 = ArrayTools.pack_unpack(Convert.HEX_2_BYTES.apply(hexString) , byte[].class);
            Assert.assertArrayEquals(bs , bs2);
        }
    }

    @Test
    public void testConverting() {
        Object in = new BigDecimal("12.34");
        String out = Convert.<Object , String> converting(in , (Object input) -> {
            return input.toString();
        });
        Assert.assertThat(out , Matchers.is("12.34"));
    }

    @Test
    public void testDouble() {
        double d = 1.23456;
        String dStr = "1.23456";
        BigDecimal bigDecimal = new BigDecimal(dStr);

        double myDouble = Convert.OB_2_DOUBLE.apply(bigDecimal);
        Assert.assertThat(myDouble , Matchers.is(d));

        myDouble = Convert.OB_2_DOUBLE.apply(dStr);
        Assert.assertThat(myDouble , Matchers.is(d));
    }

    @Test
    public void testFloat() {
        float f = 1.23F;
        String fStr = "1.23";
        BigDecimal bigDecimal = new BigDecimal(fStr);

        float myFloat = Convert.OB_2_FLOAT.apply(fStr);
        Assert.assertThat(myFloat , Matchers.is(f));

        myFloat = Convert.OB_2_FLOAT.apply(bigDecimal);
        Assert.assertThat(myFloat , Matchers.is(f));
    }

    @Test
    public void testInt() {
        int i = 15_000;
        String iStr = "15000";
        BigInteger bigInteger = new BigInteger(iStr);
        BigDecimal bigDecimal = new BigDecimal(iStr);

        int myInt = Convert.OB_2_INT.apply(iStr);
        Assert.assertThat(myInt , Matchers.is(i));

        myInt = Convert.OB_2_INT.apply(bigDecimal);
        Assert.assertThat(myInt , Matchers.is(i));

        myInt = Convert.OB_2_INT.apply(bigInteger);
        Assert.assertThat(myInt , Matchers.is(i));
    }

    @Test
    public void testLong() {
        long l = 15_000_000L;
        String lStr = "15000000";
        BigInteger bigInteger = new BigInteger(lStr);
        BigDecimal bigDecimal = new BigDecimal(lStr);

        long myLong = Convert.OB_2_LONG.apply(lStr);
        Assert.assertThat(myLong , Matchers.is(l));

        myLong = Convert.OB_2_LONG.apply(bigDecimal);
        Assert.assertThat(myLong , Matchers.is(l));

        myLong = Convert.OB_2_LONG.apply(bigInteger);
        Assert.assertThat(myLong , Matchers.is(l));
    }

    @Test
    public void testShort() {
        short s = 150;
        String sStr = "150";
        BigInteger bigInteger = new BigInteger(sStr);
        BigDecimal bigDecimal = new BigDecimal(sStr);

        short myShort = Convert.OB_2_SHORT.apply(sStr);
        Assert.assertThat(myShort , Matchers.is(s));

        myShort = Convert.OB_2_SHORT.apply(bigInteger);
        Assert.assertThat(myShort , Matchers.is(s));

        myShort = Convert.OB_2_SHORT.apply(bigDecimal);
        Assert.assertThat(myShort , Matchers.is(s));
    }

    @Test
    public void testString() {
        List<Character> list = Arrays.asList('a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g');
        String str = "[a, b, c, d, e, f, g]";

        Assert.assertEquals(str , Convert.OB_2_STR.apply(list));
    }
}
