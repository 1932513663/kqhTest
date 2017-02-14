package core.base.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
	/**
	 * 日志
	 */
	protected final Logger logger = Logger.getLogger(getClass());
	
	
	public HttpServletRequest getHttpServletRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * 
	 * @Title: setAttribute
	 * @Description: 暂时只支持request，session
	 * @param @param name
	 * @param @param type
	 * @param @param obj
	 * @return void
	 *
	 * @author kang
	 * @date 2017年2月13日 上午11:02:49
	 */
	public void setAttribute(String name,String type,Object obj){
		HttpServletRequest request = getHttpServletRequest();
		switch (type) {
		case "REQUEST":
			request.setAttribute(name, obj);
			break;
		case "SESSION":
			request.getSession().setAttribute(name, obj);
			break;
		}
	}
	
	/**
	 * 进行UTF-8解码
	 * @param str
	 * @return
	 */
	protected String decodeUtf8(String str){
		try {
			if (str != null) {
				return java.net.URLDecoder.decode(new String(str.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("解码[UTF-8]时发生异常：" + e);
		} catch(Exception ex){
			logger.error("解码[UTF-8]时发生异常：" + ex);
		}

		return str;
	}
}
