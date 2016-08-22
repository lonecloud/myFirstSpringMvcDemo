package cn.lonecloud.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	private static ApplicationContext applicationContext = null;

	public static ApplicationContext getApplicationContext() {
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// 配置xml文件
		if (applicationContext != null) {
			return applicationContext;
		}
		return null;
	}
	public static ApplicationContext getApplicationContextBySelf(String path) {
		applicationContext = new ClassPathXmlApplicationContext(
				path);
		// 配置xml文件
		if (applicationContext != null) {
			return applicationContext;
		}
		return null;
	}
}
