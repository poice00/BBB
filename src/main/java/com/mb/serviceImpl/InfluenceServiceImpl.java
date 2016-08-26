package com.mb.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.Influence;
import com.mb.service.BlogService;
import com.mb.service.InfluenceService;



@Service
@Transactional
public class InfluenceServiceImpl extends DaoSupportImpl<Influence> implements InfluenceService {
	
}
