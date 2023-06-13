package com.edta.framework.component.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * 处理客户端返回的数据
 *
 * @author Administrator
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端通道的数据   //channelRead 读取远端消息的
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //可以在这里面写一套类似SpringMVC的框架
        //让SimpleServerHandler不跟任何业务有关，可以封装一套框架
        if (msg instanceof ByteBuf) {
            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
        }
//
//        //业务逻辑代码处理框架。。。
//
//        //返回给客户端的数据，告诉客户端已经读到服务端数据了
//        String result = "hello client ";
//        //ByteBuf 用于存入数据和从里面读取数据，相当于申请了一块内存。
//        ByteBuf buf = Unpooled.buffer();
//        buf.writeBytes(result.getBytes());
//        ctx.channel().writeAndFlush(buf);
//
//        ByteBuf buf2 = Unpooled.buffer();
//        buf2.writeBytes("\r\n".getBytes());
//        ctx.channel().writeAndFlush(buf2);
//        System.out.println("==========");
    }
}