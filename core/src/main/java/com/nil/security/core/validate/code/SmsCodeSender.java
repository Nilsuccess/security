package com.nil.security.core.validate.code;

/**
 * 短信发送接口
 * @author NIL
 *
 */
public interface SmsCodeSender {

	/**
	 * 发送短信的方法
	 * @param mobile	手机号
	 * @param Code		验证码
	 */
	void send(String mobile, String Code);
}
