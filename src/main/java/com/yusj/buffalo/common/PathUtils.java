package com.yusj.buffalo.common;

/**
 * @Description Path related utilities
 * @Author kakalgy
 * @Date 2018/9/27 21:41
 **/
public class PathUtils {

    /**
     * validate the provided znode path string
     *
     * @param path         znode path string
     * @param isSequential if the path is being created with a sequential flag
     * @throws IllegalArgumentException if the path is invalid
     */
    public static void validatePath(String path, boolean isSequential)
            throws IllegalArgumentException {
        validatePath(isSequential ? path + "1" : path);
    }

    /**
     * @Description Validate the provided znode path string
     * * @param path znode path string
     * * @throws IllegalArgumentException if the path is invalid
     **/
    public static void validatePath(String path) throws IllegalArgumentException {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null!");
        }
        if (path.length() == 0) {
            throw new IllegalArgumentException("Path length must be > 0");
        }
        if (path.charAt(0) != '/') {
            throw new IllegalArgumentException("Path must start with / character");
        }
        // done checking - it's the root, path长度为1，且以'/'开头
        if (path.length() == 1) {
            return;
        }

        if (path.charAt(path.length() - 1) == '/') {
            throw new IllegalArgumentException("Path must not end with / character");
        }

        String reason = null;
        char lastc = '/';
        char chars[] = path.toCharArray();
        char c;

        for (int i = 1; i < chars.length; lastc = chars[i], i++) {
            c = chars[i];

            if (c == 0) {
                //c=0为null
                reason = "null character not allowed @";
                break;
            } else if (c == '/' && lastc == '/') {
                reason = "empty node name specified @" + i;
                break;
            } else if (c == '.' && lastc == '.') {
                if (chars[i - 2] == '/' && ((i + 1 == chars.length) || chars[i + 1] == '/')) {
                    reason = "relative paths not allowed @" + i;
                    break;
                }
            } else if (c == '.') {
                if (chars[i - 1] == '/' && ((i + 1 == chars.length) || chars[i + 1] == '/')) {
                    reason = "relative paths not allowed @" + i;
                    break;
                }
            } else if (c > '\u0000' && c <= '\u001f' || c >= '\u007f' && c <= '\u009F' || c >= '\ud800' && c <= '\uf8ff' || c >= '\ufff0' && c <= '\uffff') {
                reason = "invalid character @" + i;
                break;
            }
        }

        if (reason != null) {
            throw new IllegalArgumentException("Invalid path string \"" + path + "\" caused by " + reason);
        }
    }

    /**
     * Convert Windows path to Unix
     *
     * @param path file path
     * @return converted file path
     */
    public static String normalizeFileSystemPath(String path) {
        if (path != null) {
            String osname = java.lang.System.getProperty("os.name");
            if (osname.toLowerCase().contains("windows")) {
                return path.replace('\\', '/');
            }
        }
        return path;
    }

    public static void main(String[] args) {
        char c = 0;
        System.out.println("c = " + c);

        System.out.println("FileSystemPath: " + normalizeFileSystemPath("D:\\file"));

        System.out.println("FileSystemPath: " + normalizeFileSystemPath("/root/path/data"));

        validatePath("/root/path/../data", true);
    }


}
