package com.mb.domain;

import java.util.List;

/**
 * 分页功能中一页的信息
 * @author ssy
 *
 */
public class PageBean {
	
	// 指定或是页面参数
	private int pageSize; // 每页显示多少条
	private int currentPage; // 当前页

	// 查询数据库
	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列表
	
	// 计算
	private int pageCount; // 总页数
	private int beginPageIndex; // 页码列表开始索引
	private int endPageIndex; // 页码列表结束索引
	
	/**
	 * 只接受前4个属性的值 计算出后三个属性
	 * @param pageSize
	 * @param currentPage
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int pageSize, int currentPage, int recordCount,List recordList) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.recordCount = recordCount;
		this.recordList = recordList;
		
		//计算总页码
		pageCount = (recordCount + pageSize - 1) / pageSize ;
		
		// 计算开始索引和结束索引
		//总页数不多于10页，则全部显示
		if(pageCount<=10){
			beginPageIndex = 1;
			endPageIndex =pageCount;
		}
		//总页数大于10页，则显示附近的10个页码
		else{
		//前4+当前页+后5
			beginPageIndex = currentPage-4;
			endPageIndex = currentPage +5;
			
			//当前页码不足4个，显示前10	
			if(beginPageIndex<4){
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			//当前页码不足5个，显示后10	
			if(endPageIndex>pageCount){
				beginPageIndex = pageCount - 10 +1;
				endPageIndex = pageCount;
			}
		
		}
	}
	
	
	
	
	
	//=============================================
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public List getRecordList() {
		return recordList;
	}
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}
