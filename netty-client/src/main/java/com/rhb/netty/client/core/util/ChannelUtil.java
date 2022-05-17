package com.rhb.netty.client.core.util;

import cn.hutool.json.JSONUtil;
import com.rhb.netty.client.core.NettyClient;
import com.rhb.netty.protocol.defined.pojo.login.LoginRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Channel工具类
 *
 * @author renhuibo
 * @date 2022/5/17 17:28
 */
@Slf4j
public class ChannelUtil {

  /**
   * 重连
   * @param ctx 数据在pipeLine上传输的上下文
   */
  public static void reConnection(ChannelHandlerContext ctx){
    ctx.channel().eventLoop().schedule(()->{
      log.info("reconnection ...");
      if(!new NettyClient().connect()){
        reConnection(ctx);
      };
    },3, TimeUnit.SECONDS);
  }

  /**
   * 发送消息
   * @param client 消息的载体
   */
  public static void sendDemoMsg(Channel client){
    LoginRequest request = new LoginRequest();
    request.setUserId(System.currentTimeMillis()+"");
    request.setPassword("123");
    ChannelFuture channelFuture = client.writeAndFlush(request);

    boolean success = channelFuture.isSuccess();
    boolean cancelled = channelFuture.isCancelled();
    boolean done = channelFuture.isDone();
    boolean cancellable = channelFuture.isCancellable();
    log.info("success:{},cancelled:{},done:{},cancellable:{}",success,cancelled,done,cancellable);

    channelFuture.addListener(channelFutureListener -> log.info("request: {}", JSONUtil.toJsonStr(request)));
  }

}
