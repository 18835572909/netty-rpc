package com.rhb.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import java.net.SocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳机制模式实现（适配器）
 *
 * @author renhuibo
 * @date 2022/5/18 10:35
 */
@Slf4j
public abstract class IdleStateHandlerAdapter implements IdleStateHandler{

  @Override
  public void readIdle(ChannelHandlerContext ctx) {
    SocketAddress socketAddress = ctx.channel().remoteAddress();
    log.info("read_idle. remote:{}",socketAddress);
  }

  @Override
  public void writeIdle(ChannelHandlerContext ctx) {
    SocketAddress socketAddress = ctx.channel().remoteAddress();
    log.info("write_idle. remote:{}",socketAddress);
  }

  @Override
  public void allIdle(ChannelHandlerContext ctx) {
    SocketAddress socketAddress = ctx.channel().remoteAddress();
    log.info("all_idle. remote:{}",socketAddress);
  }
}
