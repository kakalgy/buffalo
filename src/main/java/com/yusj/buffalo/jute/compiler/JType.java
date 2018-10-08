package com.yusj.buffalo.jute.compiler;

public abstract class JType {
    private String mCName;
    private String mCppName;
    private String mCsharpName;
    /**
     * Java的变量类型 eg:int
     */
    private String mJavaName;
    /**
     * 方法名后缀 eg:Int
     */
    protected String mMethodSuffix;
    /**
     * 封装类名 eg:Integer
     */
    private String mWrapper;
    private String mSharpWrapper;
    /**
     * 解封装方法名 eg:toInt
     */
    private String mUnwrapMethod;

    /**
     * Creates a new instance of JType
     */
    JType(String cname, String cppname, String csharpName, String javaname, String suffix, String wrapper, String csharpWrapper, String unwrap) {
        mCName = cname;
        mCppName = cppname;
        mCsharpName = "Id".equals(csharpName) ? "ZKId" : csharpName;
        mJavaName = javaname;
        mMethodSuffix = suffix;
        mWrapper = wrapper;
        mSharpWrapper = csharpWrapper;
        mUnwrapMethod = unwrap;
    }

    abstract String getSignature();

    String genCppDecl(String fname) {
        return "  " + mCppName + " m" + fname + ";\n";
    }

    String genCDecl(String name) {
        return "    " + mCName + " " + name + ";\n";
    }

    public String genCsharpDecl(String name) {
        return "  private " + mCsharpName + " " + name + ";\n";
    }

    /**
     * Java的变量声明语句,eg:private mJavaName fname;
     *
     * @param fname 变量名称
     * @return
     */
    String genJavaDecl(String fname) {
        return "  private " + mJavaName + " " + fname + ";\n";
    }

    /**
     * Java构造函数中的变量参数
     *
     * @param fname 变量名
     * @return
     */
    String genJavaConstructorParam(String fname) {
        return "        " + mJavaName + " " + fname;
    }

    String genCppGetSet(String fname, int fIdx) {
        String getFunc = "  virtual " + mCppName + " get" + fname + "() const {\n";
        getFunc += "    return m" + fname + ";\n";
        getFunc += "  }\n";
        String setFunc = "  virtual void set" + fname + "(" + mCppName + " m_) {\n";
        setFunc += "    m" + fname + "=m_; bs_.set(" + fIdx + ");\n";
        setFunc += "  }\n";
        return getFunc + setFunc;
    }

    String genCsharpGetSet(String fname, int fIdx) {
        String getFunc = "  public " + getCsharpType() + " " + capitalize(fname) + " { get; set; } ";
        return getFunc;
    }

    /**
     * 将s的首字母大写，eg: name --> Name
     *
     * @param s
     * @return
     */
    static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 生成get和set方法
     *
     * @param fname 变量名
     * @param fIdx  在java中无作用
     * @return
     */
    String genJavaGetSet(String fname, int fIdx) {
        String getFunc = "  public " + mJavaName + " get" + capitalize(fname) + "() {\n";
        getFunc += "    return " + fname + ";\n";
        getFunc += "  }\n";
        String setFunc = "  public void set" + capitalize(fname) + "(" + mJavaName + " m_) {\n";
        setFunc += "    " + fname + "=m_;\n";
        setFunc += "  }\n";
        return getFunc + setFunc;
    }

    String getCType() {
        return mCName;
    }

    String getCppType() {
        return mCppName;
    }

    String getCsharpType() {
        return mCsharpName;
    }

    /**
     * 返回Java类型
     *
     * @return
     */
    String getJavaType() {
        return mJavaName;
    }

    /**
     * 返回java封装类型
     *
     * @return
     */
    String getJavaWrapperType() {
        return mWrapper;
    }

    String getCsharpWrapperType() {
        return mSharpWrapper;
    }

    /**
     * 获得方法名后缀
     *
     * @return
     */
    String getMethodSuffix() {
        return mMethodSuffix;
    }

    /**
     * 返回 eg：    a_.writemMethodSuffix(fname,"tag");
     *
     * @param fname
     * @param tag
     * @return
     */
    String genJavaWriteMethod(String fname, String tag) {
        return "    a_.write" + mMethodSuffix + "(" + fname + ",\"" + tag + "\");\n";
    }


    /**
     * 返回，eg：fname=a_.readmMethodSuffix("tag");
     *
     * @param fname
     * @param tag
     * @return
     */
    String genJavaReadMethod(String fname, String tag) {
        return "    " + fname + "=a_.read" + mMethodSuffix + "(\"" + tag + "\");\n";
    }

    /**
     * 返回封装类的格式，如果decl为true
     *
     * @param fname
     * @param tag
     * @param decl
     * @return
     */
    String genJavaReadWrapper(String fname, String tag, boolean decl) {
        String ret = "";
        if (decl) {
            ret = "    " + mWrapper + " " + fname + ";\n";
        }
        return ret + "    " + fname + "=new " + mWrapper + "(a_.read" + mMethodSuffix + "(\"" + tag + "\"));\n";
    }

    /**
     * @param fname
     * @param tag
     * @return
     */
    String genJavaWriteWrapper(String fname, String tag) {
        return "        a_.write" + mMethodSuffix + "(" + fname + "." + mUnwrapMethod + "(),\"" + tag + "\");\n";
    }

    /**
     * eg: ret = (fname == peer.fname)? 0 :((fname<peer.fname)?-1:1);
     *
     * @param fname
     * @return
     */
    String genJavaCompareTo(String fname) {
        return "    ret = (" + fname + " == peer." + fname + ")? 0 :((" + fname + "<peer." + fname + ")?-1:1);\n";
    }

//    static String genJavaCompareTo(String fname) {
//        return "    ret = (" + fname + " == peer." + fname + ")? 0 :((" + fname + "<peer." + fname + ")?-1:1);\n";
//    }

    /**
     * eg: ret = ( fname == peer);
     *
     * @param fname
     * @param peer
     * @return
     */
    String genJavaEquals(String fname, String peer) {
        return "    ret = (" + fname + "==" + peer + ");\n";
    }

    /**
     * eg: ret = (int) fname;
     *
     * @param fname
     * @return
     */
    String genJavaHashCode(String fname) {
        return "    ret = (int)" + fname + ";\n";
    }

    /**
     * 构造函数中的设置 eg：this.name = name;
     *
     * @param fname
     * @param name
     * @return
     */
    String genJavaConstructorSet(String fname, String name) {
        return "    this." + fname + "=" + name + ";\n";
    }

    String genCsharpWriteMethod(String fname, String tag) {
        return "    a_.Write" + mMethodSuffix + "(" + capitalize(fname) + ",\"" + tag + "\");\n";
    }

    String genCsharpReadMethod(String fname, String tag) {
        return "    " + capitalize(fname) + "=a_.Read" + mMethodSuffix + "(\"" + tag + "\");\n";
    }

    String genCsharpReadWrapper(String fname, String tag, boolean decl) {
        String ret = "";
        if (decl) {
            ret = "    " + mWrapper + " " + fname + ";\n";
        }
        return ret + "    " + fname + "=a_.Read" + mMethodSuffix + "(\"" + tag + "\");\n";
    }

    String genCsharpWriteWrapper(String fname, String tag) {
        if (mUnwrapMethod == null) return "        a_.Write" + mMethodSuffix + "(" + fname + "," + tag + ");\n";
        return "        a_.Write" + mMethodSuffix + "(" + fname + "." + mUnwrapMethod + "(),\"" + tag + "\");\n";
    }

    String genCsharpCompareTo(String name) {
        return "    ret = (" + capitalize(name) + " == peer." + capitalize(name) + ")? 0 :((" + capitalize(name) + "<peer." + capitalize(name) + ")?-1:1);\n";
    }

    String genCsharpEquals(String name, String peer) {
        String[] peerSplit = peer.split("\\.");
        return "    ret = (" + capitalize(name) + "==" + peerSplit[0] + "." + capitalize(peerSplit[1]) + ");\n";
    }

    String genCsharpHashCode(String fname) {
        return "    ret = (int)" + capitalize(fname) + ";\n";
    }

    String genCsharpConstructorSet(String mName, String fname) {
        return capitalize(fname) + "=" + mName + ";\n";
    }

    public String genCsharpConstructorParam(String fname) {
        return "  " + mCsharpName + " " + fname + "\n";
    }


}
