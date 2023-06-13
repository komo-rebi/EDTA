package com.edta.framework.component.nio.netty;

import com.edta.framework.component.common.CallBack;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wangluyao
 * @date 2022/6/30 16:53
 * @description
 */
public interface ByteBufCallBack extends CallBack<byte[], byte[]> {

    ByteBufCallBack DEFAULT_BYTE_BUF_CALL_BACK = new DefaultByteBufCallBack();

    byte[] handle(ChannelHandlerContext ctx, byte[] bytes);
}
