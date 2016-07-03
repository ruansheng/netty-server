package com.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.entry.Login;
import com.entry.Message;
import com.entry.Proto;
import com.entry.Push;
import com.entry.Success;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.*;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ConnectorHandler extends ChannelHandlerAdapter{
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	public static Map<String, ChannelHandlerContext> userMap = new HashMap<String, ChannelHandlerContext>();
	
	public ConnectorHandler() {
		System.out.println("Connectorhandler");
	}
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		Channel incoming = ctx.channel();
		channels.add(incoming);
        System.out.println("server:"+incoming.remoteAddress()+"在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
    	Channel incoming = ctx.channel();
    	channels.remove(incoming);
        System.out.println("server:"+incoming.remoteAddress()+"掉线");
    }
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {	
		//收到 A -> B 的消息
		String body = (String)obj;
		Proto proto = JSON.parseObject(body, Proto.class);
		System.out.println(body);
		
		System.out.println("channelRead:"+ctx.channel().remoteAddress());
		
		switch(proto.getAction()) {
			case "auth":
				Login login = new Login();
				login.set_(proto.getAction());
				login.setUid(proto.getUid());
				Boolean status = this.Login(login, ctx);
				
				Success su1 = new Success();
				su1.setAction("ret");
				su1.setEc(200);
				su1.setEm("login success");
				
				String cmd = JSON.toJSON(su1).toString();
				System.out.println(cmd);
			    ctx.writeAndFlush(this.buildRespBody(cmd));
				break;
			case "msg":
				System.out.println("msg:"+ctx.channel().remoteAddress());
				
				String to = proto.getTo();
				
				ChannelHandlerContext toc = userMap.get(to);
				
				//ChannelHandlerContext toc = this.foreachMap(to);
				
				if(toc != null) {
					System.out.println("channelRead->msg:"+toc.channel().remoteAddress());
					
					Success su = new Success();
					su.setAction("ret");
					su.setEc(200);
					su.setEm("返回消息");
					
					String scmd = JSON.toJSON(su).toString();
					toc.writeAndFlush(this.buildRespBody(scmd));	
				}				
				
				break;
			case "msg-sync":
				/*
				System.out.println("msg-sync");
				
				Push push = new Push();
				push.setAction("msg-psh");
				push.setFr("9527");
				
				String cmd1 = JSON.toJSON(su).toString();
			    ctx.writeAndFlush(this.buildRespBody(cmd1));
			    */
				break;
			default :
				break;
		}
	}
	
	private ChannelHandlerContext foreachMap(String to) {
		ChannelHandlerContext tctx = null;
		for (Entry<String, ChannelHandlerContext> entry : userMap.entrySet()) {
			System.out.println("k:"+ to + "--" +entry.getKey());
			String tto = entry.getKey();
			if(to.equals(tto)) {
				//tctx = entry.getValue(); 
			}
		}
		System.out.println("sy:" + tctx.channel().remoteAddress());
		return tctx; 
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}
		
	private Boolean Login(Login login, ChannelHandlerContext ctx) {
		Channel incoming = ctx.channel();
		userMap.put(login.getUid(), ctx);
		System.out.println("server:"+incoming.remoteAddress()+"登录");
		return true;
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
