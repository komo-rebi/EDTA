package com.edta.framework.component.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangluyao
 * @date 2022/6/30 13:02
 * @description
 */
@Slf4j
@Getter
public abstract class Client extends Net {

    public Client(String inetHost, Integer port) {
        super(inetHost, port);
    }

    public String getServerAddress() {
        return getAddress();
    }

    public boolean write(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(byteBuf);
        return this.write(byteBuf);
    }

    public boolean write(Object object) {
        if (!isConnected()) {
            log.warn("[server {}] TCP客户端发送数据失败: 通道没有激活", this.getServerAddress());
            return false;
        }
        channelFuture.channel().writeAndFlush(object);
        return true;
    }

    public final boolean isConnected() {
        return channelFuture.channel().isActive() && channelFuture.channel().isOpen();
    }
}
