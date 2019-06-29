package com.fengk.dao;

import com.fengk.pojo.Member;
import com.fengk.pojo.MemberGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface MemberManagerDao {
    Page<Member> findPageByCondition(String queryString);

    List<MemberGroup> findAllItems();

    void edit(Member member);

    void deleteGroupMemberById(Integer id);

    void addGroupMemberById(Map<String, Integer> map);

    void add(Member member);

    void deletememberById(Integer id);

    List<Integer> getMemberGroupItemById(Integer id);

    Member getMemberById(Integer id);
}
