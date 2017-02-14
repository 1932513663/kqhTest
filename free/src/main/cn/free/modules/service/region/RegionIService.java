package cn.free.modules.service.region;

import cn.free.modules.model.region.Region;
import core.base.service.BaseIService;

public interface RegionIService extends BaseIService<Region> {
	public String getProviceJsons();

	public String getCityJsons(String provinceId);

	public String getCountyJsons(String cityId);
}
