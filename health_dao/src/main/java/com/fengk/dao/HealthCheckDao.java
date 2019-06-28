package com.fengk.dao;



import com.fengk.pojo.CheckItem;
import com.fengk.pojo.HealthOrderTest;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface HealthCheckDao {
    int save(HealthOrderTest healthOrderTest);

    List<HealthOrderTest> findAll();

    Page<CheckItem> findPageByCondition(@Param("queryByString") String queryByString);

    void add(CheckItem checkItem);

    Integer findIdIsInCheckGroup(Integer id);

    void delete(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAllItems();
}
