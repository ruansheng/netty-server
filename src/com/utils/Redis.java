package com.utils;

import org.jredis.JRedis;
import org.jredis.ri.alphazero.JRedisClient;

public class Redis {
	
	private static JRedis jredis = null;
	
	/**
	 * getRedisInstance
	 * @param host
	 * @param port
	 * @return JRedis
	 */
	public static JRedis getRedisInstance(String host, int port) {
		if(jredis == null) {
			jredis = new JRedisClient("192.168.0.105",6379);	
		}
		return jredis;
	} 
	
	/**
     * close
     */
    public static void close() {
        if (jredis != null) {
            jredis.quit();
        }
    }
}
