package com.rhb.netty.server;

import com.rhb.netty.server.core.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 20:42
 */
@Slf4j
@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class,args);
    log.info("ServerApplication start ...");
  }

  @Override
  public void run(String... args) throws Exception {
    new NettyServer().start();
  }
}
