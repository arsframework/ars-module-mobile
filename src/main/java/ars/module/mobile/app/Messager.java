package ars.module.mobile.app;

import java.util.Map;

/**
 * App消息推送接口
 * 
 * @author yongqiangwu
 *
 */
public interface Messager {
	/**
	 * 消息推送
	 * 
	 * @param message
	 *            消息内容
	 * @param channels
	 *            设备列表
	 * @throws Exception
	 *             操作异常
	 */
	public void push(String message, String... channels) throws Exception;

	/**
	 * 消息推送
	 * 
	 * @param message
	 *            消息内容
	 * @param parameters
	 *            附加参数
	 * @param channels
	 *            设备列表
	 * @throws Exception
	 *             操作异常
	 */
	public void push(String message, Map<String, Object> parameters, String... channels) throws Exception;

}
