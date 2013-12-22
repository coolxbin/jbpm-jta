package com.huangzb.test.servlet.listener;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jbpm.runtime.manager.impl.RuntimeEnvironmentBuilder;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.RuntimeEnvironment;
import org.kie.internal.task.api.UserGroupCallback;

import com.huangzb.test.lang.util.JbpmUtil;

public class JbpmInitialization implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("程序退出/n");
		destroyJbpm();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("程序初始化/n");
		InitJbpm();
	}

	public void InitJbpm() {
		UserGroupCallback userGroupCallback = setUpUsers();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("org.jbpm.persistence.jpa");

		RuntimeEnvironment environment = RuntimeEnvironmentBuilder
				.getDefault()
				.persistence(true)
				.entityManagerFactory(emf)
				.userGroupCallback(userGroupCallback)
				.addAsset(ResourceFactory.newClassPathResource("sample.bpmn2"),
						ResourceType.BPMN2)
				.get();
		RuntimeManager runtimeManager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(
				environment);
		
		JbpmUtil.add(JbpmUtil.ENTITY_MANAGER_FACTORY, emf);
		JbpmUtil.add(JbpmUtil.RUNTIME_MANAGER, runtimeManager);
		JbpmUtil.add(JbpmUtil.RUNTIME_ENVIRONMENT, environment);
	}

	public void destroyJbpm(){
		((EntityManagerFactory)JbpmUtil.get(JbpmUtil.ENTITY_MANAGER_FACTORY)).close();
		((RuntimeManager)JbpmUtil.get(JbpmUtil.RUNTIME_MANAGER)).close();
		((RuntimeEnvironment)JbpmUtil.get(JbpmUtil.RUNTIME_ENVIRONMENT)).close();
	}
	
	public UserGroupCallback setUpUsers() {
		Properties properties = new Properties();
		properties.setProperty("krisv", "");
		properties.setProperty("mary", "");
		properties.setProperty("john", "");
		UserGroupCallback userGroupCallback = new JBossUserGroupCallbackImpl(
				properties);

		return userGroupCallback;
	}
}
