package com.github.andyshao.util;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.util.StringTools;

public class StringToolsTest {
	private volatile String str;
	
	@Before
	public void before(){
		this.str = "178231785178";
	}

    @Test
    public void testreplaceAll() {
    	Assert.assertThat(StringTools.replaceAll(str , "178" , ""), is("235"));
    }

    @Test
    public void testreplaceFirst() {
    	Assert.assertThat(StringTools.replaceFirst(str , "178" , ""), is("231785178"));
    }
    
    @Test
    public void testreplaceLast() {
    	Assert.assertThat(StringTools.replaceLast(str , "178" , ""), is("178231785"));
    }
    
    @Test
    public void testsplit(){
    	Assert.assertArrayEquals(StringTools.split(this.str, "231"), new String[]{"178", "785178"});
    }
    
    @Test
    public void testisEmptyOrNull(){
    	Assert.assertThat(StringTools.isEmptyOrNull(null), is(true));
    	Assert.assertThat(StringTools.isEmptyOrNull(""), is(true));
    	Assert.assertThat(StringTools.isEmptyOrNull(this.str), is(false));
    }
}
