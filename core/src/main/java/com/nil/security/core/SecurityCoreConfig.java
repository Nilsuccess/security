package com.nil.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.nil.security.core.properties.SecurityProperties;

/**
 * 使读取配置文件的类生效
 * @author NIL
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
