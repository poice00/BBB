package com.mb.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.mb.domain.PageBean;
import com.mb.util.QueryHelper;

@SuppressWarnings("unchecked")

@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	protected SessionFactory sessionFactory;

	private Class<T> clazz;

	public DaoSupportImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	public void update(T entity) {
		getSession().update(entity);

	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession()
					.createQuery(//
							"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	public List<T> findAll() {

		return getSession()
				.createQuery(//
						"FROM " + clazz.getSimpleName())//
				.list();
	}

	public void delete(String string) {
		Object obj = getById(string);
		if (obj != null) {
			getSession().delete(obj);
		}
		
	}
	public T getById(String id) {
		if (id.equals("")) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}
	public PageBean getPageBean(int currentPage, int pageSize, QueryHelper queryHelper) {
		List<Object> parameters = queryHelper.getParameters();
		//查询总记录
		Query query = getSession().createQuery(queryHelper.getCountQueryHql());
		if(parameters!=null){
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		Long recordCount = (Long) query.uniqueResult();
		
		//查询本页的数据列表
		Query quertList = getSession().createQuery(queryHelper.getListQueryHql());
		if(parameters !=null){
			for (int i = 0; i < parameters.size(); i++) {
				quertList.setParameter(i, parameters.get(i));
			}
		}
		quertList.setFirstResult((currentPage-1)*pageSize);//
		quertList.setMaxResults(pageSize);//
		List recordList = quertList.list();
		
		return new PageBean(pageSize, currentPage, recordCount.intValue(),recordList) ;
	}
}