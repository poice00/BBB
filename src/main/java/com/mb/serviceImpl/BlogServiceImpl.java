package com.mb.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.service.BlogService;



@Service
@Transactional
public class BlogServiceImpl extends DaoSupportImpl<Blog> implements BlogService {
	
}
