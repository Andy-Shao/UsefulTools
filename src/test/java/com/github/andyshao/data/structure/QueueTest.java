package com.github.andyshao.data.structure;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QueueTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                Queue.<String , CycleLinkedElmt<String> , SingleLinked<String>> DEFAULT_QUEUE(SingleLinked
                    .DEFAULT_SINGLE_LINKED())
            } ,
            {
                Queue.<String , CycleLinkedElmt<String> , CycleLinked<String>> DEFAULT_QUEUE(CycleLinked
                    .DEFAULT_CYCLE_LINKED())
            }
        });
    }

    public final String[] data = {
        "Andy" , "Shao"
    };
    public volatile Queue<String> queue;

    public QueueTest(Queue<String> queue) {
        this.queue = queue;
    }

    @Test
    public void testLogic() {
        Assert.assertThat(this.queue.size() , Matchers.is(0));

        for (int i = 0 ; i < this.data.length ; i++)
            this.queue.enqueue(this.data[i]);

        Assert.assertThat(this.queue.size() , Matchers.is(this.data.length));
        Assert.assertThat(this.queue.peek() , Matchers.is(this.data[0]));

        do
            this.queue.dequeue();
        while (this.queue.size() != 0);
    }
}
