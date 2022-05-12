package com.rhb.netty.protocol.defined.pojo.login;

import com.rhb.netty.protocol.defined.BasePacket;
import com.rhb.netty.protocol.defined.Commond;
import lombok.Data;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 18:21
 */
@Data
public class LoginRequest extends BasePacket {

  private String userId;

  private String password;

  @Override
  public Byte getCommond() {
    return Commond.LOGIN_REQUEST;
  }
}
