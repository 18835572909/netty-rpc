package com.rhb.netty.client;

import cn.hutool.json.JSONUtil;
import com.rhb.netty.client.core.NettyClient;
import com.rhb.netty.client.core.util.ChannelUtil;
import com.rhb.netty.protocol.defined.pojo.login.LoginRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 14:52
 */
@Slf4j
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(ClientApplication.class,args);
  }

  @Override
  public void run(String... args) throws Exception {
    NettyClient nettyClient = new NettyClient();
    nettyClient.connect();

    for(int i=0;i<3;i++){
      ChannelUtil.sendDemoMsg(nettyClient.getChannel());
      TimeUnit.SECONDS.sleep(3);
    }
  }
}
