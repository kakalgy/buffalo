package com.yusj.buffalo.jute.compiler;

/**
 * @Description
 * @Author kakalgy
 * @Date 2018/10/8 22:50
 **/
public class JString extends JCompType {

    /**
     * Creates a new instance of JString
     */
    public JString() {
        super("char *", " ::std::string", "string", "String", "String", "String", "string");
    }

    public String getSignature() {
        return "s";
    }

    public String genJavaReadWrapper(String fname, String tag, boolean decl) {
        String ret = "";
        if (decl) {
            ret = "    String " + fname + ";\n";
        }
        return ret + "        " + fname + "=a_.readString(\"" + tag + "\");\n";
    }

    public String genJavaWriteWrapper(String fname, String tag) {
        return "        a_.writeString(" + fname + ",\"" + tag + "\");\n";
    }

}
