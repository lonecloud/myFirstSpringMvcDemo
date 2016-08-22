package cn.lonecloud.Test;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * 
 * @Title: ActivitiTEmmByGroup.java
 * @Package cn.lonecloud.Test
 * @Description:
 * @author lonecloud
 * @date 2016年8月21日 下午10:47:13
 */
public class ActivitiTEmmByGroup {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	// 获取builder对象
	RepositoryService repositoryService = processEngine.getRepositoryService();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	TaskService taskService = processEngine.getTaskService();

	/**
	 * 创建工作流
	 * 
	 * @Description:
	 */
	@Test
	public void createWorker() {

		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment();
		// 设置工作流的名字
		deploymentBuilder.name("测试4");
		// 加载资源
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/myProcess.bpmn");
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/myProcess.png");
		// 将该对象进行部署并返回部署对象
		Deployment deployment = deploymentBuilder.deploy();
		// 获取该对象的名字
		String name = deployment.getName();
		System.out.println(name);
	}

	@Test
	public void startActiviti() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", "group1");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("TeamByGroup", map);
		System.out.println(processInstance.getId());
	}
	@Test
	public void createTeam() {
		IdentityService identityService = processEngine.getIdentityService();//认证：保存组和用户信息
		identityService.saveGroup(new GroupEntity("部门经理"));//建立组
		identityService.saveGroup(new GroupEntity("总经理"));//建立组
		identityService.saveUser(new UserEntity("小张"));//建立用户
		identityService.saveUser(new UserEntity("小李")); //建立用户
		identityService.saveUser(new UserEntity("小王")); //建立用户
		identityService.createMembership("小张", "部门经理");//建立组和用户关系
		identityService.createMembership("小李", "部门经理");//建立组和用户关系
		identityService.createMembership("小王", "总经理");//建立组和用户关系
	}
}
