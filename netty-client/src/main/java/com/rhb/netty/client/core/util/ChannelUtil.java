package com.rhb.netty.client.core.util;

import cn.hutool.json.JSONUtil;
import com.rhb.netty.client.core.NettyClient;
import com.rhb.netty.constant.SystemConstant;
import com.rhb.netty.protocol.defined.pojo.login.LoginRequest;
import com.rhb.netty.protocol.protocolbuffer.RequestProto;
import com.rhb.netty.protocol.protocolbuffer.RequestProto.RequestProtocol;
import com.rhb.netty.protocol.protocolbuffer.ResponseProto;
import com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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

  /**
   * protocol buffer 发送信息
   * @param client
   */
  public static void sendProtocolReqMsg(Channel client){
    RequestProtocol hello = RequestProtocol.newBuilder()
        .setMsg("hello")
        .setType(1)
        .setRequestId(System.currentTimeMillis())
        .build();

    client.writeAndFlush(hello).addListener((ChannelFutureListener) future -> {
      if(!future.isSuccess()){
        log.info("请求服务出现错误");
      }
    });
  }

  /**
   * 测试 LineBasedChannelHandler 发送信息
   * @param client
   */
  public static void sendLineByteBufReqMsg(Channel client){
    String content = "hello world\r\nchina body\r\n";

    client.writeAndFlush(content).addListener((ChannelFutureListener) future -> {
      if(!future.isSuccess()){
        log.info("请求服务出现错误");
      }
    });
  }

  /**
   * 测试 DelimiterBasedChannelHandler 发送信息
   * @param client
   */
  public static void sendDelimiterByteBufReqMsg(Channel client){
    String content = "hello world"+ SystemConstant.SERVER_DELIMITET +"china body" + SystemConstant.SERVER_DELIMITET;
    byte[] req = content.getBytes();
    ByteBuf message = Unpooled.buffer(req.length);
    message.writeBytes(req);

    client.writeAndFlush(message).addListener((ChannelFutureListener) future -> {
      if(!future.isSuccess()){
        log.info("请求服务出现错误");
      }
    });
  }

}
