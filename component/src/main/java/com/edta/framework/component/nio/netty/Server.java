package com.edta.framework.component.nio.netty;

import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/6/30 11:56
 * @description
 */
@Slf4j
public abstract class Server extends Net {

    public Server(String inetHost, Integer port) {
        super(inetHost, port);
    }

    public Server(String inetHost, Integer port, List<ChannelHandler> channelHandlers) {
        super(inetHost, port, channelHandlers);
    }
}
