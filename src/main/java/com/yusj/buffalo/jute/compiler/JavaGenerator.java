package com.yusj.buffalo.jute.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @Description Java Code generator front-end for Hadoop record I/O.
 * @Author kakalgy
 * @Date 2018/10/8 23:08
 **/
class JavaGenerator {
    private List<JRecord> mRecList;
    private final File outputDirectory;

    /**
     * Creates a new instance of JavaGenerator
     *
     * @param name            possibly full pathname to the file
     * @param incl            included files (as JFile)
     * @param records         List of records defined within this file
     * @param outputDirectory
     */
    JavaGenerator(String name, List<JFile> incl,
                  List<JRecord> records, File outputDirectory) {
        mRecList = records;
        this.outputDirectory = outputDirectory;
    }

    /**
     * Generate Java code for records. This method is only a front-end to
     * JRecord, since one file is generated for each record.
     */
    void genCode() throws IOException {
        for (Iterator<JRecord> i = mRecList.iterator(); i.hasNext(); ) {
            JRecord rec = i.next();
            rec.genJavaCode(outputDirectory);
        }
    }
}
