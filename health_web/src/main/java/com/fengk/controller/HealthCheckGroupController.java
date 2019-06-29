package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.entity.Result;
import com.fengk.pojo.CheckGroup;

import com.fengk.service.HealthCheckGroupService;

import com.fengk.utils.MessageConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/checkGroup")
public class HealthCheckGroupController {
    @Reference
    HealthCheckGroupService healthCheckGroupService;


    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
try {
    PageResult pageResult= healthCheckGroupService.findPageGroup(queryPageBean);

    return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
} catch(Exception e) {
    e.printStackTrace();
    return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

}
    }

    @RequestMapping(value = "/add")

    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            healthCheckGroupService.add(checkGroup,checkitemIds);

            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);

        }
    }
    @RequestMapping(value = "/delete")

    public Result delete(Integer id){
        try {
            healthCheckGroupService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);

        }
    }
    @RequestMapping(value = "/getCheckItemGroupById")
    public Result getCheckItemGroupById(Integer id){

        try {
            List<Integer> checkItemIds=healthCheckGroupService.getCheckItemGroupById(id);

            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }
    @RequestMapping(value = "/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
           healthCheckGroupService.edit(checkGroup,checkitemIds);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);

        }
    }

    @RequestMapping(value = "/findAllGroup")
    public Result findAllGroup(){
        try {
            List<CheckGroup> checkGroupList=healthCheckGroupService.findAllGroup();
            System.out.println(checkGroupList+"control端");
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }
    @RequestMapping(value = "/getCheckGroupById")
    public Result getCheckGroupById(Integer id){
        try {
            CheckGroup checkGroup=healthCheckGroupService.getCheckGroupById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }
}

