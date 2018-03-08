package com.xinlin.app.util.platform.springlog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xinlin.app.entity.vo.LoginUser;
import com.xinlin.app.util.JCDFDateUtil;
import com.xinlin.app.util.JCDFWebUtil;
import com.xinlin.app.util.StaticVar;


@Component(value="jcdfLogMgr")
public class JcdfLogMgr {

	//@Autowired  
	//private  HttpServletRequest request;

	
	@Resource(name = "jcdfLogServiceAop")
	private JcdfLogServiceAop jcdfLogServiceAop;

	public void commitLog(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (!StaticVar.SYS_LOG_ENABLED.equals(request
				.getAttribute(StaticVar.SYS_LOG_ENABLED_KEY))) {
			return;
		}
		
		String logModuleName = request
				.getAttribute(StaticVar.SYS_LOG_MODULE_KEY) == null ? null
				: (String) request.getAttribute(StaticVar.SYS_LOG_MODULE_KEY);
		//日志主体内容
		String logContextString = (String)request.getAttribute(StaticVar.SYS_LOG_KEY);
		
		//打印日期时间戳
		JCDFDateUtil.printlnSysTime();
		
		//@Before("pointCut()")
		//public void before(JoinPoint jp) {
		//jp.getStaticPart().
		// 获取目标类名
		
		String className = jp.getTarget().getClass().toString();
		className = className.substring(className.indexOf("com"));
		// 获取目标方法签名
		String signature = jp.getSignature().toString();
		// 获取方法名
		String methodName = signature.substring(signature.lastIndexOf(".") + 1,signature.indexOf("("));
		String operation = className+"."+methodName;
		String operator = null;
		LoginUser sessionUser = ((LoginUser)request.getSession().getAttribute(StaticVar.LOGIN_USER_KEY));
		operator = "admin";
		if(!"login".equalsIgnoreCase(methodName) &&  sessionUser != null){
			operator = sessionUser.getUserId() + StaticVar.SEPARATOR + sessionUser.getUserName();
		}
		//jp.getArgs();
		String operatorIP = JCDFWebUtil.getIP(request);
		
		JcdfLogEvent logEvent = new JcdfLogEvent(this);
		
		logEvent.setLoginName(operator);
		logEvent.setOperatoContent(logContextString);//logModuleName
		logEvent.setModuleName(logModuleName);
		
		logEvent.setLogIp(operatorIP);
		logEvent.setLogPara4(logModuleName);
		logEvent.setLogPara3(operation);//operation
		jcdfLogServiceAop.commitLog(logEvent);
	}

	public void beginLog(JoinPoint joinpoint) {
		JcdfLogEvent logEvent = new JcdfLogEvent(this);
		//jcdfLogServiceAop.commitLog(logEvent);
	}

	public void logException(Throwable ex) {
		JcdfLogEvent logEvent = new JcdfLogEvent(this);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		// 打印日期时间戳
		JCDFDateUtil.printlnSysTime();

		String logModuleName = request
				.getAttribute(StaticVar.SYS_LOG_MODULE_KEY) == null ? null
				: (String) request.getAttribute(StaticVar.SYS_LOG_MODULE_KEY);
		// 日志主体内容
		String logContextString = (String) request
				.getAttribute(StaticVar.SYS_LOG_KEY);

		// @Before("pointCut()")
		// public void before(JoinPoint jp) {
		// jp.getStaticPart().
		// 获取目标类名

		LoginUser sessionUser = ((LoginUser) request.getSession().getAttribute(
				StaticVar.LOGIN_USER_KEY));
		String operator = "admin";
		if (sessionUser != null) {
			operator = sessionUser.getUserId() + "_"
					+ sessionUser.getUserName();
		}
		// jp.getArgs();
		String operatorIP = JCDFWebUtil.getIP(request);

		logEvent.setLoginName(operator);
		logEvent.setOperatoContent(logContextString);
		logEvent.setModuleName(logModuleName);
		logEvent.setLogIp(operatorIP);
		// logEvent.setLogPara4(logModuleName);

		logEvent.setEx(ex);
		jcdfLogServiceAop.logException(logEvent);
	}

}
