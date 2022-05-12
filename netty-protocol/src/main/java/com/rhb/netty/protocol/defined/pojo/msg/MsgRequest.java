package com.rhb.netty.protocol.defined.pojo.msg;

import com.rhb.netty.protocol.defined.BasePacket;
import com.rhb.netty.protocol.defined.Commond;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 18:29
 */
public class MsgRequest extends BasePacket {

  @Override
  public Byte getCommond() {
    return Commond.MSG_REQUEST;
  }
}
