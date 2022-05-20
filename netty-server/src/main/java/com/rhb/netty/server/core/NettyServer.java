package com.rhb.netty.server.core;

import com.rhb.netty.constant.SystemConstant;
import com.rhb.netty.server.core.handler.delimiter.DelimiterChannelInitializer;
import com.rhb.netty.server.core.handler.http.HttpChannelInitializer;
import com.rhb.netty.server.core.handler.http.HttpFullHttpRequestHandler;
import com.rhb.netty.server.core.handler.protocol.ProChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * NettyServer
 *
 * @author renhuibo
 * @date 2022/5/12 20:42
 */
@Slf4j
public class NettyServer {

  private final EventLoopGroup boss = new NioEventLoopGroup();
  private final EventLoopGroup worker = new NioEventLoopGroup();

  private Channel channel;

  public Channel start(){
    try{
      ServerBootstrap b = new ServerBootstrap();
      b.group(boss,worker)
          .channel(NioServerSocketChannel.class)
          /**
           *  SO_BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
           *  用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
           */
          .option(ChannelOption.SO_BACKLOG,128)
          /**
           * 保持长链接
           */
          .option(ChannelOption.SO_KEEPALIVE,true)
//          .childHandler(new NeChannelInitializer());
//          .childHandler(new ProChannelInitializer());
//          .childHandler(new HttpChannelInitializer());
          .childHandler(new DelimiterChannelInitializer());

      ChannelFuture channelFuture = b.bind(new InetSocketAddress(SystemConstant.SERVER_PORT)).syncUninterruptibly();
      if(channelFuture.isSuccess()){
        log.info("Netty Server start success! port:{}",SystemConstant.SERVER_PORT);
      }
      this.channel = channelFuture.channel();
    }catch (Exception e){
      e.printStackTrace();
    }
    return this.channel;
  }

  public void destory(){
    if(this.channel!=null){
      channel.close();
    }
    boss.shutdownGracefully();
    worker.shutdownGracefully();
  }

}
