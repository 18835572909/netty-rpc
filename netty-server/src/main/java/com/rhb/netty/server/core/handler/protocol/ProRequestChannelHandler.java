package com.rhb.netty.server.core.handler.protocol;

import com.rhb.netty.protocol.protocolbuffer.RequestProto;
import com.rhb.netty.protocol.protocolbuffer.RequestProto.RequestProtocol;
import com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol;
import com.rhb.netty.server.core.handler.AbstractBaseChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import java.net.SocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/19 10:34
 */
@Slf4j
public class ProRequestChannelHandler extends AbstractBaseChannelHandler<RequestProto.RequestProtocol> {

  @Override
  public void channelRead(Channel channel, RequestProtocol protocol) {
    long requestId = protocol.getRequestId();
    int type = protocol.getType();
    String msg = protocol.getMsg();

    log.info("{} : {}#{}#,{}",channel.remoteAddress().toString().substring(1),requestId,type,msg);
    
    channel.writeAndFlush(sampleProtocolRespMsg()).addListener((ChannelFutureListener)future->{
      if(!future.isSuccess()){
        SocketAddress socketAddress = future.channel().remoteAddress();
        log.info("响应失败：{}",socketAddress);
        future.channel().close();
      }
    });

    log.info("已经发送响应...");
  }

  /**
   * 设置响应样例
   * @return
   */
  public static ResponseProtocol sampleProtocolRespMsg(){
    return ResponseProtocol.newBuilder()
        .setResponseId(System.currentTimeMillis())
        .setMsg("收到")
        .setType(1)
        .build();
  }

}
