package com.xinlin.app.util.platform.springlog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("jcdfLogEventListener")
public class JcdfLogEventListener implements ApplicationListener {

	private static Log log = LogFactory.getLog(JcdfLogEventListener.class);

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof JcdfLogEvent) {
			JcdfLogEvent firstLoginUserEvent = (JcdfLogEvent) event;
			log.debug("用户：" + firstLoginUserEvent.getLoginName() + "第一次登入");
		} else {
			log.debug("其它事件");
		}
	}

}
