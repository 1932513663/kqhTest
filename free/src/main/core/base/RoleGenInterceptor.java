package core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName: RoleGenInterceptor
 * @Title: RoleGenInterceptor.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0
 * @company 麦田
 * @date 2017年1月13日 上午10:57:24
 */
public class RoleGenInterceptor extends HandlerInterceptorAdapter {

	// private final Logger logger = Logger.getLogger(RoleGenInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestType = request.getHeader("X-Requested-With");
		HttpSession session = request.getSession();
		// // 临时处理
		//
		// FndUser user = (FndUser) session.getAttribute(Constant.LOGIN_USER);
		//
		// String path = request.getRequestURI();
		// String contextPath = request.getContextPath();
		// path = path.replace(contextPath, "");
		//
		// if (logger.isInfoEnabled()) {
		// logger.info(path);
		// }
		//
		// if (!path.endsWith("login") && user == null && path.indexOf("/code") < 0) {
		// if ("XMLHttpRequest".equals(requestType)) {// Ajax请求
		// try {
		// PrintWriter out = response.getWriter();
		// out.write("{\"status\":\"001\"}");
		// out.flush();
		// out.close();
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		// } else {
		// if ("/".equals(contextPath))
		// contextPath = "";
		// try {
		// response.sendRedirect(contextPath + "/login");
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// return false;
		// }
		//
		// if (user != null) {
		// String resourceCode = request.getParameter(Constant.RESOURCE_CODE);
		// if (StringUtils.isNotEmpty(resourceCode))
		// request.getSession().setAttribute(Constant.RESOURCE_CODE, resourceCode);
		// }
		return true;
	}
}
