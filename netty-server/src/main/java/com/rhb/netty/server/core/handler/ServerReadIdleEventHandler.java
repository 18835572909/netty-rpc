package com.rhb.netty.server.core.handler;

import com.rhb.netty.handler.IdleStateHandlerAdapter;
import com.rhb.netty.util.NettyAttrUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳
 *
 * @author renhuibo
 * @date 2022/5/18 10:45
 */
@Slf4j
public class ServerReadIdleEventHandler extends IdleStateHandlerAdapter {

  @Override
  public void readIdle(ChannelHandlerContext ctx) {
    long heartBeatTime = 3000;

    Channel channel = ctx.channel();

    Long readTime = NettyAttrUtil.getReadTime(channel);

    long currentTime = System.currentTimeMillis();

    if(readTime!=null &&  currentTime-readTime > heartBeatTime){
      ctx.channel().close();
      log.info("心跳剔除. remote:{}",channel.remoteAddress());
    }

    super.readIdle(ctx);
  }

}
