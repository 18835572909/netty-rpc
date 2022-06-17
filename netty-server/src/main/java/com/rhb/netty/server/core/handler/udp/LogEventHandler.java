package com.rhb.netty.server.core.handler.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/6/17 13:44
 */
@Slf4j
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> { //1

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  public void channelRead0(ChannelHandlerContext channelHandlerContext, LogEvent event) throws Exception {

    String builder = String.valueOf(event.getReceived())
        + " ["
        + event.getSource().toString()
        + "] ["
        + event.getLogfile()
        + "] : "
        + event.getMsg();

    log.info(builder);
  }
}