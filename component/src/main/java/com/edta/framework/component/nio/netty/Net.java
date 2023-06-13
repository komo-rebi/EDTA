package com.edta.framework.component.nio.netty;

import com.edta.framework.component.exception.SysException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangluyao
 * @date 2022/6/30 13:04
 * @description
 */
@Slf4j
@Getter
public abstract class Net {

    protected ChannelFuture channelFuture;
    private final String inetHost;
    private final Integer port;
    private final List<ChannelHandler> channelHandlers;

    public Net(String inetHost, Integer port) {
        this(inetHost, port, Collections.synchronizedList(new ArrayList<>()));
    }

    public Net(String inetHost, Integer port, List<ChannelHandler> channelHandlers) {
        this.inetHost = inetHost;
        this.port = port;
        this.channelHandlers = channelHandlers;
    }

    public String getAddress() {
        return inetHost + ":" + port;
    }

    public void addChannelHandler(ChannelHandler handler) {
        this.channelHandlers.add(handler);
    }

    public List<ChannelHandler> getChannelHandlers() {
        return channelHandlers;
    }

    protected abstract void init() throws Exception;

    public void start() {
        try {
            init();
            connect();
            //在nio线程连接建立好了以后，会调用
            channelFuture.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    Channel channel = channelFuture.channel();
                    log.info("网络服务启动成功，地址: {}", channel.localAddress());
                } else {
                    log.error("网络启动失败");
                }
            });
            // 阻塞
            // channelFuture.channel().closeFuture().sync();
            channelFuture.channel().closeFuture().addListener(future -> {
                if (future.isSuccess()) {
                    success();
                } else {
                    fail();
                }
            });
        } catch (Exception e) {
            log.error("Net异常: {}", e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        }
    }

    protected void connect() {
//        channelFuture.addListener((ChannelFutureListener) channelFuture -> {
//            if (channelFuture.isSuccess()) {
//                success();
//            } else {
//                fail();
//            }
//        });

//        channelFuture.channel().closeFuture().addListener((ChannelFutureListener) channelFuture -> close());
    }

    protected void success() {
        log.info("网络关闭成功, 地址: {}", channelFuture.channel().localAddress());
    }

    protected void fail() {
        Throwable cause = channelFuture.cause();
        log.error("网络关闭失败: {}", cause.getMessage(), cause);
    }

    public void close() {
        // channelFuture.channel().close();
    }
}
