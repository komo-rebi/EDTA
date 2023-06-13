package com.edta.framework.component.nio.netty;

import com.edta.framework.component.exception.Assert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangluyao
 * @date 2022/6/29 12:24
 * @description
 */
@Slf4j
@ChannelHandler.Sharable
public class TcpServerHandler extends ChannelInboundHandler {

    private final ByteBufCallBack callBack;

    public TcpServerHandler() {
        this(ByteBufCallBack.DEFAULT_BYTE_BUF_CALL_BACK);
    }

    public TcpServerHandler(ByteBufCallBack callBack) {
        Assert.notNull(callBack);
        this.callBack = callBack;
    }

//    struct common_header {
//        UINT magic_num; /* 魔数，值必须为0xD0BADABA */
//        UCHAR version; /* 版本号，目前值为2 */
//        UCHAR msg_type; /* 消息类型 */
//        UCHAR sub_type;	/* 消息子类型 */
//        UCHAR pad; /* 保留，必须用0x00填充 */
//        UINT session_id; /* 会话流水号 */
//        UINT cont_len; /* 消息体字节长度 */
//    }; /* sizeof = 16B */

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.debug("服务端 ==> 客户端注册: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("服务端 ==> 客户端激活: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("服务端 ==> 客户端断开: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.debug("服务端 ==> 客户端注销: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("服务端 ==> 客户端读取消息: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        this.cacheMsg(ctx, msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.debug("服务端 ==> 客户端消息读取完成: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        ByteBuf byteBuf = null;
        try {
            byte[] bytes = this.readMsg(ctx);
            if (bytes == null) {
                super.channelReadComplete(ctx);
                return;
            }
            byte[] resBytes = callBack.handle(ctx, bytes);
            if (resBytes == null || resBytes.length <= 0) {
                return;
            }
            ctx.channel().writeAndFlush(Unpooled.wrappedBuffer(resBytes));
        } catch (Exception e) {
            log.error("服务端发送消息异常: {}", e.getMessage(), e);
        } finally {
            if (byteBuf != null) {
                byteBuf.release();
            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.debug("服务端 ==> 客户端用户事件: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.debug("服务端 ==> 客户端可写状态变更: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.debug("服务端 ==> 客户端发生异常: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.exceptionCaught(ctx, cause);
    }
}
