package com.biao.gateway.netty.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class MyChannelInboundInitializer extends ChannelInitializer<SocketChannel> {

	private List<String> urls;

	public MyChannelInboundInitializer(List<String> urls) {
		this.urls = urls;
	}
	
	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();

		p.addLast(new HttpServerCodec());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new MyChannelInboundHandler(this.urls));
	}
}
