package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.Member;
import com.fengk.pojo.MemberGroup;

import java.util.List;

public interface MemberService {
    PageResult findPageGroup(QueryPageBean queryPageBean);

    List<MemberGroup> findAllItems();

    void edit(Member member, Integer[] checkitemIds);

    void add(Member member, Integer[] checkitemIds);

    void delete(Integer id);

    List<Integer> getMemberGroupItemById(Integer id);

    Member getMemberById(Integer id);
}
