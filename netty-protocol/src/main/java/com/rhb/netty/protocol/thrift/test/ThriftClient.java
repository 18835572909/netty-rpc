package com.rhb.netty.protocol.thrift.test;

import com.rhb.netty.protocol.thrift.LoginService;
import com.rhb.netty.protocol.thrift.LoginService.Client;
import com.rhb.netty.protocol.thrift.RequestException;
import com.rhb.netty.protocol.thrift.RequestThrift;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 11:46
 */
public class ThriftClient {

  public static void main(String[] args) {
    TTransport tTransport = null;

    try {
      tTransport = new TSocket("localhost",10100);

      TProtocol tProtocol = new TBinaryProtocol(tTransport);

      LoginService.Client client = new Client(tProtocol);

      tTransport.open();

      RequestThrift requestThrift = new RequestThrift().setRequestId(System.currentTimeMillis()).setMsg("hello").setType(0);

      String response = client.doLogin(requestThrift);

      System.out.println("Responsn:"+response);

    } catch (TTransportException e) {
      e.printStackTrace();
    } catch (RequestException e) {
      e.printStackTrace();
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      tTransport.close();
    }
  }

}
