package com.rhb.netty.protocol.defined.codec;

import com.rhb.netty.protocol.defined.BasePacket;
import com.rhb.netty.protocol.defined.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义编码器
 *
 * @author renhuibo
 * @date 2022/5/12 18:17
 */
public class NeMessageToByteEncoder extends MessageToByteEncoder<BasePacket> {


  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, BasePacket basePacket,
      ByteBuf byteBuf) throws Exception {
    byte[] serialize = SerializationUtil.serialize(basePacket);

    byteBuf.writeInt(serialize.length);
    byteBuf.writeByte(basePacket.getCommond());
    byteBuf.writeBytes(serialize);
  }
}
