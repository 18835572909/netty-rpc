package com.rhb.netty.server.core;

import com.rhb.netty.server.core.handler.NeChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 20:42
 */
public class NettyServer {

  private final EventLoopGroup boss = new NioEventLoopGroup();
  private final EventLoopGroup worker = new NioEventLoopGroup();

  private Channel channel;

  public void start(){
    try{
      ServerBootstrap b = new ServerBootstrap();
      b.group(boss,worker)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_KEEPALIVE,true)
          .childHandler(new NeChannelInitializer());

      ChannelFuture channelFuture = b.bind(new InetSocketAddress(10200)).syncUninterruptibly();
      this.channel = channelFuture.channel();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public void destory(){
    if(this.channel!=null){
      channel.close();
    }
    boss.shutdownGracefully();
    worker.shutdownGracefully();
  }

}
