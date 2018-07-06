package com.nil.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 图片验证码控制器
 * 
 * @author NIL
 *
 */
@RestController
public class VaildateCodeController {

	static final String SESSION_KEY_IMAGE = "SESSION_KEY_IMAGE_CODE";
	static final String SESSION_KEY_SMS = "SESSION_KEY_SMS_CODE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private ValidateCodeGenerator validateCodeGenerator;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	/**
	 *  图片验证码生成控制器
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.获取图片验证码
		ImageCode imageCode = (ImageCode) validateCodeGenerator.createImageCode(request);
		// 2.将验证码存在session中
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_IMAGE, imageCode);
		// 3.将生成的图片写到响应的接口中
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
	
	/**
	 * 短信验证码生成控制器
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletRequestBindingException 
	 */ 
	@GetMapping("/code/sms")
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
		// 1.获取短信验证码
		ValidateCode smsCode = validateCodeGenerator.createSmsCode(request);
		// 2.将验证码存在session中
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS, smsCode);
		// 3.将生成的短信验证码调用接口发送出去【这里打印到控制台上】
		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile, smsCode.getCode());
	}
}































