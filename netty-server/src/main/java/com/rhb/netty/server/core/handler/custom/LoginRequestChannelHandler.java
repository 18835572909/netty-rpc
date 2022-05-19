package com.rhb.netty.server.core.handler.custom;

import cn.hutool.json.JSONUtil;
import com.rhb.netty.protocol.defined.pojo.login.LoginRequest;
import com.rhb.netty.protocol.defined.pojo.login.LoginResponse;
import com.rhb.netty.server.core.handler.AbstractBaseChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 13:47
 */
@Slf4j
public class LoginRequestChannelHandler extends AbstractBaseChannelHandler<LoginRequest> {

  @Override
  public void channelRead(Channel channel, LoginRequest loginRequest) {
    log.info("LoginRequest : {}", JSONUtil.toJsonStr(loginRequest));

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setUserId(loginRequest.getUserId());
    loginResponse.setUserNickName(loginRequest.getUserId() + ":nickName");
    loginResponse.setUserHead("head img");

    ChannelFuture future = channel.writeAndFlush(loginResponse);
    future.addListener((ChannelFutureListener) channelFuture ->
        log.info("server push msg:[{}]", loginResponse.toString()));
  }

}
