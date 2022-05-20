package com.rhb.netty.server.core.handler.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**

 *
 * @author renhuibo
 * @date 2022/5/19 14:11
 */
public class HttpChannelInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(Channel channel) throws Exception {
    scheme1(channel);
  }

  /**
   * http协议包数据格式：
   *    HttpRequest、List<HttpContent>、LastHttpContent
   */
  private void scheme0(Channel channel){
    channel.pipeline()
        .addLast(new HttpRequestDecoder())
        .addLast(new HttpResponseEncoder());
  }

  /**
   * FullHttpRequest：整个协议包数据
   */
  private void scheme1(Channel channel){
    channel.pipeline()
        .addLast(new HttpServerCodec())
        // 压缩，支持gzip 和 deflate
        .addLast(new HttpContentCompressor())
        // 聚合，设置消息体最大值 512k
        .addLast(new HttpObjectAggregator(512 * 1024))
        .addLast(new HttpFullHttpRequestHandler());

    /**
     * 相对应的类： HttpClientCodec、HttpContentDecompressor
     */
  }

  /**
   * 提供ssl支持Https请求
   * @param channel
   * @param sslContext
   */
  private void schemeSsl(Channel channel, SSLContext sslContext){
    SSLEngine engine = sslContext.createSSLEngine();
    channel.pipeline().addFirst(new SslHandler(engine))
        .addLast(new HttpServerCodec())
        // 压缩，支持gzip 和 deflate
        .addLast(new HttpContentCompressor())
        // 聚合，设置消息体最大值 512k
        .addLast(new HttpObjectAggregator(512 * 1024))
        .addLast(new HttpFullHttpRequestHandler());
  }

}
