package com.rhb.netty.protocol.thrift.test;

import com.rhb.netty.protocol.thrift.LoginService;
import com.rhb.netty.protocol.thrift.RequestException;
import com.rhb.netty.protocol.thrift.RequestThrift;
import org.apache.thrift.TException;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 11:21
 */
public class LoginServiceImpl implements LoginService.Iface {

  @Override
  public String doLogin(RequestThrift request) throws RequestException, TException {
    String allMsg = request.getRequestId()+"#"+request.getMsg()+"#"+request.getType();
    System.out.println(allMsg);
    return allMsg;
  }
}
