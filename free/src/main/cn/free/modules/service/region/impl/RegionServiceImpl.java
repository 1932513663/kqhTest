package cn.free.modules.service.region.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.free.modules.dao.region.RegionMapper;
import cn.free.modules.model.region.Region;
import cn.free.modules.service.region.RegionIService;
import core.base.service.impl.BaseServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.JsonUtils;

@Service
public class RegionServiceImpl extends BaseServiceImpl<Region> implements RegionIService {
	@Autowired
	private RegionMapper regionMapper;

	@Override
	public String getProviceJsons() {
		JSONArray jar = new JSONArray();
		JSONObject j = null;
		Region region=new Region();
		region.setPartentId("100000");
		List<Region> list = regionMapper.select(region);
		for (Region shopRegion : list) {
			j = new JSONObject();
			j.accumulate("id", shopRegion.getCode());
			j.accumulate("name", shopRegion.getName());
			jar.add(j);
		}
		return JsonUtils.returnJsonSuccess(jar.toString());
	}
	
	@Override
	public String getCityJsons(String provinceId) {
		JSONArray jar = new JSONArray();
		JSONObject j = null;
		Region region=new Region();
		region.setPartentId(provinceId);
		List<Region> list = regionMapper.select(region);
		for (Region shopRegion : list) {
			j = new JSONObject();
			j.accumulate("id", shopRegion.getCode());
			j.accumulate("name", shopRegion.getName());
			
			jar.add(j);
		}
		return JsonUtils.returnJsonSuccess(jar.toString());
	}
	
	@Override
	public String getCountyJsons(String cityId) {
		JSONArray jar = new JSONArray();
		JSONObject j = null;
		Region region=new Region();
		region.setPartentId(cityId);
		List<Region> list = regionMapper.select(region);
		for (Region shopRegion : list) {
			j = new JSONObject();
			j.accumulate("id", shopRegion.getCode());
			j.accumulate("name", shopRegion.getName());
			jar.add(j);
		}
		return JsonUtils.returnJsonSuccess(jar.toString());
	}
}
