package com.fengk.dao;

import com.fengk.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    Page<CheckGroup> findPageByCondition(@Param("queryByString") String queryByString);

    void add(CheckGroup checkGroup);

    void addItemIdAndGroupId(Map<String, Integer> map);

    List<Integer> getCheckItemGroupById(Integer id);

    CheckGroup getCheckGroupById(Integer id);

    void edit(CheckGroup checkGroup);

    void deleteGroupItemIdById(Integer id);

    void deleteCheckGroupById(Integer id);

    List<CheckGroup> findAllGroup();
}
