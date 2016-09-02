package com.mb.service;

import java.util.List;

import com.mb.base.DaoSupport;
import com.mb.domain.Blog;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMax;
import com.mb.domain.ResultMyPR;

public interface ResultMyPRService extends DaoSupport<ResultMyPR> {

	List<ResultMyPR> getByValue();

}
