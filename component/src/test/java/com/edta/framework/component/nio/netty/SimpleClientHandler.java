package com.edta.framework.component.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 处理服务端返回的数据
 *
 * @author Administrator
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    public NettyClient nettyClient;

    //向新的连接的发送连接问候
    //当一个新的连接已经被建立时，channelActive(ChannelHandlerContext)将会被调用,随后才调用channelRead去读取
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send greeting for a new connection.
        //InetAddress.getLocalHost().getHostName() 获取电脑主机名
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    //channelRead 读取远端消息的
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            String value = ((ByteBuf) msg).toString(Charset.defaultCharset());
            System.out.println("服务器端返回的数据:" + value);
        }

        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        ctx.channel().attr(key).set("客户端处理完毕");

        //把客户端的通道关闭
        ctx.channel().close();
    }

    //channel关闭后触发这个方法
    //这有两种可能，一种服务端主动 close，还有客户端 colse，你的 handler 里重写捕获异常了吗，如果没有捕获异常，则操作此 channel 的任何异常都会关闭此 channel
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("失败断开连接触发channelInactive方法==========");
        closeChannelAndReConnect(ctx);
    }

    //在读取操作期间，有异常抛出时会调用。
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }

    //通知ChannelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息，此时会调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("这是读取的最后一条数据================================================================================================");
    }

    public void closeChannelAndReConnect(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        if (channel != null) {
            channel.close();
            ctx.close();
        }
        //触发重连
    }
}
