package ars.module.mobile.app.baidu;

import java.util.Map;
import java.util.HashMap;

import com.baidu.yun.push.model.PushRequest;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;

import ars.util.Jsons;
import ars.module.mobile.app.baidu.AbstractBaiduMessager;

/**
 * 基于百度云推送的安卓App消息推送实现
 * 
 * @author yongqiangwu
 * 
 */
public class AndroidBaiduMessager extends AbstractBaiduMessager {

	public AndroidBaiduMessager(String app, String token) {
		super(app, token);
	}

	public AndroidBaiduMessager(String url, String app, String token) {
		super(url, app, token);
	}

	@Override
	public PushRequest getPushRequest(String message, Map<String, Object> parameters, String... channels) {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("description", message);
		context.put("custom_content", parameters);
		String messageBody = Jsons.format(context);
		if (channels.length == 0) { // 全部推送
			return new PushMsgToAllRequest().addMessageType(0).addDeviceType(3).addMessage(messageBody);
		} else if (channels.length == 1) { // 定点推送
			return new PushMsgToSingleDeviceRequest().addMessageType(0).addDeviceType(3).addChannelId(channels[0])
					.addMessage(messageBody);
		}
		// 批量推送
		return new PushBatchUniMsgRequest().addMessageType(0).addDeviceType(3).addChannelIds(channels)
				.addMessage(messageBody);
	}

}
