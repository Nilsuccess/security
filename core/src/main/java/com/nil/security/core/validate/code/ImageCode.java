package com.nil.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 * 图片验证码封装类
 * @author NIL
 *
 */
public class ImageCode extends ValidateCode{

	/**
	 * 图片
	 */
	private BufferedImage image;
	

	public ImageCode(BufferedImage image, String code, LocalDateTime exprieTime) {
		super(code, exprieTime);
		this.image = image;
	}
	
	/**
	 * 构造，传入的过期时间是秒
	 * @param image
	 * @param code
	 * @param exprieSecond
	 */
	public ImageCode(BufferedImage image, String code, int exprieSecond) {
		super(code, exprieSecond);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}		
}
