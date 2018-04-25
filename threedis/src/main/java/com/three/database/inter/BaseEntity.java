package com.three.database.inter;

import java.io.Serializable;

public interface BaseEntity extends Serializable{
	public Long getId();
	public void setId(Long id);
	
	public String getCharId();
	public void setCharId(String charId);
	
}
