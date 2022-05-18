package com.rhb.netty.protocol.defined;

import com.rhb.netty.protocol.defined.pojo.heartbeat.PingRequest;
import com.rhb.netty.protocol.defined.pojo.heartbeat.PongResponse;
import com.rhb.netty.protocol.defined.pojo.login.LoginRequest;
import com.rhb.netty.protocol.defined.pojo.login.LoginResponse;
import com.rhb.netty.protocol.defined.pojo.msg.MsgRequest;
import com.rhb.netty.protocol.defined.pojo.msg.MsgResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 协议包
 *
 * @author renhuibo
 * @date 2022/5/12 18:30
 */
public abstract class BasePacket {

  private static final Map<Byte,Class<? extends BasePacket>> PACKET_TYPE = new ConcurrentHashMap<>();

  static{
    PACKET_TYPE.put(Commond.LOGIN_REQUEST, LoginRequest.class);
    PACKET_TYPE.put(Commond.LOGIN_RESPONSE, LoginResponse.class);
    PACKET_TYPE.put(Commond.MSG_REQUEST, MsgRequest.class);
    PACKET_TYPE.put(Commond.MSG_RESPONSE, MsgResponse.class);
    PACKET_TYPE.put(Commond.PING_REQUEST, PingRequest.class);
    PACKET_TYPE.put(Commond.PONG_RESPONSE, PongResponse.class);
  }

  public static Class<? extends BasePacket> getType(Byte commond){
    return PACKET_TYPE.get(commond);
  }

  /**
   * 获取协议指令
   *
   * @return 协议指令
   */
  public abstract Byte getCommond();

}
