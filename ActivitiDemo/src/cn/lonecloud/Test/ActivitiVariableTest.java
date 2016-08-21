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
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import cn.lonecloud.User;
/**
 * 用于设置值
 * @Title: ActivitiVariableTest.java
 * @Package cn.lonecloud.Test
 * @Description: 
 * @author lonecloud
 * @date 2016年8月21日 下午9:34:00
 */
public class ActivitiVariableTest {
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
		deploymentBuilder.name("测试3");
		// 加载资源
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/test01.bpmn");
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/test01.png");
		// 将该对象进行部署并返回部署对象
		Deployment deployment = deploymentBuilder.deploy();
		// 获取该对象的名字
		String name = deployment.getName();
		System.out.println(name);
	}
	/**
	 * 启动线程
	 */
	@Test
	public void startProcess(){
		runtimeService.startProcessInstanceByKey("myProcess");
		
	}
	/**
	 * 全局变量是对该实例的所有的task有效
	 * 并且相同的会被覆盖
	 */
	/**
	 * 在运行时候设置并且在全局有效
	 */
	@Test
	public void setGrobelVarInRunTime(){
		Execution execution = runtimeService.createExecutionQuery().processDefinitionKey("myProcess").list().get(1);
		runtimeService.setVariable(execution.getId(), "test", "我是运行时存进来的");
	}
	/**
	 * 在启动时候设置并且在全局有效
	 */
	@Test
	public void setGrobelVarInStart(){
		HashMap<String,Object> variabies=new HashMap<>();
		variabies.put("123", "cscds");
		runtimeService.startProcessInstanceByKey("myProcess",variabies);
	}
	/**
	 * task中加全局变量
	 */
	@Test
	public void setGrobelVarInTask(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processDefinitionKey("myProcess");
		Task task = taskQuery.list().get(2);
		String taskId = task.getId();
		Map<String, Object> variables=new HashMap<>();
		variables.put("456", "123");
		taskService.complete(taskId, variables);
	}
	/**
	 * task中加局部变量
	 * 只能自己查看
	 */
	@Test
	public void setLocalVarInTask(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processDefinitionKey("myProcess");
		Task task = taskQuery.list().get(2);
		String taskId = task.getId();
		Map<String, Object> variables=new HashMap<>();
		variables.put("456", "123");
		taskService.setVariablesLocal(taskId, variables);//设置局部变量
	}
	/**
	 * 设置object 必须实现序列化id
	 * @Description:
	 */
	@Test
	public void setVisable(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processDefinitionKey("myProcess03");
		Task task = taskQuery.list().get(2);
		String taskId = task.getId();
		Map<String, Object> variables=new HashMap<>();
		User user=new User();
		user.setId(taskId);
		user.setName("cdscs");
		variables.put("456", user);
		taskService.setVariablesLocal(taskId, variables);//设置局部变量
	}
	/**
	 * 获取值
	 * @Description:
	 */
	@Test
	public void getVisable(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processDefinitionKey("myProcess03");
		Task task = taskQuery.list().get(2);
		String taskId = task.getId();
		Object object = taskService.getVariableLocal(taskId, "456");
		if (object instanceof User) {
			User u=(User) object;
			System.out.println(u.toString());
		}
	}
}
