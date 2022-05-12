package com.rhb.netty.protocol.thrift.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rhb.netty.protocol.thrift.LoginService;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 11:24
 */

public class ThriftServer implements Runnable{

  private static final Logger log = LoggerFactory.getLogger(ThriftServer.class);

  private Integer port;

  public ThriftServer(Integer port){
    this.port = port;
  }

  @Override
  public void run() {
    try {
      // 监听端口
      ServerSocket serverSocket = new ServerSocket(port);
      TServerSocket tServerSocket = new TServerSocket(serverSocket);

      //processor处理器
      LoginService.Processor<LoginServiceImpl> processor = new LoginService.Processor<LoginServiceImpl>(new LoginServiceImpl());

      TServer.Args tServerArgs = new Args(tServerSocket);
      // 中线添加子处理器
      tServerArgs.processor(processor);

      TServer server = new TSimpleServer(tServerArgs);
      System.out.println("Thrift Server Start ... ");

      server.serve();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TTransportException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(15, 15, 0, TimeUnit.SECONDS,
        new LinkedBlockingDeque<Runnable>(), new ThreadFactoryBuilder().setNameFormat("Thrift.Server %d").build());

    threadPoolExecutor.submit(new ThriftServer(10100));
  }
}
