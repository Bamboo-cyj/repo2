package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.CheckGroupDao;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.CheckGroup;
import com.fengk.service.HealthCheckGroupService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service(loadbalance = "random",interfaceClass = HealthCheckGroupService.class)
public class HealthCheckGroupServiceImpl implements HealthCheckGroupService {

    @Autowired
    CheckGroupDao checkGroupDao;

    @Override
    public PageResult findPageGroup(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        String queryString = queryPageBean.getQueryString();
        if (queryString != null) {
            queryString = "%" + queryString + "%";
        }
        Page<CheckGroup> pageGroup = checkGroupDao.findPageByCondition(queryString);
        PageResult pageResultGroup = new PageResult(pageGroup.getTotal(), pageGroup.getResult());

        return pageResultGroup;
    }

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);

        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap();
                map.put("groupId", checkGroup.getId());
                map.put("itemId", checkitemId);
                checkGroupDao.addItemIdAndGroupId(map);
            }
        }
    }


    @Override
    public List<Integer> getCheckItemGroupById(Integer id) {


        return checkGroupDao.getCheckItemGroupById(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.edit(checkGroup);
        checkGroupDao.deleteGroupItemIdById(checkGroup.getId());
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap();
                map.put("groupId", checkGroup.getId());
                map.put("itemId", checkitemId);
                checkGroupDao.addItemIdAndGroupId(map);
            }
        }

    }

    @Override
    public CheckGroup getCheckGroupById(Integer id) {
        return checkGroupDao.getCheckGroupById(id);
    }

    @Override
    public void delete(Integer id) {
        checkGroupDao.deleteGroupItemIdById(id);
        checkGroupDao.deleteCheckGroupById(id);
    }

    @Override
    public List<CheckGroup> findAllGroup() {
        List<CheckGroup> allGroup = checkGroupDao.findAllGroup();

        System.out.println(allGroup+"service端");
        return allGroup;
    }


}
