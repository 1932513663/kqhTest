package cn.free.modules.web.manage.region;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.free.modules.service.region.RegionIService;
import core.base.web.BaseController;

@RequestMapping("/manage/region")
@Controller
public class RegionController extends BaseController {
	@Autowired
	private RegionIService regionIService;

	/**
	 * 
	 * @Title: getProviceJsons
	 * @Description: 获取省
	 * @param @param
	 *            response
	 * @return void
	 *
	 * @author kang
	 * @date 2017年1月18日 上午9:05:10
	 */
	@RequestMapping(value = "/getProviceJsons", method = RequestMethod.POST)
	@ResponseBody
	public String getProviceJsons(HttpServletResponse response) {
		logger.info("getProviceJsons");
		String res = regionIService.getProviceJsons();
		return res;
	}

	/**
	 * 
	 * @Title: getCityJsons
	 * @Description: 获取市
	 * @param @param
	 *            provinceId
	 * @param @param
	 *            response
	 * @return void
	 *
	 * @author kang
	 * @date 2017年1月18日 上午9:06:43
	 */
	@RequestMapping(value = "/getCityJsons", method = RequestMethod.POST)
	@ResponseBody
	public String getCityJsons(String provinceId, HttpServletResponse response) {
		logger.info("getCityJsons");
		String res = regionIService.getCityJsons(provinceId);
		return res;
	}

	/**
	 * 
	 * @Title: getCountyJsons
	 * @Description: 获取区
	 * @param @param
	 *            cityId
	 * @param @param
	 *            response
	 * @return void
	 *
	 * @author kang
	 * @date 2017年1月18日 上午9:07:53
	 */
	@RequestMapping(value = "/getCountyJsons", method = RequestMethod.POST)
	@ResponseBody
	public String getCountyJsons(String cityId, HttpServletResponse response) {
		logger.info("getCountyJsons");
		String res = regionIService.getCountyJsons(cityId);
		return res;
	}
}
