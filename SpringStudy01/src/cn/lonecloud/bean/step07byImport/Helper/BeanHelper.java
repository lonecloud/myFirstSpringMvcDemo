package cn.lonecloud.bean.step07byImport.Helper;

import cn.lonecloud.bean.step07byImport.Ibean;


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
	//对这个生成相对于的getset方法
	public Ibean getBean() {
		return bean;
	}

	public void setBean(Ibean bean) {
		this.bean = bean;
	}
	
}
