package com.edta.framework.component.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
class TcpClientTest {

    @SneakyThrows
    @Test
    void clientTest() {
//        TcpServer tcpServer = new TcpServer(9000);
//        tcpServer.start();
//        tcpServer.addChannelHandler(new TcpServerHandler(bytes -> {
//            String buf = new String(bytes, StandardCharsets.UTF_8);
//            log.info("服务端收到消息: [{}], size: {}", buf, bytes.length);
//            return "hello".getBytes(StandardCharsets.UTF_8);
//        }));
        TcpClient tcpClient = new TcpClient("127.0.0.1", 32700);
        tcpClient.addChannelHandler(new TcpClientHandler(tcpClient, new DefaultByteBufCallBack() {
            @Override
            public byte[] handle(ChannelHandlerContext ctx, byte[] bytes) {
                log.info("客户端收到消息: {}", new String(bytes, StandardCharsets.UTF_8));
                return new byte[0];
            }
        }));
        tcpClient.start();
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String test = "I'm client";
            ByteBuf byteBuf = Unpooled.buffer(test.length());
            byteBuf.writeBytes(test.getBytes(StandardCharsets.UTF_8));
            tcpClient.write(byteBuf);
            Thread.sleep(2000);
        }

//        for (int i = 0; i < 100; i++) {
//            stringBuilder.append("123456789; ");
//        }
//        String test = stringBuilder.toString();
//        ByteBuf byteBuf = Unpooled.buffer(test.length());
//        byteBuf.writeBytes(test.getBytes(StandardCharsets.UTF_8));
//        tcpClient.write(byteBuf);
//        while (true) {
//            Thread.sleep(60 * 1000);
//        }
    }


    @SneakyThrows
    @Test
    void clientTest1() {
        TcpServer tcpServer = new TcpServer(9000);
        tcpServer.start();
        tcpServer.addChannelHandler(new TcpServerHandler(new DefaultByteBufCallBack() {
            @Override
            public byte[] handle(ChannelHandlerContext ctx, byte[] bytes) {
                String buf = new String(bytes);
                log.info(buf);
                // FileUtil.appendString(buf, new File("test.txt"), StandardCharsets.UTF_8);
                return null;
            }
        }));
        TcpClient tcpClient = new TcpClient("127.0.0.1", 9000);
        tcpClient.start();

        for (int i = 0; i < 5; i++) {
            String test = "<START><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><?xml version=\"1.0\" encoding=\"UTF-8\" ?><MSG VERSION=\"2\"><LICENSE>test_license</LICENSE><TTL><BEGIN>2012-08-21 21:05:06</BEGIN><END>2023-09-15 20:05:06</END></TTL><SERVICE>TUP5</SERVICE><FILTER><group_id>0</group_id><ip_version>%d</ip_version><src_ip>%s</src_ip><src_ip_mask>%d</src_ip_mask><dst_ip>%s</dst_ip><dst_ip_mask>%d</dst_ip_mask><src_port>%d</src_port><src_port_mask>%d</src_port_mask><dst_port>%d</dst_port><dst_port_mask>%d</dst_port_mask><protocol>TCP</protocol><dir>BOTH</dir><istest>0</istest><result>0</result><balance_group_num>64</balance_group_num><balance_group_id>1</balance_group_id></FILTER><PRIORITY>high</PRIORITY></MSG><END>";
            ByteBuf byteBuf = Unpooled.buffer(test.length());
            byteBuf.writeBytes(test.getBytes(StandardCharsets.UTF_8));
            tcpClient.write(byteBuf);
//            Thread.sleep(1);
        }

        Thread.sleep(60 * 1000);
    }
}