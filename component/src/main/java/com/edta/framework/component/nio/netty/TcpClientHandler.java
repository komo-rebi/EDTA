package com.edta.framework.component.nio.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import static com.edta.framework.component.nio.netty.ByteBufCallBack.DEFAULT_BYTE_BUF_CALL_BACK;

/**
 * @author wangluyao
 * @date 2022/6/29 12:26
 * @description
 */
@Slf4j
@ChannelHandler.Sharable
public class TcpClientHandler extends ChannelInboundHandler {

    private final ByteBufCallBack callBack;
    private final TcpClient tcpClient;

    public TcpClientHandler(TcpClient tcpClient) {
        this(tcpClient, DEFAULT_BYTE_BUF_CALL_BACK);
    }

    public TcpClientHandler(TcpClient tcpClient, ByteBufCallBack callBack) {
        this.tcpClient = tcpClient;
        this.callBack = callBack;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端 ==> 注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端 ==> 激活: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端 ==> 断开: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.channelInactive(ctx);
        log.info("TCP通道断开，客户端开始重连... {}", ctx.channel().remoteAddress().toString());
        // FIXME 如果接受到rst包，会出发这里的重新连接
        Thread.sleep(5000);
        this.tcpClient.connect();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端 ==> 注销");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("客户端 ==> 读取消息: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        this.cacheMsg(ctx, msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端 ==> 消息读取完成: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        byte[] bytes = this.readMsg(ctx);
        if (bytes == null || bytes.length <= 0) {
            super.channelReadComplete(ctx);
            return;
        }
        callBack.handle(ctx, bytes);
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.debug("客户端 ==> 用户事件: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端 ==> 可写状态变更为" + ctx.channel().isWritable());
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.debug("客户端 ==> 发生异常: {}, {}", ctx.channel().remoteAddress().toString(), ctx.channel().localAddress().toString());
        ctx.close();
        log.error("客户端发生异常: {}", cause.getMessage(), cause);
    }
}
