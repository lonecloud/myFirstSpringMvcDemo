package cn.lonecloud.bean.step03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置configuration类进行载入相当于配置xml文档一样的
 * @Title: ConfigClass.java
 * @Package cn.lonecloud.bean.step03
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 下午12:39:49
 */
//使用这个注解说明这是一个注解的类
@Configuration
public class ConfigClass {
	@Bean(name="hello")
	public IBean getIBeanImp(){
		//下面对这个实例的实现
		return new IBeanImp();
	}
	@Bean(name="hello2")
	public IBean getIBeanImp2(){
		//下面对这个实例的实现
		return new IBeanImp2();
	}
}
