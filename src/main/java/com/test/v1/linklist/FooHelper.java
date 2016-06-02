package com.test.v1.linklist;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

public class FooHelper {
    public static void write(Foo f, String filename) throws Exception{
        XMLEncoder encoder =
           new XMLEncoder(
              new BufferedOutputStream(
                new FileOutputStream(filename)));
        encoder.writeObject(f);
        encoder.close();
    }

    public static Foo read(String filename) throws Exception {
        XMLDecoder decoder =
            new XMLDecoder(new BufferedInputStream(
                new FileInputStream(filename)));
        Foo o = (Foo)decoder.readObject();
        decoder.close();
        return o;
    }
}