package com.batool.io;

/**
 * @author jun.wan
 * Text IO, read and write
 */
public class TextIO {

    public static TextReader read(){
        return new TextReader();
    }

    public static TextWriter write(){
        return new TextWriter();
    }

}
