package com.three.database.dbutils.page;

import java.util.List;

public class Pager {
	
	private int curPage;  
	private int beginIndex;  
    private int itemsPerPage;
    private int items;
    private List<?> list;
    
    
    
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}  
    
    
    
    
    
}
