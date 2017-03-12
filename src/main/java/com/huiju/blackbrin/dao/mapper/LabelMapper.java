package com.huiju.blackbrin.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huiju.blackbrin.entity.Label;
import com.huiju.blackbrin.pojo.RetLabel;

/**
 * 标签操作数据库
 */
@Repository
public interface LabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);
    
    /**
     * 获取标签类别、标签、及其关系，用于接口返回
     */
    List<RetLabel> selectRetLabelList();
}