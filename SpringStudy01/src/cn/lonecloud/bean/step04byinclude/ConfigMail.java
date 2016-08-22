package cn.lonecloud.bean.step04byinclude;

import org.springframework.context.annotation.Bean;

/**
 * 用于对Mail的配置的config类型
 * @Title: ConfigMail.java
 * @Package cn.lonecloud.bean.step04byinclude
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 下午3:19:21
 */
public class ConfigMail {
	@Bean(name="mail")
	public Mail getMail(){
		return new Mail();
	}
}
