package com.nil.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 * 图片验证码封装类
 * @author NIL
 *
 */
public class ImageCode {

	/**
	 * 图片
	 */
	private BufferedImage image;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 过期时间
	 */
	private LocalDateTime exprieTime;

	public ImageCode(BufferedImage image, String code, LocalDateTime exprieTime) {
		this.image = image;
		this.code = code;
		this.exprieTime = exprieTime;
	}
	
	/**
	 * 构造，传入的过期时间是秒
	 * @param image
	 * @param code
	 * @param exprieSecond
	 */
	public ImageCode(BufferedImage image, String code, int exprieSecond) {
		this.image = image;
		this.code = code;
		this.exprieTime = LocalDateTime.now().plusSeconds(exprieSecond);
	}
	
	/**
	 * 判断验证码是否过期
	 * @return
	 */
	public boolean isExpried () {
		return LocalDateTime.now().isAfter(exprieTime);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExprieTime() {
		return exprieTime;
	}

	public void setExprieTime(LocalDateTime exprieTime) {
		this.exprieTime = exprieTime;
	}
	
	
}
