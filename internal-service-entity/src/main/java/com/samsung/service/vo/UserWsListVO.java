package com.samsung.service.vo;

import java.util.List;

public class UserWsListVO {

	private String actionMessage;	
	private String searchMessage;
	private int pagesCount;	
	private long totalList;	
	private List<UserWsVO> list;
	
	public String getSearchMessage() {
		return searchMessage;
	}
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	public String getActionMessage() {
		return actionMessage;
	}
	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}
	public int getPagesCount() {
		return pagesCount;
	}
	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}
	public List<UserWsVO> getList() {
		return list;
	}
	public void setList(List<UserWsVO> list) {
		this.list = list;
	}
	public long getTotalList() {
		return totalList;
	}
	public void setTotalList(long totalList) {
		this.totalList = totalList;
	}		
}