package com.rhb.netty.server.core.handler.length;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * FixLengthChannelHandler: 定长帧
 * LengthFieldChannelHandler: 头部+数据
 *
 * @author renhuibo
 * @date 2022/5/20 11:29
 */
public class LengChannelInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(Channel channel) throws Exception {

  }
}
