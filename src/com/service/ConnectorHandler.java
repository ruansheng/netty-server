package com.service;

import com.alibaba.fastjson.JSON;
import com.entry.ConnecterCentext;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ConnectorHandler extends ChannelHandlerAdapter{
	
	public ConnectorHandler() {
		System.out.println("Connectorhandler");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead");
		String body = (String)msg;
		System.out.println(body);
		
		ConnecterCentext body_obj = JSON.parseObject(body, ConnecterCentext.class);
		System.out.println(body_obj);
	
		ConnecterCentext conctx = new ConnecterCentext();
		conctx.setCid("abcdef");
		conctx.setCom("com");
		String cmd = JSON.toJSON(conctx).toString();
		
	    ctx.writeAndFlush(this.buildRespBody(cmd));
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}
		
	/**
	 * 构建response data
	 * @param body
	 * @return
	 */
	private ByteBuf buildRespBody(String body) {
		body += System.getProperty("line.separator");
	    ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
		return resp;
	} 
}
