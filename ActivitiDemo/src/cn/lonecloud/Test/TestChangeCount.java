package cn.lonecloud.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

/**
 * 用于对测试对工作流设置的时候测试
 * 
 * @Title: TestChangeCount.java
 * @Package cn.lonecloud.Test
 * @Description:
 * @author lonecloud
 * @date 2016年8月21日 下午6:03:09
 */
public class TestChangeCount {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	// 获取builder对象
	RepositoryService repositoryService = processEngine.getRepositoryService();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	TaskService taskService = processEngine.getTaskService();
	private static String id;

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
		deploymentBuilder.name(new SimpleDateFormat("dd-hh-mm")
				.format(new Date()));
		// 加载资源
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/TreeeChange.bpmn");
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/TreeeChange.png");
		// 将该对象进行部署并返回部署对象
		Deployment deployment = deploymentBuilder.deploy();
		// 获取该对象的名字
		String name = deployment.getName();
		System.out.println(name);
		// 创建实例
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("ThreeChange");
		id = processInstance.getProcessDefinitionId();
		System.out.println(id);
		// testCount();
	}

	@Test
	public void testCount() {
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processDefinitionId("ThreeChange:1:7304");
		Task task = taskQuery.singleResult();
		Map<String, Object> variables = new HashMap<>();
		//传入对应的参数用于比较
		variables.put("count", "100000");
		taskService.complete(task.getId(), variables);
	}

}
