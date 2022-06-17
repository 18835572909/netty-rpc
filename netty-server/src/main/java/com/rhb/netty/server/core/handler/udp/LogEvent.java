package com.rhb.netty.server.core.handler.udp;

import java.net.InetSocketAddress;
import lombok.Getter;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/6/17 10:13
 */
@Getter
public final class LogEvent {
  public static final byte SEPARATOR = (byte) ':';

  private final InetSocketAddress source;
  private final String logfile;
  private final String msg;
  private final long received;

  public LogEvent(String logfile, String msg) {
    this(null, -1, logfile, msg);
  }

  public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
    this.source = source;
    this.logfile = logfile;
    this.msg = msg;
    this.received = received;
  }

}
