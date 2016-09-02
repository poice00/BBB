package com.mb.service;

import java.util.List;

import com.mb.base.DaoSupport;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMax;

public interface ResultMaxService extends DaoSupport<ResultMax> {

	List<ResultMax> getByValue();

}
