package com.rhb.netty.server.core.handler.websocket;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/25 10:42
 */
@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

  private final String wsUri;

  private static final File INDEX;

  static {
    URL location = HttpRequestHandler.class
        .getProtectionDomain()
        .getCodeSource()
        .getLocation();

    log.info("location:{}",location);

    try{
      String path = location.toURI() + "index.html";
      path = !path.contains("file:") ? path : path.substring(5);
      log.info("path:{}",path);

      INDEX = new File(path);
    }catch (URISyntaxException e){
      throw  new IllegalStateException("Unable to locate index.html",e);
    }
  }

  public HttpRequestHandler(String wsUri){
    this.wsUri = wsUri;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request)
      throws Exception {

    if(wsUri.equalsIgnoreCase(request.uri())){
      /**
       * ctx.fireChannelRead(): 将入站消息转发给下一个 ChannelInboundHandler
       * request.retain(): 对于编码器和解码器来说，其过程也是相当的简单：
       * 一旦消息被编码或者解码，它就会被 ReferenceCountUtil.release(message)调用自动释放。
       * 如果你需要保留引用以便稍后使用，那么你可以调用 ReferenceCountUtil.retain(message)方法。
       * 主要是： 这将会增加该引用计数，从而防止该消息被释放。
       * ReferenceCountUtil: 主要是资源释放相关
       */
      ctx.fireChannelRead(request.retain());
    }else {
      /**
       * 处理100 Continue 请求以符合 HTTP1.1 规范
       */
      if (HttpUtil.is100ContinueExpected(request)){
        send100Continue(ctx);
      }

      RandomAccessFile file = new RandomAccessFile(INDEX,"r");

      /**
       * 找了一下午... 自己都蠢哭了（不熟悉原理和netty整体结构）
       * 这里是HttpResponse不是HttpFullResponse()
       */
      HttpResponse response =  new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
      response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html; charset=UTF-8");

      /**
       * 如果head中keep-alive，则在头部加入信息 （HTTP1.1 规范）
       */
      boolean keepAlive = HttpUtil.isKeepAlive(request);
      if(keepAlive){
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,file.length());
      }

      /**
       * response 写入客户端
       */
      ctx.write(response);

      /**
       * http或https的请求，将页面写入客户端
       */
      if (ctx.pipeline().get(SslHandler.class)==null){
        /**
         * 文件传输一定要搭配ChunkedWriteHandler使用
         */
        ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
      }else {
        /**
         * ssl中，不能使用zero-copy
         */
        ctx.write(new ChunkedNioFile(file.getChannel()));
      }

      /**
       * 将LastHttpContent刷至客户端
       *
       * ChannelFutureListener.CLOSE : 负责断开请求
       */
      ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT)
          .addListener(ChannelFutureListener.CLOSE);
    }
  }

  private static void send100Continue(ChannelHandlerContext ctx){
    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE);
    ctx.writeAndFlush(response);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
