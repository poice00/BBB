package com.mb.service;

import java.util.List;

import com.mb.base.DaoSupport;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;

public interface ResultCenterService extends DaoSupport<ResultCenter> {

	List<ResultCenter> getByValue();

}
