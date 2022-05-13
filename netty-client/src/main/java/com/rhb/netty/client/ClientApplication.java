package com.rhb.netty.client;

import cn.hutool.json.JSONUtil;
import com.rhb.netty.client.core.NettyClient;
import com.rhb.netty.protocol.defined.pojo.login.LoginRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 14:52
 */
@Slf4j
public class ClientApplication {

  public static void main(String[] args) throws InterruptedException {
    Channel client = new NettyClient().start();

    for(int i=0;i<10;i++){
      LoginRequest request = new LoginRequest();
      request.setUserId(System.currentTimeMillis()+"");
      request.setPassword("123");
      ChannelFuture channelFuture = client.writeAndFlush(request);
      boolean success = channelFuture.isSuccess();
      boolean cancelled = channelFuture.isCancelled();
      boolean done = channelFuture.isDone();
      boolean cancellable = channelFuture.isCancellable();
      log.info("{},{},{},{}",success,cancelled,done,cancellable);

      channelFuture.addListener(channelFutureListener -> log.info("request: {}", JSONUtil.toJsonStr(request)));
      TimeUnit.SECONDS.sleep(10);
    }

  }

}
