package com.rhb.netty.server.core.handler.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * NettyServer
 *
 * @author renhuibo
 * @date 2022/5/25 11:45
 */
public class ChatServer {

  private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

  private final EventLoopGroup group = new NioEventLoopGroup();

  private Channel channel;

  public ChannelFuture start(){
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(group)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChatServerInitializer(channelGroup));

    ChannelFuture future = bootstrap.bind(10001);
    future.syncUninterruptibly();
    channel = future.channel();
    return future;
  }

  public void destory(){
    if (channel != null){
      channel.close();
    }
    channelGroup.close();
    group.shutdownGracefully();
  }

  public static void main(String[] args) {
    final ChatServer chatServer = new ChatServer();
    ChannelFuture future = chatServer.start();

    Runtime.getRuntime().addShutdownHook(new Thread(){
      @Override
      public void run(){
        chatServer.destory();
      }
    });

    future.channel().closeFuture().syncUninterruptibly();
  }

}
