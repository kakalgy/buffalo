package com.yusj.buffalo;

import org.apache.yetus.audience.InterfaceAudience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description The command line client to ZooKeeper.
 * <p>
 * * <ul>
 * * <li>1.如果标注的是Public，说明被注解的类型对多有工程和应用可用。</li>
 * * <li>2.如果标注的是LimitedPrivate,说明被注解的类型只能用于某些特定的工程或应用，如Common,HDFS,MapReduce,ZooKeeper,HBase等。</li>
 * * <li>3.如果标注的是Private，说明被注解的类型只能用于Hadoop。</li>
 * * </ul>
 * @Author kakalgy
 * @Date 2018/9/25 21:54
 **/
@InterfaceAudience.Public
public class ZooKeeperMain {
    private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperMain.class);

    static final Map<String, String> commandMap = new HashMap<>();
}
