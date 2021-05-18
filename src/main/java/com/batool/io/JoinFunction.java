package com.batool.io;

import java.util.function.Function;

/**
 * @author jun.wan
 *
 */
public class JoinFunction implements Function<String[], String> {

    /**
     * the delimiting regular
     */
    private String sp;

    public JoinFunction(String sp) {
        this.sp = sp;
    }

    @Override
    public String apply(String[] strings) {
        return String.join(sp, strings);
    }
}
