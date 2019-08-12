package io.renren.modules.job.task;

import io.renren.modules.test.service.StressTestFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 性能测试定时任务
 * 
 * stressTestTask为spring bean的名称
 * 
 * @author smooth
 * @email smooth@163.com
 * @date 2019年08月08日 下午1:34:24
 */
@Component("stressTestTask")
public class StressTestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StressTestFileService stressTestFileService;
	
	//压测定时任务的参数为文件ID；如果有多个参数，用逗号分隔@RequestBody 
	public void stressTest(String params){
		logger.info("我是性能压测的stressTest方法，正在被执行，参数为：" + params);
		
		try {
			String[] strParam = params.split(",");
			Long[] fileIds = new Long[strParam.length];
			for (int i = 0; i < strParam.length; i++) {
				fileIds[i] = Long.valueOf(strParam[i]);
			}
			stressTestFileService.run(fileIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
