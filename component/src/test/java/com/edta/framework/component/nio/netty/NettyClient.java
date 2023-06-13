package com.edta.framework.component.nio.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Netty客户端编写
 *
 * @author Administrator
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 首先，netty通过Bootstrap启动客户端，服务端一般使用ServerBootstrap
        Bootstrap client = new Bootstrap();

        //第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        //第2步 绑定客户端通道
        client.channel(NioSocketChannel.class);

        //第3步 给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new ChannelInitializer<NioSocketChannel>() {  //通道是NioSocketChannel
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                //字符串编码器，一定要加在SimpleClientHandler 的上面
                pipeline.addLast(new StringEncoder());
                pipeline.addLast(new DelimiterBasedFrameDecoder(
                        Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                //找到他的管道 增加他的handler
                pipeline.addLast(new SimpleClientHandler());
            }
        });

        //连接服务器
        ChannelFuture future = client.connect("localhost", 8080).sync();
        Channel channel = future.channel();
        //代码分析的很复杂，但结论很简单：被Bootstrap引导的NioSocketChannel在构造好之后就进入了open状态，之后通过把自己注册进EventLoop进入registered状态，接着连接服务器进入active状态。
        // channel.isOpen() --> channel.isRegistered() -->channel.isActive()
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("---客户端启动成功，连接的是===port：" + 8080);
                    //发送数据给服务器
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", "wangluyao");
                    user.put("num", 12);
                    //发送给客户端信息
                    channel.writeAndFlush(JSONObject.toJSONString(user) + "\r\n");
                    for (int i = 0; i < 5; i++) {
                        //这里发送的收必须是换行符之类的，不然不能编码和解码
                        String msg = "ssss" + i + "\r\n";
                        channel.writeAndFlush(msg);
                    }
                } else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
        //当通道关闭了，就继续往下走
        channel.closeFuture().sync();

        //接收服务端返回的数据
        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        Object result = channel.attr(key).get();
        System.out.println(result.toString());
    }
}