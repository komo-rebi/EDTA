package com.edta.framework.component.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/6/29 13:54
 * @description
 */
@Slf4j
public class TcpServer extends Server {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;

    public TcpServer(Integer port) {
        super("", port);
    }

    public TcpServer(String inetHost, Integer port) {
        super(inetHost, port);
    }

    public TcpServer(String inetHost, Integer port, List<ChannelHandler> channelHandlers) {
        super(inetHost, port, channelHandlers);
    }

    @Override
    protected void init() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                //使用NioServerSocketChannel作为服务器的通道
                .channel(NioServerSocketChannel.class)
                // .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    //给PipeLine设置处理器
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        for (ChannelHandler channelHandler : getChannelHandlers()) {
                            socketChannel.pipeline().addLast(channelHandler);
                        }
                    }
                });
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
        if (StringUtils.isNotBlank(getInetHost())) {
            channelFuture = bootstrap.bind(getInetHost(), getPort()).sync();
        } else {
            channelFuture = bootstrap.bind(getPort()).sync();
        }
        log.info("TCP服务端启动中... 地址: {}", getAddress());
    }

    @Override
    public void close() {
        super.close();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
