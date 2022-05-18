package com.rhb.netty.client.core.handler;

import com.rhb.netty.handler.IdleStateHandlerAdapter;
import com.rhb.netty.protocol.defined.pojo.heartbeat.PingRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端监听写操作
 *
 * @author renhuibo
 * @date 2022/5/18 15:29
 */
@Slf4j
public class ClientWriteIdleStateHandler extends IdleStateHandlerAdapter {

  @Override
  public void writeIdle(ChannelHandlerContext ctx) {
    Channel channel = ctx.channel();
    channel.writeAndFlush(new PingRequest()).addListener((ChannelFutureListener) future -> {
      if(!future.isSuccess()){
        log.error("Server Exception ... ");
      }
    });
  }
}
