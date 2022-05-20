package com.rhb.netty.server.core.handler.file;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 *
 * FileRegionChannelHandler: 主要处理大文件传输
 *
 * FileRegion: 通过支持零拷贝的文件传输的 Channel 来发送的文件区域
 * 优点： 利用 NIO 的零拷贝特性，消除了将文件的内容从文件系统移动到网络栈的复制过程
 *
 * 当需要将文件内容复制到用户内存的时候：ChunkedWriteHandler
 * ChunkedWriteHandler中主要处理文件的是ChunkedInput接口
 * ChunkedInput的实现类有：
 *    ChunkedFile：从文件中逐块获取数据，当你的平台不支持零拷贝或者你需要转换数据时使用
 *    ChunkedNioFile：和 ChunkedFile 类似，只是它使用了 FileChannel
 *    ChunkedStream：从 InputStream 中逐块传输内容
 *    ChunkedNioStream：从 ReadableByteChannel 中逐块传输内容
 *
 * @author renhuibo
 * @date 2022/5/20 11:38
 */
public class FileRegionChannelInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(Channel channel) throws Exception {

  }

}
