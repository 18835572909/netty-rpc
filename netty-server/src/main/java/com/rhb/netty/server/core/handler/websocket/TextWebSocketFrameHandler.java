package com.rhb.netty.server.core.handler.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.ServerHandshakeStateEvent;
import java.util.Date;

/**
 *  Netty自定义6种帧
 *      BinaryWebSocketFrame: 二进制数据
 *      TextWebSocketFrame: 文本数据
 *      ContinuationWebSocketFrame: 二进制或文本数据
 *      CloseWebSocketFrame: CLOSE请求，包含一个关闭的状态码和关闭的原因
 *      PingWebSocketFrame: Ping
 *      PongWebSocketFrame: Pong
 *
 * @author renhuibo
 * @date 2022/5/25 11:22
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

  private final ChannelGroup channelGroup;

  public TextWebSocketFrameHandler(ChannelGroup channelGroup){
    this.channelGroup = channelGroup;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      TextWebSocketFrame  webSocketFrame) throws Exception {

    String text = webSocketFrame.text();
    System.out.println("websocket server receiver ："+text);

    TextWebSocketFrame res = new TextWebSocketFrame(new Date().toString()+"-"+channelHandlerContext.channel().id()+"===>"+ text);
    channelGroup.writeAndFlush(res);

    /**
     * 增加引用计数，并将消息传递至所有ChannelGroup的客户端中

    channelGroup.writeAndFlush(webSocketFrame.retain());*/
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt == ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){

      ctx.pipeline().remove(HttpRequestHandler.class);

      /**
       * 通知ChannelGroup中的所有客户端，有新的客户端链接
       */
      channelGroup.writeAndFlush(new TextWebSocketFrame("Client Joined. Id:" + ctx.channel().id()));
      /**
       * 将新的WebSocket Channel添加至ChannelGroup中。
       */
      channelGroup.add(ctx.channel());
    }else {
      super.userEventTriggered(ctx, evt);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
