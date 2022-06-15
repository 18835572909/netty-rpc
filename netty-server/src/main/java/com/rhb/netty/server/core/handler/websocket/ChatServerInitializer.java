package com.rhb.netty.server.core.handler.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrameEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/25 11:40
 */
public class ChatServerInitializer extends ChannelInitializer<Channel> {

  private ChannelGroup channelGroup;

  public ChatServerInitializer(ChannelGroup channelGroup){
    this.channelGroup = channelGroup;
  }

  @Override
  protected void initChannel(Channel channel) throws Exception {
    channel.pipeline()
        /**
         * 字节码信息 <==>  HttpRequest、HttpContent、LastHttpContent (字节码信息的编码解码)
         */
        .addLast(new HttpServerCodec())
        /**
         * 写入一个文件内容
         */
        .addLast(new ChunkedWriteHandler())
        /**
         * FullHttpRequest、FullHttpResponse （聚合字节码信息为完整的http请求信息）
         *
         * 这个地方可以添加压缩或解压的处理，来降低网络传输的开销。
         * server: new HttpContentCompressor()
         * client: new HttpContentDecompressor()
         */
        .addLast(new HttpObjectAggregator(64 * 1024))
        /**
         * 1. WebSocket是一种始终以http\https作为开始，然后升级（升级握手：Upgrade handshake）建立的链接。
         * 2. 升级握手，触发的时机并不确定，可能是启动时、或请求到某个url的时候。
         *
         * 所以这里可以定义Http->WebSocket的触发升级
         */
        .addLast(new HttpRequestHandler("/ws"))
        /**
         * 因为WebSocket规范，处理WebSocket升级握手、PingWebSocketFrame、PongWebSocketFrame、CloseWebSocketFrame
         */
        .addLast(new WebSocketServerProtocolHandler("/ws"))
        /**
         * 这里就是具体处理WebSocketFrame的业务逻辑
         */
        .addLast(new TextWebSocketFrameHandler(channelGroup));

  }

}
