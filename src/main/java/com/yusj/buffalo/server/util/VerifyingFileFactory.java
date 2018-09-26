package com.yusj.buffalo.server.util;

import org.slf4j.Logger;

import java.io.File;

/**
 * @Description 能够完成两种检测：
 * * 1.判断文件是否为相对路径
 * * 2.判断文件是否存在
 * *
 * @Author kakalgy
 * @Date 2018/9/26 22:12
 **/
public final class VerifyingFileFactory {
    /**
     * 判断文件是否为相对路径，若不是则打印warn
     */
    private final boolean warnForRelativePath;
    /**
     * 判断文件是否存在，若不存在则抛出异常
     */
    private final boolean failForNonExistingPath;

    private final Logger LOGGER;

    /**
     * @return
     * @Description 创建一个VerifyingFileFactory，其中Builder为静态内部类
     * @Param [builder]
     **/
    public VerifyingFileFactory(Builder builder) {
        warnForRelativePath = builder.warnForRelativePathOption;
        failForNonExistingPath = builder.failForNonExistingPathOption;
        LOGGER = builder.LOGGER;
        assert (LOGGER != null);
    }

    /**
     * @return java.io.File
     * @Description 通过路径来创建一个新文件，并确认文件是否存在且是否为相对路径
     * @Param [path]
     **/
    public File create(String path) {
        File file = new File(path);
        return validate(file);
    }

    /**
     * @return java.io.File
     * @Description 确认文件是否存在且是否为相对路径
     * @Param [file]
     **/
    public File validate(File file) {
        if (warnForRelativePath) doWarnForRelativePath(file);
        if (failForNonExistingPath) doFailForNonExistingPath(file);
        return file;
    }

    /**
     * @return void
     * @Description 判断文件是否存在，若不存在则抛出异常
     * @Param [file]
     **/
    private void doFailForNonExistingPath(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file.toString()
                    + " file is missing");
        }
    }

    /**
     * @return void
     * @Description 判断文件是否为相对路径，若不是则打印warn
     * @Param [file]
     **/
    private void doWarnForRelativePath(File file) {
        if (file.isAbsolute()) return;
        if (file.getPath().substring(0, 2).equals("." + File.separator)) return;
        log.warn(file.getPath() + " is relative. Prepend ." + File.separator + " to indicate that you're sure!");
    }

    public static class Builder {
        private boolean warnForRelativePathOption = false;
        private boolean failForNonExistingPathOption = false;

        private final Logger LOGGER;

        public Builder(Logger LOGGER) {
            this.LOGGER = LOGGER;
        }

        public Builder warnForRelativePath() {
            warnForRelativePathOption = true;
            return this;
        }

        public Builder failForNonExistingPath() {
            failForNonExistingPathOption = true;
            return this;
        }

        public VerifyingFileFactory build() {
            return new VerifyingFileFactory(this);
        }
    }
}
