package com.yusj.pangolin.jute.compiler;

public class JInt extends JType {

    /**
     * Creates a new instance of JInt
     */
    public JInt() {
        super("int32_t", "int32_t", "int", "int", "Int", "Integer", "int", "toInt");
    }

    public String getSignature() {
        return "i";
    }
}
