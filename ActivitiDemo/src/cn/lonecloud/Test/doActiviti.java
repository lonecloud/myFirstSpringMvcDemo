package cn.lonecloud.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
/**
 * 初级的一些都在这里
 * @Title: doActiviti.java
 * @Package cn.lonecloud.Test
 * @Description: 
 * @author lonecloud
 * @date 2016年8月21日 下午9:34:46
 */
public class doActiviti {
	// 获取引擎
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();

	// private TaskService taskService;
	// private RepositoryService repositoryService;
	// private HistoryService historyService;
	// private RuntimeService runtimeService;

	/**
	 * 创建工作流
	 * 
	 * @Description:
	 */
	@Test
	public void createWorker() {
		// 获取builder对象
		DeploymentBuilder deploymentBuilder = pe.getRepositoryService()
				.createDeployment();
		// 设置工作流的名字
		deploymentBuilder.name("测试2");
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

	@Test
	public void createWorkerByZip() throws FileNotFoundException {
		// 获取builder对象
		DeploymentBuilder deploymentBuilder = pe.getRepositoryService()
				.createDeployment();
		// 设置工作流的名字
		deploymentBuilder.name("测试2");
		// 加载资源
		deploymentBuilder
				.addZipInputStream(new ZipInputStream(
						new FileInputStream(
								"/Users/lonecloud/Project/SpringStudy/Test/src/cn/lonecloud/diagram/test03.zip")));
		// 将该对象进行部署并返回部署对象
		Deployment deployment = deploymentBuilder.deploy();
		// 获取该对象的名字
		String name = deployment.getName();
		System.out.println(name);
	}

	/**
	 * 查询工作流
	 * 
	 * @Description: 查询act_re_deployment;
	 */
	@Test
	public void queryDeployment() {
		List<Deployment> list = pe.getRepositoryService()
				.createDeploymentQuery().list();
		for (Deployment deployment : list) {
			System.out.println(deployment.getId() + ":" + deployment.getName());
		}
	}

	/**
	 * 查询流程定义数据
	 * 
	 * @Description:查询act_re_procdef表中的东西
	 */
	@Test
	public void queryProcessDefinition() {
		// 获取service对象
		RepositoryService repositoryService = pe.getRepositoryService();
		// 创建查询对象
		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery();
		// 查询该对象的列表
		List<ProcessDefinition> list = processDefinitionQuery.list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println(processDefinition.getId() + ":"
					+ processDefinition.getName() + ":"
					+ processDefinition.getVersion());
		}
	}

	/**
	 * 将某个流程表输出
	 * 
	 * @throws IOException
	 * @Description:act_ge_bytearray 不推荐使用不能获取到最新的版本
	 */
	@Test
	public void querybyteArrayByProcessDefineId() throws IOException {
		// 获取service对象
		RepositoryService repositoryService = pe.getRepositoryService();
		// 创建查询对象
		String processDefineId = "myProcess:1:4";
		InputStream inputStream = repositoryService
				.getProcessDiagram(processDefineId);
		FileUtils.copyInputStreamToFile(inputStream, new File(
				"/Users/lonecloud/test.png"));
	}
	/**
	 * 获取最新的digram的png图片
	 * @Description: 
	 * @throws IOException
	 */
	@Test
	public void toInputStream() throws IOException {
		// 获取service对象
		RepositoryService repositoryService = pe.getRepositoryService();
		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
		deploymentQuery.deploymentName("测试4");//获取这个名字的设置
		deploymentQuery.orderByDeploymenTime().desc();//倒序排序
		List<Deployment> list = deploymentQuery.list();
		for (Deployment deployment : list) {
			System.out.println(deployment.getId());
		}
		Deployment deployment = list.get(0);//获取最新
		String id = deployment.getId();//获取deployment的id
		String fileName=null;
		InputStream inputStream =null;
		List<String> resourceNames = repositoryService.getDeploymentResourceNames(id);
		for (String resourceName : resourceNames) {
			if (resourceName.contains(".png")) {
				fileName=resourceName.substring(resourceName.lastIndexOf("/")+1);
				inputStream= repositoryService.getResourceAsStream(id, resourceName);						
				FileUtils.copyInputStreamToFile(inputStream, new File("/Users/lonecloud/",fileName));
			}
		}
		
	}

	/**
	 * 删除部署信息
	 * 
	 * @Description:根据id删除
	 */
	@Test
	public void deleteDeployment() {
		RepositoryService repositoryService = pe.getRepositoryService();
		String deploymentId = "301";
		boolean cascade = false;//  是否级联删除
		repositoryService.deleteDeployment(deploymentId);
		repositoryService.deleteDeployment(deploymentId, cascade);// 级联删除
	}

	/**
	 * 获取该工作流id的并且执行
	 * 
	 * @Description:不利于维护
	 */
	@Test
	public void startInstanceById() {
		// 获取运行时服务层
		RuntimeService runtimeService = pe.getRuntimeService();
		String processDefinitionId = "myProcess:1:4";
		// 启动该工作流后返回一个工作实例
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinitionId);
		System.out.println(processInstance.getId());
	}

	/**
	 * 获取该工作流key的并且执行
	 * 
	 * @Description:推荐使用act_re_procdef中的key 可以获取到最新的版本的工作流
	 */
	@Test
	public void startInstanceByKey() {
		// 获取运行时服务层
		RuntimeService runtimeService = pe.getRuntimeService();
		String processDefinitionKey = "myProcess";
		// 启动该工作流后返回一个工作实例
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey);
		System.out.println(processInstance.getId());
	}

	/**
	 *  查询流程实例
	 * 
	 * @Description:
	 */
	@Test
	public void queryNewInstance() {
		RuntimeService runtimeService = pe.getRuntimeService();
		ProcessInstanceQuery createProcessInstanceQuery = runtimeService
				.createProcessInstanceQuery();
		List<ProcessInstance> list = createProcessInstanceQuery.list();
		for (ProcessInstance processInstance : list) {
			System.out.println(processInstance.getId() + ":"
					+ processInstance.getBusinessKey());
		}
	}

	/**
	 * 删除某个流程实例
	 * 
	 * @Description: processInstanceId 是在act_ru_execution里面的id
	 */
	@Test
	public void deleteInstance() {
		RuntimeService runtimeService = pe.getRuntimeService();
		String processInstanceId = "401";
		String reason = "驳回。。。";
		runtimeService.deleteProcessInstance(processInstanceId, reason);
	}

	/**
	 * 查询个人任务 
	 * 
	 * @Description:数据表 act_ru_task
	 */
	@Test
	public void queryTask() {
		// 获取任务服务层
		TaskService taskService = pe.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		// taskQuery.taskAssignee("设置某个主要负责人");
		List<Task> list = taskQuery.list();
		for (Task task : list) {
			System.out.println(task.getId() + ":" + task.getName());

		}
	}

	/**
	 * 办理个人业务 操作数据表：act_ru_task、act_ru_execution 
	 * 
	 * @Description:
	 */
	@Test
	public void doTask() {
		// 获取任务服务层
		TaskService taskService = pe.getTaskService();
		taskService.complete("504");
	}

	/**
	 * 用于查询实例历史记录
	 * 
	 * @Description: 查询表：act_hi_procinst
	 */
	@Test
	public void queryHistoryInstance() {
		// 获取历史数据服务层
		HistoryService historyService = pe.getHistoryService();
		HistoricProcessInstanceQuery processInstanceQuery = historyService
				.createHistoricProcessInstanceQuery();
		List<HistoricProcessInstance> list = processInstanceQuery.list();
		for (HistoricProcessInstance historicProcessInstance : list) {
			System.out.println(historicProcessInstance.getId() + ":"
					+ historicProcessInstance.getDeleteReason() + ":"
					+ historicProcessInstance.getStartTime());
		}
	}

	/**
	 * 查询任务信息
	 * 
	 * @Description: 表：act_hi_taskinst
	 */
	@Test
	public void queryHistoryTask() {
		HistoryService historyService = pe.getHistoryService();
		HistoricTaskInstanceQuery taskInstanceQuery = historyService
				.createHistoricTaskInstanceQuery();
		taskInstanceQuery.orderByHistoricTaskInstanceStartTime().desc();
		List<HistoricTaskInstance> list = taskInstanceQuery.list();
		for (HistoricTaskInstance historicTaskInstance : list) {
			System.out.println(historicTaskInstance.getId() + ":"
					+ historicTaskInstance.getDeleteReason() + ":"
					+ historicTaskInstance.getName());
		}
	}

	/**
	 * 设置流程变量用于注释和一些对应的操作和信息的存储
	 * 
	 * @Description: ACT_RU_VARIABLE
	 * 
	 */
	@Test
	public void setGetvarible() {
		TaskService taskService = pe.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.processDefinitionKey("myProcess");// 设置筛选条件
		Task task = taskQuery.singleResult();// 获取单独result
		taskService.setVariable(task.getId(), "请假vd", "vvdcs");// 设置值
		taskService.getVariable(task.getId(), "请假vd");// 获取值
		System.out.println("-----------");
		Map<String, Object> variables = taskService.getVariables(task.getId());
		Set<String> keySet = variables.keySet();
		for (String string : keySet) {
			System.out
					.println("key" + string + "value" + variables.get(string));
		}
		// taskService.complete(task.getId());
	}

	@Test
	public void showPicture() throws IOException {
		RepositoryService repositoryService = pe.getRepositoryService();
		String procDefId = "myProcess:2:104";
		ProcessDefinition procDef = repositoryService
				.createProcessDefinitionQuery().processDefinitionId(procDefId)
				.singleResult();
		String diagramResourceName = procDef.getDiagramResourceName();
		InputStream imageStream = repositoryService.getResourceAsStream(
				procDef.getDeploymentId(), diagramResourceName);
		FileUtils.copyInputStreamToFile(imageStream, new File(
				"/Users/lonecloud/test.png"));
	}
	//
	// @Test
	// /**
	// * 得到带有高亮节点的流程图
	// * @param processInstanceId 流程实例id
	// * @return
	// */
	// public byte[] traceProcessImage(String processInstanceId) {
	// taskService = pe.getTaskService();
	// repositoryService = pe.getRepositoryService();
	// String taskId = taskService.createTaskQuery()
	// .processInstanceId(processInstanceId).singleResult().getId();
	// if (StringUtils.isBlank(taskId))
	// throw new IllegalArgumentException("任务ID不能为空！");
	// // 当前任务节点
	// Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	// if (task == null)
	// throw new IllegalArgumentException("任务不存在！");
	//
	// BpmnModel bpmnModel = repositoryService.getBpmnModel(task
	// .getProcessDefinitionId());
	// // List<String> activeActivityIds =
	// // runtimeService.getActiveActivityIds(task.getExecutionId());
	//
	// // 必须添加此行才能取到配置文件中的字体，待根本解决问题后删除
	// // Context.setProcessEngineConfiguration(processEngineConfiguration);
	// // return ProcessDiagramGenerator.generateDiagram(bpmnModel, "PNG",
	// // activeActivityIds);
	//
	// // 经过的节点
	// List<String> activeActivityIds = new ArrayList<>();
	// List<String> finishedActiveActivityIds = new ArrayList<>();
	// historyService = pe.getHistoryService();
	// // 已执行完的任务节点
	// List<HistoricActivityInstance> finishedInstances = historyService
	// .createHistoricActivityInstanceQuery()
	// .processInstanceId(task.getProcessInstanceId()).finished()
	// .list();
	// for (HistoricActivityInstance hai : finishedInstances) {
	// finishedActiveActivityIds.add(hai.getActivityId());
	// }
	// runtimeService = pe.getRuntimeService();
	// // 已完成的节点+当前节点
	// activeActivityIds.addAll(finishedActiveActivityIds);
	// activeActivityIds.addAll(runtimeService.getActiveActivityIds(task
	// .getProcessInstanceId()));
	//
	// // 经过的流
	// ProcessDefinitionEntity processDefinitionEntity =
	// (ProcessDefinitionEntity) repositoryService
	// .getProcessDefinition(task.getProcessDefinitionId());
	// List<String> highLightedFlows = new ArrayList<>();
	// getHighLightedFlows(processDefinitionEntity.getActivities(),
	// highLightedFlows, activeActivityIds);
	//
	// ProcessDiagramGenerator pdg = processEngineConfiguration
	// .getProcessDiagramGenerator();
	// InputStream inputStream = pdg.generateDiagram(bpmnModel, "PNG",
	// finishedActiveActivityIds, highLightedFlows,
	// processEngineConfiguration.getProcessEngineConfiguration()
	// .getActivityFontName(), processEngineConfiguration
	// .getProcessEngineConfiguration().getLabelFontName(),
	// processEngineConfiguration.getProcessEngineConfiguration()
	// .getProcessEngineConfiguration().getClassLoader(), 1.0);
	// try {
	// return IOUtils.toByteArray(inputStream);
	// } catch (Exception e) {
	// throw new RuntimeException("生成流程图异常！", e);
	// } finally {
	// IOUtils.closeQuietly(inputStream);
	// }
	// }
	//
	// /**
	// *
	// * 递归查询经过的流
	// */
	// private void getHighLightedFlows(List<ActivityImpl> activityList,
	// List<String> highLightedFlows,
	// List<String> historicActivityInstanceList) {
	// for (ActivityImpl activity : activityList) {
	// if (activity.getProperty("type").equals("subProcess")) {
	// // get flows for the subProcess
	// getHighLightedFlows(activity.getActivities(), highLightedFlows,
	// historicActivityInstanceList);
	// }
	//
	// if (historicActivityInstanceList.contains(activity.getId())) {
	// List<PvmTransition> pvmTransitionList = activity
	// .getOutgoingTransitions();
	// for (PvmTransition pvmTransition : pvmTransitionList) {
	// String destinationFlowId = pvmTransition.getDestination()
	// .getId();
	// if (historicActivityInstanceList
	// .contains(destinationFlowId)) {
	// highLightedFlows.add(pvmTransition.getId());
	// }
	// }
	// }
	// }
	// }
}
