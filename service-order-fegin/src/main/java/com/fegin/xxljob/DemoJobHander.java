package com.fegin.xxljob;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value="demoJobHandler")
@Component
public class DemoJobHander extends IJobHandler {

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("XXL-JOB, Hello World.");

		for (int i = 0; i < 5; i++) {
			XxlJobLogger.log("beat at:" + i);
			TimeUnit.SECONDS.sleep(2);
		}
		return SUCCESS;
	}

}