package com.rhb.netty.protocol.protocolbuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.rhb.netty.protocol.protocolbuffer.RequestProto.RequestProtocol;
import java.util.Arrays;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/11 10:24
 */
public class ProtoUtil {

  public static byte[] requestEncode(RequestProto.RequestProtocol protocol){
    return protocol.toByteArray();
  }

  public static RequestProto.RequestProtocol requestDecode(byte[] bytes)
      throws InvalidProtocolBufferException {
    return RequestProto.RequestProtocol.parseFrom(bytes);
  }

  public static byte[] responseEncode(ResponseProto.ResponseProtocol protocol){
    return protocol.toByteArray();
  }

  public static ResponseProto.ResponseProtocol responseDecode(byte[] bytes)
      throws InvalidProtocolBufferException {
    return ResponseProto.ResponseProtocol.parseFrom(bytes);
  }

  public static void main(String[] args) throws InvalidProtocolBufferException {
    RequestProtocol requestProtocol = RequestProtocol.newBuilder()
        .setRequestId(System.currentTimeMillis())
        .setType(1)
        .setMsg("hello world")
        .build();

    byte[] encode = requestEncode(requestProtocol);
    System.out.println(Arrays.toString(encode));

    RequestProtocol decode = requestDecode(encode);
    System.out.println(decode.toString());
  }

}
