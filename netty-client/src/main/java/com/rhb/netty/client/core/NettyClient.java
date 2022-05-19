package com.rhb.netty.client.core;

import com.rhb.netty.client.core.handler.protocol.ProChannelInitializer;
import com.rhb.netty.constant.SystemConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.ConnectException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 14:53
 */
@Slf4j
public class NettyClient {

  private EventLoopGroup worker = new NioEventLoopGroup();

  private Channel channel;

  private Bootstrap bootstrap;

  public NettyClient(){
    bootstrap = new Bootstrap();
    bootstrap.group(worker)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.AUTO_READ,true)
        .handler(new ProChannelInitializer());
//        .handler(new NeChannelInitializer());
  }

  public boolean connect(){

    try {
      ChannelFuture channelFuture = bootstrap
          .connect(SystemConstant.SERVER_HOST, SystemConstant.SERVER_PORT);

      /**
       * await()和sync()的区别？
       */
      boolean result = channelFuture.awaitUninterruptibly(30, TimeUnit.SECONDS);

      this.channel = channelFuture.channel();

      if (result) {
        if (this.channel != null && channel.isActive()) {
          log.info("client connect success!");
          return true;
        }

        Throwable cause = channelFuture.cause();
        if (cause != null) {
          exceptionHandler(cause);
        }
      }
    }catch (Exception e){
      exceptionHandler(e);
    }

    destory();
    return false;
  }

  private void exceptionHandler(Throwable cause) {
    if (cause instanceof ConnectException) {
      log.error("连接异常:{}", cause.getMessage());
    } else if (cause instanceof ClosedChannelException) {
      log.error("connect error:{}", "client has destroy");
    } else {
      log.error("connect error:", cause);
    }
  }

  public Channel getChannel(){
    return this.channel;
  }

  public void destory(){
    if(channel!=null){
      channel.close();
      worker.shutdownGracefully();
    }
  }

}
