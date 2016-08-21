package cn.lonecloud;

import java.io.Serializable;
/**
 * 这是一个User类
 * @Title: User.java
 * @Package cn.lonecloud
 * @Description: 
 * @author lonecloud
 * @date 2016年8月21日 下午8:07:43
 */
public class User implements Serializable{
	
	
	
	 /**
	 * @Fields serialVersionUID : 必须添加这个序列化id
	 */
	private static final long serialVersionUID = -7301380756745363484L;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	private String id;
	 
	 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
