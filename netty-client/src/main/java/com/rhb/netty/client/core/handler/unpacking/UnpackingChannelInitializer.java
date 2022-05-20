package com.rhb.netty.client.core.handler.unpacking;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 客户端不处理响应，这里就不用添加handler处理（Server\Client相同）
 *
 * @author renhuibo
 * @date 2022/5/20 14:13
 */
public class UnpackingChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {

  }
}
