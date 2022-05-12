package com.rhb.netty.protocol.defined.codec;

import com.rhb.netty.protocol.defined.BasePacket;
import com.rhb.netty.protocol.defined.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * 自定义解码器：
 * QA: 对于bytebuf操作不熟，可能会出现沾包类问题
 *
 * @author renhuibo
 * @date 2022/5/12 18:51
 */
public class NeByteToMessageDecoder extends ByteToMessageDecoder{

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf,
      List<Object> list) throws Exception {
    /**
     * 可读长度没有4位 （int::4）
     */
    if(byteBuf.readableBytes()<4){
      System.out.println("直接丢弃1");
      return;
    }
    /**
     * 标记读取下标
     */
    byteBuf.markReaderIndex();

    int dataLength = byteBuf.readInt();
    if(byteBuf.readableBytes()<dataLength){
      System.out.println("直接丢弃2");
      byteBuf.resetReaderIndex();
      return;
    }

    byte commond = byteBuf.readByte();
    Class<? extends BasePacket> packetType = BasePacket.getType(commond);
    if(packetType==null){
      System.out.println("直接丢弃3");
      byteBuf.resetReaderIndex();
      return;
    }

    byte[] data = new byte[dataLength];
    byteBuf.readBytes(data);
    BasePacket deserialize = SerializationUtil.deserialize(data, packetType);
    list.add(deserialize);
  }

}
