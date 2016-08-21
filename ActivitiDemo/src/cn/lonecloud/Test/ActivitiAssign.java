package cn.lonecloud.Test;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
/**
 * 用于动态设置主要负责人
 * @Title: ActivitiAssign.java
 * @Package cn.lonecloud.Test
 * @Description: 
 * @author lonecloud
 * @date 2016年8月21日 下午9:33:09
 */
public class ActivitiAssign {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	// 获取builder对象
	RepositoryService repositoryService = processEngine.getRepositoryService();
	RuntimeService runtimeService=processEngine.getRuntimeService();
	TaskService taskService=processEngine.getTaskService();
	/**
	 * 创建工作流
	 * 
	 * @Description:
	 */
	@Test
	public void createWorker() {

		DeploymentBuilder deploymentBuilder =repositoryService.createDeployment();
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
	/**
	 * 启动线程
	 * ${test}在化图的时候进行操作
	 * 这里可以进行动态设置某些属性
	 */
	@Test
	public void startProcess(){
		String test="1223";//启动的时候动态设置主要负责人
		Map<String,Object> variables=new HashMap<>();
		variables.put("test", test);
		runtimeService.startProcessInstanceByKey("myProcess", variables);
	}
	
}
