package com.rhb.netty.protocol.defined.pojo.heartbeat;

import com.rhb.netty.protocol.defined.BasePacket;
import com.rhb.netty.protocol.defined.Commond;
import lombok.Data;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/18 15:14
 */
@Data
public class PingRequest extends BasePacket {

  @Override
  public Byte getCommond() {
    return Commond.PING_REQUEST;
  }
}
