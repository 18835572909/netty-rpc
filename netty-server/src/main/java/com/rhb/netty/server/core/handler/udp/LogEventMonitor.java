package com.rhb.netty.server.core.handler.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/6/17 13:46
 */
public class LogEventMonitor {

  private final Bootstrap bootstrap;

  private final EventLoopGroup group;

  public LogEventMonitor(InetSocketAddress address) {
    group = new NioEventLoopGroup();
    bootstrap = new Bootstrap();
    bootstrap.group(group)  //1
        .channel(NioDatagramChannel.class)
        .option(ChannelOption.SO_BROADCAST, true)
        .handler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel channel) throws Exception {
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast(new LogEventDecoder());  //2
            pipeline.addLast(new LogEventHandler());
          }
        }).localAddress(address);

  }

  public Channel bind() {
    return bootstrap.bind().syncUninterruptibly().channel();  //3
  }

  public void stop() {
    group.shutdownGracefully();
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      throw new IllegalArgumentException("Usage: LogEventMonitor <port>");
    }

    LogEventMonitor monitor = new LogEventMonitor(new InetSocketAddress(Integer.parseInt(args[0])));  //4
    try {
      Channel channel = monitor.bind();
      System.out.println("LogEventMonitor running【"+args[0]+"】");

      channel.closeFuture().await();
    } finally {
      monitor.stop();
    }
  }
}
