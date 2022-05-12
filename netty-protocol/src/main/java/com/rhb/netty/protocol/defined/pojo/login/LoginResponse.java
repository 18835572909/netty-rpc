package com.rhb.netty.protocol.defined.pojo.login;

import com.rhb.netty.protocol.defined.BasePacket;
import com.rhb.netty.protocol.defined.Commond;
import com.rhb.netty.protocol.defined.pojo.login.dto.ChatDTO;
import com.rhb.netty.protocol.defined.pojo.login.dto.FriendDTO;
import com.rhb.netty.protocol.defined.pojo.login.dto.FriendGroupDTO;
import java.util.List;
import lombok.Data;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 18:23
 */
@Data
public class LoginResponse extends BasePacket {

  private String userId;

  private String userHead;

  private String userNick;

  private List<ChatDTO> chatList;

  private List<FriendDTO> friendList;

  private List<FriendGroupDTO> friendGroupList;

  @Override
  public Byte getCommond() {
    return Commond.LOGIN_RESPONSE;
  }
}
