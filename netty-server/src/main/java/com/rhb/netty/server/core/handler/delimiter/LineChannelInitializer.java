package com.rhb.netty.server.core.handler.delimiter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * LineBaseChannelHandler : \n 或者 \r\n 为分割符  (同DelimiterBaseChannelHandler使用，书中提示最多比DelimiterBaseChannelHandler快3倍)
 *
 * @author renhuibo
 * @date 2022/5/20 11:29
 */
public class LineChannelInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(Channel channel) throws Exception {
    channel.pipeline()
        .addLast(new LineBasedFrameDecoder(512 * 1024))
        .addLast(new DelimiterFrameHandler());
  }

}
