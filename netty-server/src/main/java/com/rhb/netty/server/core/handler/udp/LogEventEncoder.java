package com.rhb.netty.server.core.handler.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/6/17 10:13
 */
public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {
  private final InetSocketAddress remoteAddress;

  public LogEventEncoder(InetSocketAddress remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, LogEvent logEvent, List<Object> out) throws Exception {
    byte[] file = logEvent.getLogfile().getBytes(CharsetUtil.UTF_8);
    byte[] msg = logEvent.getMsg().getBytes(CharsetUtil.UTF_8);
    ByteBuf buf = channelHandlerContext.alloc().buffer(file.length + msg.length + 1);
    /**
     * 日志： log_name : log_content
     */
    buf.writeBytes(file);
    buf.writeByte(LogEvent.SEPARATOR);
    buf.writeBytes(msg);

    out.add(new DatagramPacket(buf, remoteAddress));
  }
}
