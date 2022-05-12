package com.rhb.netty.protocol.defined;

/**
 * 协议命令
 *
 * @author renhuibo
 * @date 2022/5/12 17:09
 */
public interface Commond {

  Byte LOGIN_REQUEST = 1;

  Byte LOGIN_RESPONSE = 2;

  Byte MSG_REQUEST = 3;

  Byte MSG_RESPONSE = 4;

}
