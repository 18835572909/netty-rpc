package com.rhb.netty.client.core.handler;

import com.rhb.netty.protocol.defined.codec.NeByteToMessageDecoder;
import com.rhb.netty.protocol.defined.codec.NeMessageToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 客户端
 *
 * @author renhuibo
 * @date 2022/5/13 15:42
 */
public class NeChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline()
        .addLast(new IdleStateHandler(0,10,0))
        .addLast(new NeByteToMessageDecoder())
        .addLast(new HeartbeatChannelHandler())
        .addLast(new LoginResponseChannelHandler())
        .addLast(new NeMessageToByteEncoder());
  }

}
