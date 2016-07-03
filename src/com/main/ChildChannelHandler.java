package com.main;

import com.service.ConnectorHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
        arg0.pipeline().addLast(new StringDecoder());		
		arg0.pipeline().addLast(new ConnectorHandler());
		
		System.out.println("ChildChannelHandler:" + arg0.remoteAddress() +"连接上");
	}
}