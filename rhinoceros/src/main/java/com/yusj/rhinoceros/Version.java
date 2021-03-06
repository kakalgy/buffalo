package com.yusj.rhinoceros;

import com.yusj.rhinoceros.generated.version.Info;
import com.yusj.rhinoceros.server.ExitCode;

/**
 * @Description 打印Zookeeper的版本号
 * @Author kakalgy
 * @Date 2018/9/26 21:53
 **/
public class Version implements Info {

    /*
     * Since the SVN to Git port this field doesn't return the revision anymore
     * TODO: remove this method and associated field declaration in VerGen
     * @see {@link #getHashRevision()}
     * @return the default value -1
     */
    @Deprecated
    public static int getRevision() {
        return REVISION;
    }

    public static String getRevisionHash() {
        return REVISION_HASH;
    }

    public static String getBuildDate() {
        return BUILD_DATE;
    }

    public static String getVersion() {
        return MAJOR + "." + MINOR + "." + MICRO
                + (QUALIFIER == null ? "" : "-" + QUALIFIER);
    }

    public static String getVersionRevision() {
        return getVersion() + "-" + getRevisionHash();
    }

    public static String getFullVersion() {

        return getVersionRevision() + ", built on " + getBuildDate();
    }

    public static void printUsage() {
        System.out
                .print("Usage:\tjava -cp ... org.apache.zookeeper.Version "
                        + "[--full | --short | --revision],\n\tPrints --full version "
                        + "info if no arg specified.");
        System.exit(ExitCode.UNEXPECTED_ERROR.getValue());
    }

    /**
     * Prints the current version, revision and build date to the standard out.
     *
     * @param args <ul>
     *             <li> --short - prints a short version string "1.2.3"
     *             <li> --revision - prints a short version string with the SVN
     *             repository revision "1.2.3-94"
     *             <li> --full - prints the revision and the build date
     *             </ul>
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            printUsage();
        }
        if (args.length == 0 || (args.length == 1 && args[0].equals("--full"))) {
            System.out.println(getFullVersion());
            System.exit(ExitCode.EXECUTION_FINISHED.getValue());
        }
        if (args[0].equals("--short"))
            System.out.println(getVersion());
        else if (args[0].equals("--revision"))
            System.out.println(getVersionRevision());
        else
            printUsage();
        System.exit(ExitCode.EXECUTION_FINISHED.getValue());
    }
}
