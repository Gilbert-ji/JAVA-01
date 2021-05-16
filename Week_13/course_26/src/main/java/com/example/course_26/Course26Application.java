package com.example.course_26;

import com.alibaba.fastjson.JSON;
import com.example.course_26.bean.Order;
import com.example.course_26.kmq.core.KmqMessage;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

@SpringBootApplication
public class Course26Application {

	public static void main(String[] args) {
		SpringApplication.run(Course26Application.class, args);

		String topic = "topic-test";

		System.out.println("------start--------");

		createTopic(topic);

		execute((httpClient) -> {
			providerSend(topic, httpClient);
		});

		execute(httpClient -> {
			consumerPoll(topic, "consumer1", httpClient);
		});

	}

	private static void createTopic(String topic){
		HttpPost post = new HttpPost("http://127.0.0.1:8080/broker/createTopic");
		StringEntity entity = new StringEntity(topic, "utf-8");
		entity.setContentType("application/json;charset=utf-8");
		post.setEntity(entity);
		try(CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(post)) {
			System.out.println("创建Topic----");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void execute(Consumer<CloseableHttpClient> consumer) {
		new Thread(()->{
			try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
				while (true) {
					consumer.accept(httpClient);
					Thread.sleep(3000);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private static void providerSend(String topic, CloseableHttpClient httpClient){
		HttpPost post = new HttpPost("http://127.0.0.1:8080/broker/send");
		Map<String, Object> map = new HashMap<>();
		map.put("topic", topic);
		map.put("message",
				JSON.toJSONString(new KmqMessage(null,
						new Order(100000L + new Random().nextInt(100), System.currentTimeMillis(), "USD2CNY", 6.51d))));
		StringEntity entity = new StringEntity(JSON.toJSONString(map), "utf-8");
		entity.setContentType("application/json;charset=utf-8");
		post.setEntity(entity);
		try(CloseableHttpResponse response = httpClient.execute(post)){
			System.out.println("生产者发送消息！-----------");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void consumerPoll(String topic, String consumer, CloseableHttpClient httpClient){
		HttpPost post = new HttpPost("http://127.0.0.1:8080/broker/poll");
		Map<String, Object> map = new HashMap<>();
		map.put("topic", topic);
		map.put("consumer", consumer);
		StringEntity entity = new StringEntity(JSON.toJSONString(map), "utf-8");
		entity.setContentType("application/json;charset=utf-8");
		post.setEntity(entity);

		try (CloseableHttpResponse response = httpClient.execute(post)) {
			String result = EntityUtils.toString(response.getEntity());
			KmqMessage msg = JSON.parseObject(result, KmqMessage.class);
			if(null != msg){
				System.out.println("消费者-" + consumer + ", 接收消息：" + msg.getBody());
				// 提交消费者的偏移量
				Map<String, Object> offsetMap = new HashMap<>();
				Integer offset = (Integer) msg.getHeaders().get("offset");
//				HttpPost offsetPost = new HttpPost("http://127.0.0.1:8080/broker/commitOffset");
//				offsetMap.put("consumer", consumer);
//				offsetMap.put("offset", offset);
//				StringEntity offsetEntity = new StringEntity(JSON.toJSONString(offsetMap), "utf-8");
//				offsetEntity.setContentType("application/json;charset=utf-8");
//				offsetPost.setEntity(entity);
//				try (CloseableHttpResponse response1 = httpClient.execute(offsetPost)) {
//					System.out.println("消费者提交offset-----");
//				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
