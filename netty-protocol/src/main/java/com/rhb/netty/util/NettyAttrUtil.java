package com.rhb.netty.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.apache.commons.lang3.StringUtils;

/**
 * Attr工具类
 *
 * @author renhuibo
 * @date 2022/5/18 10:56
 */
public class NettyAttrUtil {

  private static final AttributeKey<String> ATTR_READ_TIME = AttributeKey.valueOf("readTime");

  private static String getAttribute(Channel channel, AttributeKey<String> key){
    Attribute<String> attr = channel.attr(key);
    return attr.get();
  }

  public static void updateReadTime(Channel channel,Long time){
    channel.attr(ATTR_READ_TIME).set(time.toString());
  }

  public static Long getReadTime(Channel channel){
    String value = getAttribute(channel,ATTR_READ_TIME);
    if(StringUtils.isNotEmpty(value)) return Long.parseLong(value);
    return null;
  }

}
