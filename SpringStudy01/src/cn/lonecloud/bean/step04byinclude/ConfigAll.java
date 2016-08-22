package cn.lonecloud.bean.step04byinclude;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/**
 * 将所有的configuration进行集合
 * @Title: ConfigAll.java
 * @Package cn.lonecloud.bean.step04byinclude
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 下午3:21:00
 */
@Configuration//configuration注解
@Import({ConfigCall.class,ConfigMail.class})//用于导入相对应的配置文件如果多个使用{}将其包裹
public class ConfigAll {

}
