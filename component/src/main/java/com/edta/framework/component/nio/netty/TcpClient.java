package com.edta.framework.component.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


/**
 * @author wangluyao
 * @date 2022/6/30 13:48
 * @description
 */
@Slf4j
@Getter
public class TcpClient extends Client {

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private final Integer retry = Integer.MAX_VALUE;
    private final Long interval = 15000L;
    private final ClientStatus clientStatus;

    public TcpClient(String inetHost, Integer port) {
        super(inetHost, port);
        this.clientStatus = ClientStatus.UNCONNECTED;
    }

    @Override
    protected void init() throws Exception {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        for (ChannelHandler channelHandler : getChannelHandlers()) {
                            socketChannel.pipeline().addLast(channelHandler);
                        }
                    }
                });
    }

    @Override
    public void connect() {
        log.info("TCP客户端开始建立连接，服务端地址: {}", getServerAddress());
        channelFuture = bootstrap.connect(this.getInetHost(), this.getPort())
                .addListener((ChannelFutureListener) (ChannelFuture channelFuture) -> {
                    if (!channelFuture.isSuccess()) {
                        log.info("TCP客户端建立失败，开始重连... {}", this.getServerAddress());
                        channelFuture.channel().eventLoop().schedule(this::connect, interval, TimeUnit.MILLISECONDS);
                        connectFail();
                    } else {
                        log.info("TCP客户端连接建立成功, 服务端地址: {}, 客户端地址: {}",
                                channelFuture.channel().remoteAddress().toString(),
                                channelFuture.channel().localAddress().toString());
                        connectSuccess();
                    }
                });
    }

    public void connectSuccess() {

    }

    public void connectFail() {
    }

    @Override
    public void close() {
        super.close();
        eventLoopGroup.shutdownGracefully();
    }
}
