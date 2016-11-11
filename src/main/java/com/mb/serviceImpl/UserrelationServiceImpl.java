package com.mb.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.Repostrelation;
import com.mb.domain.Userrelation;
import com.mb.service.BlogService;
import com.mb.service.RepostrelationService;
import com.mb.service.UserrelationService;



@Service
@Transactional
public class UserrelationServiceImpl extends DaoSupportImpl<Userrelation> implements UserrelationService {
	
}
