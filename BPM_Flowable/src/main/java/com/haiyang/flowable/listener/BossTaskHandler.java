package com.haiyang.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class BossTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("老板审批...");
		delegateTask.setAssignee("老板");
	}
}
