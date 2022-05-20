package com.rhb.netty.server.core.handler.http;

import com.rhb.netty.server.core.handler.AbstractBaseChannelHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renhuibo
 * @date 2022/5/19 14:28
 */
@Slf4j
@Sharable
public class HttpFullHttpRequestHandler extends AbstractBaseChannelHandler<FullHttpRequest> {

  @Override
  public void channelRead(Channel channel, FullHttpRequest request) {
    log.info("******************************************************************");

    String uri = request.uri();
    log.info("Request URL:{}",uri);

    HttpMethod method = request.method();
    log.info("Request Method:{}",method.name());

    /**
     * 1. 操作ByteBuf有两个工具类：Unpooled、ByteBufUtil
     * 2. content是指body中的数据
     */
    ByteBuf content = request.content();
    String s = ByteBufUtil.hexDump(content);
    String s1 = content.toString(CharsetUtil.UTF_8);
    log.info("Payload: \r\n{}", s1);

    HttpHeaders headers = request.headers();
    log.info("Request Headers:");
    for(Entry<String, String> entry : headers.entries()){
      log.info("{}:{}",entry.getKey(),entry.getValue());
    }

    HttpVersion httpVersion = request.protocolVersion();
    log.info("Request Version:{}",httpVersion.text());

    DecoderResult decoderResult = request.decoderResult();
    log.info("decoderResult:{}",decoderResult.toString());

    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.OK);

    channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    log.info("******************************************************************");
  }

  private void getDispose(){}

  private void postDispose(){}

  private void deleteDispose(){}
}
