package com.github.andyshao.util;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringToolsTest {
    private volatile String str;

    @Before
    public void before() {
        this.str = "178231785178";
    }

    @Test
    public void testisEmptyOrNull() {
        Assert.assertThat(StringTools.isEmptyOrNull(null) , Matchers.is(true));
        Assert.assertThat(StringTools.isEmptyOrNull("") , Matchers.is(true));
        Assert.assertThat(StringTools.isEmptyOrNull(this.str) , Matchers.is(false));
    }

    @Test
    public void testreplaceAll() {
        Assert.assertThat(StringTools.replaceAll(this.str , "178" , "") , Matchers.is("235"));
    }

    @Test
    public void testreplaceFirst() {
        Assert.assertThat(StringTools.replaceFirst(this.str , "178" , "") , Matchers.is("231785178"));
    }

    @Test
    public void testreplaceLast() {
        Assert.assertThat(StringTools.replaceLast(this.str , "178" , "") , Matchers.is("178231785"));
    }

    @Test
    public void testsplit() {
        Assert.assertArrayEquals(StringTools.split(this.str , "231") , new String[] {
            "178" , "785178"
        });
    }
    
    @Test
    public void testFlipString(){
    	Assert.assertThat(StringTools.flipString("123"), Matchers.is("321"));
    }
}
