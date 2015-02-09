package com.github.andyshao.data.structure;

import org.junit.Test;

public class StackTest {
    @Test
    public void testBuild(){
        Stack<String> stack = Stack.<String, SingleLinked<String>>DEFAULT_STACK(SingleLinked.<String>DEFAULT_SINGLE_LINKED());
        stack.clean();
        stack.size();
        
        
        stack = Stack.<String, CycleLinked<String>>DEFAULT_STACK(CycleLinked.<String>DEFAULT_CYCLE_LINKED());
        stack.clean();
        stack.size();
    }
}
