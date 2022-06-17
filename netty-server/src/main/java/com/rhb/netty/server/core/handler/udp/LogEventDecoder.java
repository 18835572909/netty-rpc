package com.rhb.netty.server.core.handler.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/6/17 13:43
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

  @Override
  protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
    ByteBuf data = datagramPacket.content();

    int i = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);

    /**
     * duplicate()： 浅拷贝，复制ByteBuf
     * slice()
     * slice(i,j)：浅拷贝，影响原来的ByteBuf
     * copy()： 深拷贝，性能底，新的ByteBuf与之前的没有关系
     * order()：查看字节顺序
     * order(ByteOrder): 修改缓存区字节顺序
     *  - big-endian: 大字节序
     *  - little-endian: 小字节序
     *
     *  $ 数据存储规则 $
     *  小字节序是高位数据存储在内存高位地址，低位数据存储在低位地址
     *  大字节序是高位数据存储在内存低位地址，低位数据存储在高位地址
     *
     * wrap(byte[],i,j) : 将i-j的数据复制到字节数据中
     * flip() : 将limit = position,position=0. 只留下已经读取的字段
     */
    String filename = data.slice(0, i).toString(CharsetUtil.UTF_8);
    String logMsg =  data.slice(i + 1, data.readableBytes()).toString(CharsetUtil.UTF_8);

    LogEvent event = new LogEvent(datagramPacket.recipient(), System.currentTimeMillis(),
        filename,logMsg);
    out.add(event);
  }
}
