package com.rhb.netty.client.core.handler;

import com.rhb.netty.protocol.defined.codec.NeByteToMessageDecoder;
import com.rhb.netty.protocol.defined.codec.NeMessageToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 15:42
 */
public class NeChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline()
        .addLast(new NeByteToMessageDecoder())
        .addLast(new LoginResponseChannelHandler())
        .addLast(new NeMessageToByteEncoder());
  }

}
