package com.rhb.netty.server;

import cn.hutool.core.collection.ListUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/20 15:27
 */
public class SimpleTest {

  /**
   * stream debug test
   */
  @Test
  public void t1(){
    List<String> list = ListUtil
        .of("blog.didispace.com", "spring4all.com", "openwrite.cn", "www.didispace.com");

    List<String> result = list.stream()
        .filter(e -> e.contains("didispace.com"))
        .filter(e -> e.length() > 17)
        .collect(Collectors.toList());

    System.out.println(result);
  }

}
