package com.yusj.buffalo.jute.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Container for the Hadoop Record DDL.
 * * The main components of the file are filename, list of included files,
 * * and records defined in that file.
 * @Author kakalgy
 * @Date 2018/10/8 23:01
 **/
public class JFile {
    private String mName;
    private List<JFile> mInclFiles;
    private List<JRecord> mRecords;

    /**
     * Creates a new instance of JFile
     *
     * @param name      possibly full pathname to the file
     * @param inclFiles included files (as JFile)
     * @param recList   List of records defined within this file
     */
    public JFile(String name, ArrayList<JFile> inclFiles,
                 ArrayList<JRecord> recList) {
        mName = name;
        mInclFiles = inclFiles;
        mRecords = recList;
    }

    /**
     * Strip the other pathname components and return the basename
     */
    String getName() {
        int idx = mName.lastIndexOf('/');
        return (idx > 0) ? mName.substring(idx) : mName;
    }

    /**
     * Generate record code in given language. Language should be all
     * lowercase.
     *
     * @param outputDirectory
     */
    public void genCode(String language, File outputDirectory)
            throws IOException {
        if ("c++".equals(language)) {
            CppGenerator gen = new CppGenerator(mName, mInclFiles, mRecords,
                    outputDirectory);
            gen.genCode();
        } else if ("java".equals(language)) {
            JavaGenerator gen = new JavaGenerator(mName, mInclFiles, mRecords,
                    outputDirectory);
            gen.genCode();
        } else if ("c".equals(language)) {
            CGenerator gen = new CGenerator(mName, mInclFiles, mRecords,
                    outputDirectory);
            gen.genCode();
        } else if ("csharp".equals(language)) {
            CSharpGenerator gen = new CSharpGenerator(mName, mInclFiles, mRecords,
                    outputDirectory);
            gen.genCode();
        } else {
            throw new IOException("Cannnot recognize language:" + language);
        }
    }
}
