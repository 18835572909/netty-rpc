package com.rhb.netty.server.core.handler.serialize;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * 序列化类型的ChannelHandler:
 *    ## 简单说的话，相当于SpringBoot默认的序列化方式场景理解。
 *    1. JDK
 *      CompatibleObjectDecoder 和使用 JDK 序列化的非基于 Netty 的远程节点进行互操作的解码器
 *      CompatibleObjectEncoder 和使用 JDK 序列化的非基于 Netty 的远程节点进行互操作的编码器
 *      ObjectDecoder 构建于 JDK 序列化之上的使用自定义的序列化来解码的解码器；(当没有其他的外部依赖时，它提供了速度上的改进。否则其他的序列化 实现更加可取)
 *      ObjectEncoder 构建于 JDK 序列化之上的使用自定义的序列化来编码的编码器；
 *    2. JBoss Marshalling
 *      CompatibleMarshallingDecoder
 *      CompatibleMarshallingEncoder    与只使用 JDK 序列化的远程节点兼容
 *      MarshallingDecoder
 *      MarshallingEncoder    适用于使用 JBoss Marshalling 的节点。这些类必须一起使用
 *    3. Protocol Buffer (常用)
 *      ProtobufDecoder 使用 protobuf 对消息进行解码
 *      ProtobufEncoder 使用 protobuf 对消息进行编码
 *      ProtobufVarint32FrameDecoder 根据消息中的 Google Protocol Buffers 的“Base 128 Varints”a 整型长度字段值动态地分割所接收到的 ByteBuf
 *      ProtobufVarint32LengthFieldPrepender 向 ByteBuf 前追加一个 Google Protocal Buffers 的“Base 128 Varints”整型的长度字段值
 *
 * @author renhuibo
 * @date 2022/5/20 11:49
 */
public class SerializeChannelInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(Channel channel) throws Exception {

  }

}
