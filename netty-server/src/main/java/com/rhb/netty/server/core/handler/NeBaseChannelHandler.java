package com.rhb.netty.server.core.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 10:31
 */
@Slf4j
public abstract class NeBaseChannelHandler <T> extends SimpleChannelInboundHandler<T> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, T t) throws Exception {
    channelRead(channelHandlerContext.channel(),t);
  }

  public abstract void channelRead(Channel channel,T t);

  /**
   * 事件触发器，常用于心跳检测
   * @param ctx
   * @param evt
   * @throws Exception
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    super.userEventTriggered(ctx, evt);
  }

  /**
   * Channnel注册到EventLoop时
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    super.channelRegistered(ctx);
    log.info("Channel Register ... ");
  }

  /**
   * Channel从EventLoop注销时
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    super.channelUnregistered(ctx);
    log.info("Channel Out ... ");
  }

  /**
   * Channel建立链接，出于活跃状态
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    log.info("Channel Active ... ");
  }

  /**
   * Channel中断链接
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    log.info("Channel Inactive ... ");
  }

  /**
   * 读取完毕
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    super.channelReadComplete(ctx);
    log.info("Read Over ... ");
  }

  /**
   * Channel的可写状态发生改变时调用，可以用于确保写操作不会太快，避免OOM异常
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    super.channelWritabilityChanged(ctx);
    log.info("Write Status Change ... ");
  }

  /**
   * 触发异常
   * @param ctx
   * @param cause
   * @throws Exception
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
    log.info("Netty Server Exception ... ");
    ctx.channel().close();
  }
}
