package com.test.v1.linklist;

public class FooTest {
    public static void main (String [] args) throws Exception{
        Foo  f1 = new Foo();
        f1.setFoo("bar");
        FooHelper.write(f1, "foo.xml");

        Foo f2 = FooHelper.read("foo.xml");
        System.out.println("Foo" + f2.getFoo());
        // the output : Foobar
    }
}