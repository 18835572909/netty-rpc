package com.rhb.netty.client.core.handler.protocol;

import com.rhb.netty.client.core.handler.AbstractBaseChannelHandler;
import com.rhb.netty.protocol.protocolbuffer.ResponseProto;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/19 10:34
 */
@Slf4j
public class ProResponseChannelHandler extends AbstractBaseChannelHandler<ResponseProto.ResponseProtocol> {

  @Override
  public void channelRead(Channel channel, ResponseProto.ResponseProtocol response) {
    long requestId = response.getResponseId();
    int type = response.getType();
    String msg = response.getMsg();

    log.info("{} : {}#{}#,{}",channel.remoteAddress(),requestId,type,msg);
  }

}
