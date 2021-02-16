package com.sms.bean;

import java.util.List;

public class Pagnation<T> {
	private int totalCount;
	private int pageSize;
	private int currentPage;
//	private int totalPage;
	private List<T> data;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
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
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotalPage() {
		return getTotalCount() % getPageSize() == 0 ? getTotalCount() / getPageSize() : (getTotalCount() / getPageSize() + 1); 
	}
	
	public boolean hasPre() {
		return getCurrentPage() > 1;
	}
	
	public boolean hasNext() {
		return getCurrentPage() < getTotalPage();
	}
}
