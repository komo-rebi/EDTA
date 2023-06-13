package com.edta.framework.component.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author wangluyao
 * @date 2022/6/30 16:46
 * @description
 */
public abstract class ChannelInboundHandler extends ChannelInboundHandlerAdapter {

    protected void cacheMsg(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = null;
        if (msg instanceof ByteBuf) {
            byteBuf = (ByteBuf) msg;
        }
        if (byteBuf == null) {
            return;
        }
        //
        AttributeKey<byte[]> key = AttributeKey.valueOf("data");
        int writerIndex = byteBuf.writerIndex();
        byte[] bytes = new byte[writerIndex];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        //
        byte[] cacheBytes = ctx.channel().attr(key).get();
        if (cacheBytes == null) {
            cacheBytes = bytes;
        } else {
            cacheBytes = ArrayUtils.addAll(cacheBytes, bytes);
        }
        ctx.channel().attr(key).set(cacheBytes);
    }

    protected byte[] readMsg(ChannelHandlerContext ctx) {
        AttributeKey<byte[]> key = AttributeKey.valueOf("data");
        byte[] cacheBytes = ctx.channel().attr(key).get();
        ctx.channel().attr(key).set(null);
        return cacheBytes;
    }
}
