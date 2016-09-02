package com.mb.serviceImpl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMyPR;
import com.mb.domain.ResultPR;
import com.mb.service.BlogService;
import com.mb.service.ResultCenterService;
import com.mb.service.ResultMyPRService;
import com.mb.service.ResultPRService;



@Service
@Transactional
public class ResultPRServiceImpl extends DaoSupportImpl<ResultPR> implements ResultPRService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultPR> getByValue() {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"From ResultPR r Order By r.value DESC")//
				.list();
	}
	
}
