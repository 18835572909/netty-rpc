package com.rhb.netty.client.core.handler.custom;

import cn.hutool.json.JSONUtil;
import com.rhb.netty.client.core.handler.AbstractBaseChannelHandler;
import com.rhb.netty.protocol.defined.pojo.login.LoginResponse;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/13 15:41
 */
@Slf4j
public class LoginResponseChannelHandler extends AbstractBaseChannelHandler<LoginResponse> {

  @Override
  public void channelRead(Channel channel, LoginResponse loginResponse) {
    log.info("response:{}", JSONUtil.toJsonStr(loginResponse));
  }

}
