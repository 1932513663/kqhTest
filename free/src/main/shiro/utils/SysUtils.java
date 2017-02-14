package shiro.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import shiro.security.Principal;

/**
 * 
 * @ClassName: SysUtils
 * @Title: SysUtils.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月10日 下午2:35:17
 */
public class SysUtils {
	
	/**
     * 取得当前登录用户对象
     * @return
     */
	public static Principal getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal)subject.getPrincipal();
        return principal;
    }
	
	public static String getLoalHostIp() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress();
		} catch (UnknownHostException e) {
		}
		return "未识别";
	}
	
	public static String getHostIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}
	
	/**
	 * 取得服务器IP
	 * @return
	 */
	public static String getRealLocalHostIp() {
		Enumeration<NetworkInterface> netInterfaces = null;  
		try {  
		    netInterfaces = NetworkInterface.getNetworkInterfaces();  
		    while (netInterfaces.hasMoreElements()) {  
		        NetworkInterface ni = netInterfaces.nextElement();  
		        if (ni.isVirtual()) {
		        	continue;
		        }
		        Enumeration<InetAddress> ips = ni.getInetAddresses();  
		        while (ips.hasMoreElements()) {
		        	InetAddress inetAddress = ips.nextElement();
		        	if (!inetAddress.isLinkLocalAddress()
		        			&& inetAddress.isSiteLocalAddress()) {
		        		if (ni.getDisplayName() != null && 
		        				ni.getDisplayName().toLowerCase().indexOf("virtual") >= 0) {
		        			continue;
		        		}
		        		return inetAddress.getHostAddress();
		        	}
		        }
		    }
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return "未识别";
	}
	
	/**
	 * 监测数据库是否正常
	 * @return
	 */
	public static String getMonitorDBStr(){
		try {
			/*SysCompanyIService sysCompanyIService=(SysCompanyIService)SpringContextHolder.getBean("sysCompanyIService");
			List<SysCompany> list = sysCompanyIService.findAllSysCompany();*/
			/*if(list!=null && list.size()>0){
				return "MonitorDBOK";
			}*/
		} catch (Exception e) {
			return "dbError";
		}
		return "dbError";
	}
}
