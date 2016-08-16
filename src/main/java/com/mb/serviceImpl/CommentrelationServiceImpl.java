package com.mb.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.Commentrelation;
import com.mb.service.BlogService;
import com.mb.service.CommentrelationService;



@Service
@Transactional
public class CommentrelationServiceImpl extends DaoSupportImpl<Commentrelation> implements CommentrelationService {
	
}
