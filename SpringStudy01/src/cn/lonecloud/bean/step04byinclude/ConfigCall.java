package cn.lonecloud.bean.step04byinclude;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Call的config类
 * @Title: ConfigCall.java
 * @Package cn.lonecloud.bean.step04byinclude
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 下午3:17:34
 */
@Configuration
public class ConfigCall {
	@Bean(name="call")
	public Call getCall(){
		return new Call();
	}
}
