package com.rhb.netty.client.core.handler.custom;

import com.rhb.netty.client.core.handler.AbstractBaseChannelHandler;
import com.rhb.netty.handler.IdleStateHandler;
import com.rhb.netty.protocol.defined.pojo.heartbeat.PongResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/18 15:20
 */
@Slf4j
public class HeartbeatChannelHandler extends AbstractBaseChannelHandler<PongResponse> {

  @Override
  public void channelRead(Channel channel, PongResponse pongRequest) {
    log.info("pong");
//    channel.pipeline().remove(this);
  }

  /**
   * 事件触发器，常用于心跳检测
   * @param ctx
   * @param evt
   * @throws Exception
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt instanceof IdleStateEvent){
      IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

      IdleStateHandler idleStateHandler = new ClientWriteIdleStateHandler();

      if(idleStateEvent.state() == IdleState.WRITER_IDLE){
        idleStateHandler.writeIdle(ctx);
      }
    }

    super.userEventTriggered(ctx, evt);
  }

}
