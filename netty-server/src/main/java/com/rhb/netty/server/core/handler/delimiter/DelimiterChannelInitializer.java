package com.rhb.netty.server.core.handler.delimiter;

import com.rhb.netty.constant.SystemConstant;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 需要注意的是：
 *    一般Netty默认封装提供的都是这对ByteBuf的处理(传输的对象是ByteBuf)
 *
 * 1. 原始ByteBuf解析
 * 2. 添加字符串解析： StringEncoder|StringDecoder
 *
 * DelimiterChannelHandler: 自定义分割符
 *
 * @author renhuibo
 * @date 2022/5/19 16:13
 */
public class DelimiterChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    channel.pipeline()
        .addLast(new DelimiterBasedFrameDecoder(512 * 1024, Unpooled.wrappedBuffer(SystemConstant.SERVER_DELIMITET.getBytes())))
//        .addLast(new StringDecoder())
//        .addLast(new StringEncoder())
        .addLast(new DelimiterFrameHandler());
  }

}
