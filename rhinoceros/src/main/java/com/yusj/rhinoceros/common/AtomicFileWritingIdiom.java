package com.yusj.rhinoceros.common;

import java.io.*;

/**
 * @Description Used to perform an atomic write into a file.
 * *  If there is a failure in the middle of the writing operation,
 * *  the original file (if it exists) is left intact.
 * *  Based on the org.apache.zookeeper.server.quorum.QuorumPeer.writeLongToFile(...) idiom
 * *  using the HDFS AtomicFileOutputStream class.
 * @Author kakalgy
 * @Date 2018/10/9 21:55
 **/
public class AtomicFileWritingIdiom {

    public static interface OutputStreamStatement {

        public void write(OutputStream os) throws IOException;

    }

    public static interface WriterStatement {

        public void write(Writer os) throws IOException;

    }

    public AtomicFileWritingIdiom(File targetFile, OutputStreamStatement osStmt) throws IOException {
        this(targetFile, osStmt, null);
    }

    public AtomicFileWritingIdiom(File targetFile, WriterStatement wStmt) throws IOException {
        this(targetFile, null, wStmt);
    }

    private AtomicFileWritingIdiom(File targetFile, OutputStreamStatement osStmt, WriterStatement wStmt) throws IOException {
        AtomicFileOutputStream out = null;
        boolean error = true;
        try {
            out = new AtomicFileOutputStream(targetFile);
            if (wStmt == null) {
                // execute output stream operation
                osStmt.write(out);
            } else {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                // execute writer operation and flush
                wStmt.write(bw);
                bw.flush();
            }
            out.flush();
            // everything went ok
            error = false;
        } finally {
            // nothing interesting to do if out == null
            if (out != null) {
                if (error) {
                    // worst case here the tmp file/resources(fd) are not cleaned up and the caller will be notified (IOException)
                    out.abort();
                } else {
                    // if the close operation (rename) fails we'll get notified. worst case the tmp file may still exist
                    IOUtils.closeStream(out);
                }
            }
        }
    }
}
