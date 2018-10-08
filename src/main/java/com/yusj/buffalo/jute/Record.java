package com.yusj.buffalo.jute;

import org.apache.yetus.audience.InterfaceAudience;

import java.io.IOException;

/**
 * @Description Interface that is implemented by generated classes.
 * @Author kakalgy
 * @Date 2018/10/8 23:16
 **/
@InterfaceAudience.Public
public interface Record {
    public void serialize(OutputArchive archive, String tag) throws IOException;

    public void deserialize(InputArchive archive, String tag) throws IOException;
}
