package cn.lonecloud.bean.step02;
/**
 * 用于继承Ibean后的一个实现类
 * @Title: EnglishBean.java
 * @Package cn.lonecloud.bean.step02
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 上午11:47:34
 */
public class EnglishBean implements Ibean {

	@Override
	public void say() {
		System.out.println("I say English");
	}

}
