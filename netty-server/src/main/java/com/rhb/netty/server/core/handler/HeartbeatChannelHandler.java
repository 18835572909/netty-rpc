package com.rhb.netty.server.core.handler;

import com.rhb.netty.handler.IdleStateHandler;
import com.rhb.netty.protocol.defined.pojo.heartbeat.PingRequest;
import com.rhb.netty.protocol.defined.pojo.heartbeat.PongResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端心跳监听读操作
 *
 * @author renhuibo
 * @date 2022/5/18 15:20
 */
@Slf4j
public class HeartbeatChannelHandler extends AbstractBaseChannelHandler<PingRequest> {

  @Override
  public void channelRead(Channel channel, PingRequest pingRequest) {
    if(pingRequest!=null){
      channel.writeAndFlush(new PongResponse()).addListener((ChannelFutureListener) future ->{
        if(!future.isSuccess()){
          log.error("client error. remote:{}",channel.remoteAddress());
          channel.close();
        }
      });
    }
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

      IdleStateHandler idleStateHandler = new ServerReadIdleEventHandler();

      if(idleStateEvent.state() == IdleState.READER_IDLE){
        idleStateHandler.readIdle(ctx);
      }
    }

    super.userEventTriggered(ctx, evt);
  }

}
