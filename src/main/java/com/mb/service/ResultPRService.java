package com.mb.service;

import java.util.List;

import com.mb.base.DaoSupport;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMax;
import com.mb.domain.ResultMyPR;
import com.mb.domain.ResultPR;

public interface ResultPRService extends DaoSupport<ResultPR> {

	List<ResultPR> getByValue();

}
