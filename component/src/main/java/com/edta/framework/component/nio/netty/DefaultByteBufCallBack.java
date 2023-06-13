package com.edta.framework.component.nio.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author wangluyao
 * @date 2022/6/30 16:56
 * @description
 */
public class DefaultByteBufCallBack implements ByteBufCallBack {

    @Override
    public byte[] handle(byte[] bytes) {
        return new byte[]{};
    }

    @Override
    public byte[] handle(ChannelHandlerContext ctx, byte[] bytes) {
        return handle(bytes);
    }
}
