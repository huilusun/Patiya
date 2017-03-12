package com.huiju.blackbrin.entity;

import java.io.Serializable;

/**
 * 标签
 */
public class Label implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 标签所属分类
	 */
	private int labelTypeId;
	
	/**
	 * 排序字段
	 */
	private int sortIndex;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 更新时间
	 */
	private long updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLabelTypeId() {
		return labelTypeId;
	}

	public void setLabelTypeId(int labelTypeId) {
		this.labelTypeId = labelTypeId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}
	
}
