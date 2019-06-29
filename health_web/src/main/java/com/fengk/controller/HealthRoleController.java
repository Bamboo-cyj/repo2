package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.entity.Result;
import com.fengk.pojo.*;
import com.fengk.service.HealthRoleService;
import com.fengk.utils.MessageConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class HealthRoleController {
    @Reference
    private HealthRoleService healthRoleService;

    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = healthRoleService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);        }
    }

    @RequestMapping(value = "/delete")
    public Result delete(Integer id){
        try {
            healthRoleService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);

        }
    }

    @RequestMapping(value = "/findById")
    public Result findById(Integer id){
        try {
            Role role=healthRoleService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,role);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }

    @RequestMapping(value = "/getPermissionRoleById")
    public Result getPermissionRoleById(Integer id){

        try {
            List<Integer> PermissionIds=healthRoleService.getPermissionRoleById(id);

            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,PermissionIds);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }

    @RequestMapping(value = "/getMenuRoleById")
    public Result getMenuRoleById(Integer id){

        try {
            List<Integer> MenuIds=healthRoleService.getMenuRoleById(id);

            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,MenuIds);
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
    public Result edit(@RequestBody Role role,Integer[] permissionIds,Integer[] menuIds){
        try {
            healthRoleService.edit(role,permissionIds,menuIds);
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

    @RequestMapping(value = "/add")
    public Result add(@RequestBody Role role,Integer[] permissionIds,Integer[] menuIds){
        try {
            healthRoleService.add(role,permissionIds,menuIds);

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
}
