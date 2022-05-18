package com.rhb.netty.server.core.handler;

import com.rhb.netty.protocol.defined.codec.NeByteToMessageDecoder;
import com.rhb.netty.protocol.defined.codec.NeMessageToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 20:54
 */
public class NeChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline()
        .addLast(new IdleStateHandler(5,0,0))
        .addLast(new NeByteToMessageDecoder())
        .addLast(new HeartbeatChannelHandler())
        .addLast(new LoginRequestChannelHandler())
        .addLast(new NeMessageToByteEncoder());
  }

}
