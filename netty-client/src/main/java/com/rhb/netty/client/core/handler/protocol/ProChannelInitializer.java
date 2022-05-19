package com.rhb.netty.client.core.handler.protocol;

import com.rhb.netty.protocol.protocolbuffer.RequestProto;
import com.rhb.netty.protocol.protocolbuffer.ResponseProto;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/19 10:28
 */
public class ProChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline()
        .addLast(new ProtobufVarint32FrameDecoder())
        .addLast(new ProtobufDecoder(ResponseProto.ResponseProtocol.getDefaultInstance()))
        .addLast(new ProtobufVarint32LengthFieldPrepender())
        .addLast(new ProtobufEncoder())
        .addLast(new ProResponseChannelHandler());
  }

}
