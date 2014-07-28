package com.github.andyshao.util;

import static org.junit.Assert.*;
import org.junit.Test;

import com.github.andyshao.util.StringTools;

public class StringToolsTest {

    @Test
    public void testreplaceAll() {
        String str = "178231785178";
        assertEquals("235" , StringTools.replaceAll(str , "178" , ""));
    }

    @Test
    public void testreplaceFirst() {
        String str = "178231785178";
        assertEquals("231785178" , StringTools.replaceFirst(str , "178" , ""));
    }
    
    @Test
    public void testreplaceLast() {
        String str = "178231785178";
        assertEquals("178231785" , StringTools.replaceLast(str , "178" , ""));
    }
}
