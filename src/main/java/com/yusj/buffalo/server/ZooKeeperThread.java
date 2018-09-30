package com.yusj.buffalo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the main class for catching all the uncaught exceptions thrown by the threads.
 */
public class ZooKeeperThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperThread.class);

    private UncaughtExceptionHandler uncaughtExceptionalHandler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            handleException(t.getName(), e);
        }
    };

    public ZooKeeperThread(String threadName) {
        super(threadName);
        setUncaughtExceptionHandler(uncaughtExceptionalHandler);
    }

    /**
     * This will be used by the uncaught exception handler and just log a warning message and return.
     *
     * @param thName - thread name
     * @param e      - exception object
     */
    protected void handleException(String thName, Throwable e) {
        LOGGER.warn("Exception occurred from thread {}", thName, e);
    }
}
