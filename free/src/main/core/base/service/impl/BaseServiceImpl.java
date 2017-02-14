package core.base.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import core.base.service.BaseIService;
import tk.mybatis.mapper.common.Mapper;

@Service
public abstract class BaseServiceImpl<T> implements BaseIService<T> {
	
	public final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	public Mapper<T> mapper;

	@Override
	public int update(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public T findById(Object id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> findByEntity(T entity) {
		return mapper.select(entity);
	}

	@Override
	public List<T> findAll() {
		return mapper.selectAll();
	}

	@Override
	public int deleteById(Object id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<T> findPage(int pageNum, int pageSize,T entity) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = findByEntity(entity);
		PageInfo<T> page = new PageInfo<>(list);
		return page;
	}
}
