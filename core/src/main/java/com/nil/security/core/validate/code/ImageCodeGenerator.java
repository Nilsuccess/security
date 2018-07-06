package com.nil.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.nil.security.core.properties.SecurityProperties;

public class ImageCodeGenerator implements ValidateCodeGenerator{
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 生成图片验证码
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ValidateCode createImageCode(HttpServletRequest request) {
		int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidateCodeProperties().getImageCodeProperties().getWidth());
		int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getValidateCodeProperties().getImageCodeProperties().getHeight());
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}
		String sRand = "";
		for (int i = 0; i < securityProperties.getValidateCodeProperties().getImageCodeProperties().getLength(); i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}
		logger.info("验证码：" + sRand);
		g.dispose();

		return new ImageCode(image, sRand, securityProperties.getValidateCodeProperties().getImageCodeProperties().getExpireIn());
	
	}
	
	
	@Override
	public ValidateCode createSmsCode(HttpServletRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getValidateCodeProperties().getSmsCode().getLength());
		return new ValidateCode(code, securityProperties.getValidateCodeProperties().getImageCodeProperties().getExpireIn());
	}
	
	
	
	
	
	
	
	/**
	 * 生成随机条纹
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		return getRandColor(fc, bc, fc);
	}

	private Color getRandColor(int fc, int bc, int interval) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + new Random().nextInt(bc - interval);
		int g = fc + new Random().nextInt(bc - interval);
		int b = fc + new Random().nextInt(bc - interval);
		return new Color(r, g, b);
	}
	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}
	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	
	

}
