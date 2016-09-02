package com.mb.serviceImpl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.service.BlogService;
import com.mb.service.ResultCenterService;



@Service
@Transactional
public class ResultCenterServiceImpl extends DaoSupportImpl<ResultCenter> implements ResultCenterService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultCenter> getByValue() {
		return getSession().createQuery(//
				"From ResultCenter r Order By r.value DESC")//
				.list();
	}
	
}
