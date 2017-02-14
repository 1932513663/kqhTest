package shiro.web.manage;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.base.web.BaseController;
import shiro.security.Principal;
import shiro.security.UsernamePasswordToken;
import shiro.utils.SysUtils;

@RequestMapping("/shiro")
@Controller
public class ShiroController extends BaseController{
	
	/**
	 * 直接（跳转）登陆
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/logins", method = RequestMethod.POST)
	public ModelAndView logins(@RequestParam(value = "u") String userName,
			@RequestParam(value = "p") String pwd,
			@RequestParam(value = "s") String sysNo,
			@RequestParam(value = "at") String accountType, 
			@RequestParam(value = "af") String accountFrom,
			@RequestParam(value = "afp") String accountFromPwd) {
		ModelAndView modelAndView=new ModelAndView("/manage/member/index");
		/** 1.解码用户名 **/
		userName=decodeUtf8(userName);
		accountFrom=decodeUtf8(accountFrom);
		
		/** 2.创建登录信息 **/
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(userName);
		token.setPassword(pwd.toCharArray());
		token.setLoginType("DirectLogin");

		/** 3.进行登陆 **/
		doLogin(token,userName,pwd,sysNo,accountType,accountFrom,accountFromPwd);

		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: toLogin
	 * @Description: 跳转登录页面
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月13日 下午3:36:19
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public ModelAndView toLogin() {
		logger.info("方法：toLogin");
		ModelAndView modelAndView = new ModelAndView("manage/main/login");
		return modelAndView;
	}
	/**
	 * 手动登陆当前系统
	 * @param userName
	 * @param pwd
	 * @param validateCode
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "username",required=false) String userName, 
						  @RequestParam(value = "password",required=false) String pwd,
						  @RequestParam(value = "s",required=false) String sysNo,
						  @RequestParam(value = "validateCode",required=false) String validateCode,
						  @RequestParam(value = "at",required=false) String accountType, 
						  @RequestParam(value = "af",required=false) String accountFrom,
						  @RequestParam(value = "afp",required=false) String accountFromPwd) {
		ModelAndView modelAndView=null;
		
		/** 2.创建登录信息 **/
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(userName);
		token.setPassword(pwd.toCharArray());
		token.setLoginType("DirectLogin");
		
		/** 3. 手动进行登陆 **/
		if(!doLogin(token,userName,pwd,sysNo,accountType,accountFrom,accountFromPwd)){
			this.setAttribute("flag", "SESSION", "ERROR_USER_OR_PWD");
			modelAndView=new ModelAndView("redirect:/shiro/toLogin");
			return modelAndView;
		}
		modelAndView=new ModelAndView("redirect:/manage/member/index");
		return modelAndView;
	}
	/**
	 * 进行登陆
	 * @param token
	 * @param userName
	 * @param pwd
	 * @param sysNo
	 * @param accountType
	 * @param accountFrom
	 */
	private boolean doLogin(UsernamePasswordToken token,String userName,String pwd,String sysNo,String accountType,String accountFrom,String accountFromPwd){
		boolean isException=true;
		Principal principal = SysUtils.getPrincipal();
		try {
			SecurityUtils.getSubject().login(token);
			principal = SysUtils.getPrincipal();
			// 配置帐号切换信息
			configAccountTypeInfo(principal, accountType, accountFrom,accountFromPwd);
		} catch (UnknownAccountException e) {
			isException = false;
			logger.error("没有找到此用户:" + e);
		}
		
		// 记录日志
		printLoginLog(isException, principal, userName, pwd, sysNo, accountType, accountFrom,accountFromPwd);
		
		return isException;
	}
	
	/**
	 * 配置帐号切换信息
	 * @param principal
	 * @param accountType
	 * @param accountFrom
	 */
	private void configAccountTypeInfo(Principal principal, String accountType, String accountFrom,String accountFromPwd) {
		if (principal == null) return;
		if (accountType != null && ("0".equals(accountType.trim()) || "1".equals(accountType.trim()))) {
			accountType = accountType.trim();
			principal.setAccountType(accountType);
			principal.setAccountFrom(accountFrom);
			principal.setAccountFromPwd(accountFromPwd);
		}
		this.setAttribute("ulName","SESSION",principal.getEncodeLoginName());
		this.setAttribute("userName","REQUEST", principal.getName());
		this.setAttribute("gb2312Ulname","SESSION", principal.getEncodeGb2312LoginName());
	}
	
	/**
	 * 登出系统
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		SecurityUtils.getSubject().logout();
		ModelAndView modelAndView=new ModelAndView("redirect:/shiro/toLogin");
		return modelAndView;
	}
	
	/**
	 * 记录用户切换信息
	 * @param isException
	 * @param principal
	 * @param userName
	 * @param pwd
	 * @param sysNo
	 * @param accountType
	 * @param accountFrom
	 */
	private void printLoginLog(boolean isException,Principal principal,String userName,String pwd,String sysNo,String accountType,String accountFrom,String accountFromPwd){
		if(isException){
			//logger.error("ex0 FromSysName:["+getSysName(sysNo)+"] sysNo:["+sysNo+"] userName:["+userName+"] pwd:["+pwd+"] accountType:["+accountType+"] accountFrom:["+accountFrom+"] accountFromPwd:["+accountFromPwd+"] userLoginName:["+principal.getLoginName()+"] userName:["+principal.getName()+"] oldUserId: ["+principal.getOldUserId()+"]  loginTime:["+DateUtil.customDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss")+"]");
		}else{
			//logger.error("ex1 FromSysName:["+getSysName(sysNo)+"] sysNo:["+sysNo+"] userName:["+userName+"] pwd:["+pwd+"] accountType:["+accountType+"] accountFrom:["+accountFrom+"] accountFromPwd:["+accountFromPwd+"] Time:["+DateUtil.customDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss")+"]");
		}
	}
}
