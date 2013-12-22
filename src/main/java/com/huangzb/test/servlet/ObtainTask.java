package com.huangzb.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;

import com.huangzb.test.lang.util.JbpmUtil;

/**
 * Servlet implementation class ObtainTask
 */
public class ObtainTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtainTask() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		obtainTask();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		obtainTask();
	}

	public void obtainTask(){
		RuntimeManager runtime = (RuntimeManager) JbpmUtil.get(JbpmUtil.RUNTIME_MANAGER);
		RuntimeEngine engine = runtime.getRuntimeEngine(EmptyContext.get());
		
		TaskService ts = engine.getTaskService();
		
		List<TaskSummary> tasks = ts.getTasksAssignedAsPotentialOwner("mary", "en-UK");
		
		System.out.println("获取到mary的任务数量为:" + tasks.size());
	}
}
