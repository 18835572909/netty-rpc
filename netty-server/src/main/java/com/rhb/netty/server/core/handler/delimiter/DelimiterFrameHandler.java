package com.rhb.netty.server.core.handler.delimiter;

import com.rhb.netty.server.core.handler.AbstractBaseChannelHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/20 10:58
 */
@Slf4j
public class DelimiterFrameHandler extends AbstractBaseChannelHandler<ByteBuf> {

  @Override
  public void channelRead(Channel channel, ByteBuf info) {
//    log.info("response:{}",byteBuf.toString(CharsetUtil.UTF_8));
    log.info("request:{}",info.toString(CharsetUtil.UTF_8));
  }

}
