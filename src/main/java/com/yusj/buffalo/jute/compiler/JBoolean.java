package com.yusj.buffalo.jute.compiler;

public class JBoolean extends JType {

    /**
     * Creates a new instance of JBoolean
     */
    public JBoolean() {
        super("int32_t", "bool", "bool", "boolean", "Bool", "Boolean", "bool", "toBoolean");
    }

    public String getSignature() {
        return "z";
    }

    public String genJavaCompareTo(String fname) {
        return "    ret = (" + fname + " == peer." + fname + ")? 0 : (" + fname + "?1:-1);\n";
    }

    public String genJavaHashCode(String fname) {
        return "     ret = (" + fname + ")?0:1;\n";
    }

    String genCsharpHashCode(String fname) {
        return "     ret = (" + capitalize(fname) + ")?0:1;\n";
    }

    String genCsharpCompareTo(String name) {
        return "    ret = (" + capitalize(name) + " == peer." + capitalize(name) + ")? 0 : (" + capitalize(name) + "?1:-1);\n";
    }
}
