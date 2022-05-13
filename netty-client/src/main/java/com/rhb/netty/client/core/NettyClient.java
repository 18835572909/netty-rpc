package com.rhb.netty.client.core;

import com.rhb.netty.client.core.handler.NeChannelInitializer;
import com.rhb.netty.constant.SystemConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 14:53
 */
public class NettyClient {

  private EventLoopGroup worker = new NioEventLoopGroup();

  private Channel channel;

  public Channel start(){
    Bootstrap b = new Bootstrap();
    b.group(worker)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.AUTO_READ,true)
        .handler(new NeChannelInitializer());

    ChannelFuture channelFuture = b.connect(SystemConstant.SERVER_HOST, SystemConstant.SERVER_PORT)
        .syncUninterruptibly();

    this.channel = channelFuture.channel();
    return channel;
  }

  public void destory(){
    if(channel!=null){
      channel.close();
      worker.shutdownGracefully();
    }
  }

}
