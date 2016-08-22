package cn.lonecloud.Test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

public class ActivitiTeam {
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
		deploymentBuilder.name("组团测试");
		// 加载资源
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/team/Team.bpmn");
		deploymentBuilder
				.addClasspathResource("cn/lonecloud/diagram/team/Team.png");
		// 将该对象进行部署并返回部署对象
		Deployment deployment = deploymentBuilder.deploy();
		// 获取该对象的名字
		String name = deployment.getName();
		System.out.println(name);
	}
	@Test
	public void startMyTask(){
		runtimeService.startProcessInstanceByKey("myProcess");
	}
	@Test
	public void findMyTask(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.taskCandidateUser("xu");
		List<Task> list = taskQuery.list();
		for (Task task : list) {
			System.out.println(task.getAssignee());
		}
	}
}
