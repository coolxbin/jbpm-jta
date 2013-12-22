package com.huangzb.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;

import com.huangzb.test.lang.util.JbpmUtil;

/**
 * Servlet implementation class StartProcess
 */
public class StartProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StartProcess() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		start(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		start(request, response);
	}

	public void start(HttpServletRequest request, HttpServletResponse response){
		System.out.println("start");
		RuntimeManager runtime = (RuntimeManager) JbpmUtil.get(JbpmUtil.RUNTIME_MANAGER);
		RuntimeEngine engine = runtime.getRuntimeEngine(EmptyContext.get());
		KieSession ksession = engine.getKieSession();
		
		ProcessInstance pi = ksession.startProcess("com.huangzb.test.sample");
		
		System.out.println("流程已经启动，当前状态" + pi.getState());	
		
		TaskService ts = engine.getTaskService();
		
		List<TaskSummary> tasks = ts.getTasksAssignedAsPotentialOwner("mary", "en-UK");
		
		System.out.println("获取到mary的任务数量为:" + tasks.size());
	}
	
}
