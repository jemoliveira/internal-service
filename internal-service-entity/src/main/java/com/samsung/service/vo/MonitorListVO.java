package com.samsung.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.samsung.service.model.BatLogBean;

public class MonitorListVO {
    private int pagesCount;
    private long total;

    private String actionMessage;
    private String searchMessage;

    private List<BatLogBean> batLogs;
    private List<MonitorVO> listAllTrans = new ArrayList<MonitorVO>();
    private List<MonitorVO> listAllTrans2 = new ArrayList<MonitorVO>();
    private List<MonitorVO> listSaw = new ArrayList<MonitorVO>();
    private List<MonitorVO> listSaw2 = new ArrayList<MonitorVO>();
    private List<MonitorVO> listTms = new ArrayList<MonitorVO>();
    private List<MonitorVO> listTms2 = new ArrayList<MonitorVO>();
    
    
    private List<MonitorVO> listMonitor;
    private List<MonitorVO> listMonitor2 = new ArrayList<MonitorVO>();
        
    public MonitorListVO() {
    }

    public MonitorListVO(int pages, long total, List<BatLogBean> batLogs, 
    	List<MonitorVO> listMonitor, List<MonitorVO> listAllTrans, List<MonitorVO> listSaw, List<MonitorVO> listTms) {
    	
        this.pagesCount = pages;
        this.batLogs = batLogs;
        this.total = total;
        this.listMonitor = listMonitor;
        this.listAllTrans = listAllTrans;
        this.listSaw = listSaw;
        this.listTms = listTms;
    }
   
	public List<MonitorVO> getListAllTrans() {
		return listAllTrans;
	}

	public void setListAllTrans(List<MonitorVO> listAllTrans) {
		this.listAllTrans = listAllTrans;
	}

	public List<MonitorVO> getListSaw() {
		return listSaw;
	}

	public void setListSaw(List<MonitorVO> listSaw) {
		this.listSaw = listSaw;
	}

	public List<MonitorVO> getListTms() {
		return listTms;
	}

	public void setListTms(List<MonitorVO> listTms) {
		this.listTms = listTms;
	}

	public List<MonitorVO> getListMonitor() {
		return listMonitor;
	}

	public void setListMonitor(List<MonitorVO> listMonitor) {
		this.listMonitor = listMonitor;
	}

	public List<BatLogBean> getBatLogs() {
		return batLogs;
	}

	public void setBatLogs(List<BatLogBean> batLogs) {
		this.batLogs = batLogs;
	}

	public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }

	public List<MonitorVO> getListSaw2() {
		return listSaw2;
	}

	public void setListSaw2(List<MonitorVO> listSaw2) {
		this.listSaw2 = listSaw2;
	}

	public List<MonitorVO> getListAllTrans2() {
		return listAllTrans2;
	}

	public void setListAllTrans2(List<MonitorVO> listAllTrans2) {
		this.listAllTrans2 = listAllTrans2;
	}

	public List<MonitorVO> getListTms2() {
		return listTms2;
	}

	public void setListTms2(List<MonitorVO> listTms2) {
		this.listTms2 = listTms2;
	}

	public List<MonitorVO> getListMonitor2() {
		return listMonitor2;
	}

	public void setListMonitor2(List<MonitorVO> listMonitor2) {
		this.listMonitor2 = listMonitor2;
	}
}