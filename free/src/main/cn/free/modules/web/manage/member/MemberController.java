package cn.free.modules.web.manage.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.free.modules.service.member.MemberService;

/**
 * 
 * @ClassName: MemberController
 * @Title: MemberController.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0
 * @company 麦田
 * @date 2017年1月12日 下午2:17:38
 */
@RequestMapping("/manage/member")
@Controller
public class MemberController {
	private static Logger logger = Logger.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		logger.info("方法：index");
		ModelAndView modelAndView = new ModelAndView("manage/main/main");
		memberService.findAll();
		return modelAndView;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public String findAll() {
		logger.info("方法：findAll");
		// ModelAndView modelAndView = new ModelAndView("index");
		memberService.findAll();
		return "1111";
	}
}
