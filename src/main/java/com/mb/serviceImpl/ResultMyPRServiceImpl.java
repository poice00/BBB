package com.mb.serviceImpl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMyPR;
import com.mb.service.BlogService;
import com.mb.service.ResultCenterService;
import com.mb.service.ResultMyPRService;



@Service
@Transactional
public class ResultMyPRServiceImpl extends DaoSupportImpl<ResultMyPR> implements ResultMyPRService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultMyPR> getByValue() {
		return getSession().createQuery(//
				"From ResultMyPR r Order By r.value DESC")//
				.list();
	}
	
}
