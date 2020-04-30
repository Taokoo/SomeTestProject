package com.taokoo.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import com.taokoo.util.MailUtil1;

public class BossTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("老板审批...");
		MailUtil1.sendMail("ocean@taokoo.com", "审批邮件", "老板已审批通过");
		delegateTask.setAssignee("老板");
	}
}
