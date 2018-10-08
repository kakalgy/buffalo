package com.yusj.buffalo.jute.compiler;

/**
 * Abstract base class for all the "compound" types such as ustring,
 * buffer, vector, map, and record.
 */
abstract class JCompType extends JType {

    /**
     * Creates a new instance of JCompType
     */
    JCompType(String cType, String cppType, String csharpType, String javaType, String suffix, String wrapper, String csharpWrapper) {
        super(cType, cppType, csharpType, javaType, suffix, wrapper, csharpWrapper, null);
    }

    String genCppGetSet(String fname, int fIdx) {
        String cgetFunc = "  virtual const " + getCppType() + "& get" + fname + "() const {\n";
        cgetFunc += "    return m" + fname + ";\n";
        cgetFunc += "  }\n";
        String getFunc = "  virtual " + getCppType() + "& get" + fname + "() {\n";
        getFunc += "    bs_.set(" + fIdx + ");return m" + fname + ";\n";
        getFunc += "  }\n";
        return cgetFunc + getFunc;
    }

    String genJavaCompareTo(String fname) {
        return "    ret = " + fname + ".compareTo(peer." + fname + ");\n";
    }

    String genJavaEquals(String fname, String peer) {
        return "    ret = " + fname + ".equals(" + peer + ");\n";
    }

    String genJavaHashCode(String fname) {
        return "    ret = " + fname + ".hashCode();\n";
    }

    String genCsharpHashCode(String fname) {
        return "    ret = " + capitalize(fname) + ".GetHashCode();\n";
    }

    String genCsharpEquals(String name, String peer) {
        String[] peerSplit = peer.split("\\.");
        return "    ret = " + capitalize(name) + ".Equals(" + peerSplit[0] + "." + capitalize(peerSplit[1]) + ");\n";
    }

    String genCsharpCompareTo(String name) {
        return "    ret = " + capitalize(name) + ".CompareTo(peer." + capitalize(name) + ");\n";
    }
}

