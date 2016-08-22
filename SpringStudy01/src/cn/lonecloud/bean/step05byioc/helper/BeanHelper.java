package cn.lonecloud.bean.step05byioc.helper;

import cn.lonecloud.bean.step05byioc.Ibean;

/**
 * 用于对获得Bean的一个辅助类
 * @Title: BeanHelper.java
 * @Package cn.lonecloud.bean.step02.helper
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 上午11:48:27
 */
public class BeanHelper {
	private Ibean bean;
	
	//用于构造函数进行依赖注入
	public BeanHelper(Ibean bean) {
		System.out.println("用于构造函数进行依赖注入");
		this.bean = bean;
	}

	//对这个生成相对于的getset方法
	public Ibean getBean() {
		System.out.println("对这个生成相对于的getset方法");
		return bean;
	}

	public void setBean(Ibean bean) {
		this.bean = bean;
	}
	
}
