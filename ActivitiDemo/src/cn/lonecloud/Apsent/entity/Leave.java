package cn.lonecloud.Apsent.entity;

import java.io.Serializable;

public class Leave implements Serializable{
	
	
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = -599849667439502557L;

	private String id;
	
	private String instanceId;
	
	private String userId;
	
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
