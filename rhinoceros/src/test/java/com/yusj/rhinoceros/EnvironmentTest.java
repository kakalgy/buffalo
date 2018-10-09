package com.yusj.rhinoceros;

import org.junit.Test;

public class EnvironmentTest {

    @Test
    public void testStaticClass() {
        Environment.Entry e1 = new Environment.Entry("lgy", "male");
        Environment.Entry e2 = new Environment.Entry("yusj", "female");

        System.out.println(e1);
        System.out.println(e2);

    }
}
