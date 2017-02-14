package core.base.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

public interface BaseIService<T> {
	/*int save(T entity);*/

	public int update(T entity);

	public T findById(Object id);

	public List<T> findByEntity(T entity);

	public List<T> findAll();

	public int deleteById(Object id);
	
	public PageInfo<T> findPage(int pageNum, int pageSize,T entity);

}
