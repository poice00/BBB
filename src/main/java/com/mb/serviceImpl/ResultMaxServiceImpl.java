package com.mb.serviceImpl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMax;
import com.mb.service.BlogService;
import com.mb.service.ResultCenterService;
import com.mb.service.ResultMaxService;



@Service
@Transactional
public class ResultMaxServiceImpl extends DaoSupportImpl<ResultMax> implements ResultMaxService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultMax> getByValue() {
		return getSession().createQuery(//
				"From ResultMax r Order By r.value DESC")//
				.list();
	}
	
}
