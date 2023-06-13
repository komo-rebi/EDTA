package com.edta.framework.component.nio.netty;

import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author wangluyao
 * @date 2022/6/30 22:10
 * @description
 */
@Slf4j
class TcpServerTest {

    @SneakyThrows
    @Test
    void serverTest() {
        TcpServer tcpServer = new TcpServer("127.0.0.1", 32700);
        tcpServer.addChannelHandler(new TcpServerHandler(new DefaultByteBufCallBack() {
            @Override
            public byte[] handle(ChannelHandlerContext ctx, byte[] bytes) {
                String buf = new String(bytes, StandardCharsets.UTF_8);
                log.info("服务端收到消息: [{}], size: {}", buf, bytes.length);
                return "hello".getBytes(StandardCharsets.UTF_8);
            }
        }));
        tcpServer.start();
        while (true) {
            Thread.sleep(1000);
        }
    }

}