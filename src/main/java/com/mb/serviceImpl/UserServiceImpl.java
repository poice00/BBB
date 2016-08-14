package com.mb.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mb.base.DaoSupportImpl;
import com.mb.domain.Blog;
import com.mb.domain.User;
import com.mb.service.BlogService;
import com.mb.service.UserService;



@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {
	
}
