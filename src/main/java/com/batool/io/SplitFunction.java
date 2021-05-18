package com.batool.io;


import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author jun.wan
 *
 * split line string to array
 */
public class SplitFunction implements Function<String, String[]> {

    /**
     * the delimiting regular
     */
    private String sp;

    /**
     * 应该有的列数
     */
    private int colSize = -1;

    public SplitFunction(String sp) {
        this.sp = sp;
    }

    public SplitFunction(String sp, int colSize) {
        this.sp = sp;
        this.colSize = colSize;
    }

    @Override
    public String[] apply(String s) {

        String[] ss = (s.endsWith(sp) ? s.concat(" ") : s).split(sp);
        if (colSize > 0 && ss.length < colSize){
            String[] nLine = new String[colSize];
            Arrays.fill(nLine, "");
            System.arraycopy(ss, 0, nLine, 0, ss.length);
            ss = nLine;
        }
        if (Objects.equals(ss[ss.length - 1], " ")){
            ss[ss.length -1] = "";
        }
        return ss;
    }
}
