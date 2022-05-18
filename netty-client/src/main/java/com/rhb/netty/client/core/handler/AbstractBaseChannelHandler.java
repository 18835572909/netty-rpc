package com.rhb.netty.client.core.handler;

import com.rhb.netty.client.core.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用处理器: 也可以将事件处理拆分添加ChannelPipeline中
 *
 * @author renhuibo
 * @date 2022/5/13 10:31
 */
@Slf4j
public abstract class AbstractBaseChannelHandler<T> extends SimpleChannelInboundHandler<T> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, T t) throws Exception {
    channelRead(channelHandlerContext.channel(),t);
  }

  public abstract void channelRead(Channel channel,T t);

  /**
   * Channnel注册到EventLoop时
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    log.info("Channel Register ... ");
    super.channelRegistered(ctx);
  }

  /**
   * Channel从EventLoop注销时
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    log.info("Channel Out ... ");
    super.channelUnregistered(ctx);
  }

  /**
   * Channel建立链接，出于活跃状态
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("Channel Active ... ");
    super.channelActive(ctx);
  }

  /**
   * Channel中断链接
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("Channel Inactive ... ");
    ctx.channel().close();

    /**
     * 断线重连
     */
    ChannelUtil.reConnection(ctx);
  }

  /**
   * 读取完毕
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    log.info("Read Over ... ");
    super.channelReadComplete(ctx);
  }

  /**
   * Channel的可写状态发生改变时调用，可以用于确保写操作不会太快，避免OOM异常
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    log.info("Write Status Change ... ");
    super.channelWritabilityChanged(ctx);
  }

  /**
   * 触发异常
   * 源码流程： AbstractNioByteChannel#NioByteUnsafe#handleReadException()
   *
   * @param ctx
   * @param cause
   * @throws Exception
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.info("Netty Client Exception ... ");

    if(cause instanceof IOException){
      /**
       * 避免异常后触发channelInactive
       */
      ctx.pipeline().remove(this);

      ctx.channel().close();
      /**
       * 断线重连
       */
      ChannelUtil.reConnection(ctx);
    }

  }

}
