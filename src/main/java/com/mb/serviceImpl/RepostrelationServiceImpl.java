package com.mb.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.Repostrelation;
import com.mb.service.BlogService;
import com.mb.service.RepostrelationService;



@Service
@Transactional
public class RepostrelationServiceImpl extends DaoSupportImpl<Repostrelation> implements RepostrelationService {
	
}
