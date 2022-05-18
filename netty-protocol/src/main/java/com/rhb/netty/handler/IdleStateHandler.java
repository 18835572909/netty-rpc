package com.rhb.netty.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/18 10:29
 */
public interface IdleStateHandler {

  /**
   * 读取超时
   */
  void readIdle(ChannelHandlerContext ctx);

  /**
   * 写入超时
   */
  void writeIdle(ChannelHandlerContext ctx);

  /**
   * 读写超时
   */
  void allIdle(ChannelHandlerContext ctx);

}
