package ars.module.mobile.app.baidu;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import com.baidu.yun.push.model.PushRequest;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;

import ars.util.Jsons;
import ars.module.mobile.app.baidu.AbstractBaiduMessager;

/**
 * 基于百度云推送的苹果App消息推送实现
 * 
 * @author yongqiangwu
 * 
 */
public class IOSBaiduMessager extends AbstractBaiduMessager {
	protected final boolean develop; // 是否为开发状态

	public IOSBaiduMessager(String app, String token) {
		this(app, token, false);
	}

	public IOSBaiduMessager(String app, String token, boolean develop) {
		super(app, token);
		this.develop = develop;
	}

	public IOSBaiduMessager(String url, String app, String token) {
		this(url, app, token, false);
	}

	public IOSBaiduMessager(String url, String app, String token, boolean develop) {
		super(url, app, token);
		this.develop = develop;
	}

	@Override
	public PushRequest getPushRequest(String message, Map<String, Object> parameters, String... channels) {
		Map<String, Object> context = new HashMap<String, Object>();
		Map<String, Object> aps = new HashMap<String, Object>();
		aps.put("alert", message);
		context.put("aps", aps);
		for (Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			if (context.containsKey(key)) {
				throw new IllegalArgumentException("Parameter key is already exists:" + key);
			}
			context.put(key, entry.getValue());
		}
		String messageBody = Jsons.format(context);
		if (channels.length == 0) { // 全部推送
			return new PushMsgToAllRequest().addMessageType(1).addDepolyStatus(this.develop ? 1 : 2).addDeviceType(4)
					.addMessage(messageBody);
		} else if (channels.length == 1) { // 定点推送
			return new PushMsgToSingleDeviceRequest().addMessageType(1).addDeployStatus(this.develop ? 1 : 2)
					.addDeviceType(4).addChannelId(channels[0]).addMessage(messageBody);
		}
		// 批量推送
		return new PushBatchUniMsgRequest().addMessageType(1).addDeviceType(4).addChannelIds(channels)
				.addMessage(messageBody);
	}

}
