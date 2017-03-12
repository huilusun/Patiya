package com.huiju.blackbrin.pojo;

import java.io.Serializable;

/**
 * 类说明：用于封装数据库返回的结果集
 */
public class RetLabel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标签类型主键ID
	 */
	private int typeId;
	
	/**
	 * 类别名称
	 */
	private String typeName;
	
	/**
	 * 标签主键ID
	 */
	private int labelId;
	
	/**
	 * 标签名称
	 */
	private String name;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
