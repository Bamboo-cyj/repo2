package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.MemberManagerDao;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;

import com.fengk.pojo.Member;
import com.fengk.pojo.MemberGroup;
import com.fengk.service.MemberService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberManagerDao memberManagerDao;


    @Override
    public PageResult findPageGroup(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        String queryString = queryPageBean.getQueryString();
        if (queryString != null) {
            queryString = "%" + queryString + "%";
        }
        Page<Member> pageGroup = memberManagerDao.findPageByCondition(queryString);
        PageResult pageResultGroup = new PageResult(pageGroup.getTotal(), pageGroup.getResult());

        return pageResultGroup;

    }

    @Override
    public List<MemberGroup> findAllItems() {
        return memberManagerDao.findAllItems();
    }

    @Override
    public void edit(Member member, Integer[] checkitemIds) {
        memberManagerDao.edit(member);
        memberManagerDao.deleteGroupMemberById(member.getId());
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap();
                map.put("memberId", member.getId());
                map.put("member_groupId", checkitemId);
                memberManagerDao.addGroupMemberById(map);
            }
        }
    }

    @Override
    public void add(Member member, Integer[] checkitemIds) {
        memberManagerDao.add( member);

        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap();
                map.put("memberId",  member.getId());
                map.put("member_groupId", checkitemId);
                memberManagerDao.addGroupMemberById(map);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        memberManagerDao.deleteGroupMemberById(id);
        memberManagerDao.deletememberById(id);
    }

    @Override
    public List<Integer> getMemberGroupItemById(Integer id) {
        return memberManagerDao.getMemberGroupItemById(id);
    }

    @Override
    public Member getMemberById(Integer id) {
        return memberManagerDao.getMemberById(id);
    }
}
