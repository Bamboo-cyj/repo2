package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.entity.Result;
import com.fengk.pojo.CheckItem;
import com.fengk.pojo.HealthOrderTest;
import com.fengk.service.HealthCheckService;
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
@RequestMapping(value = "/health")
public class HealthCheckController {
    @Reference
    HealthCheckService healthCheckService;
    @RequestMapping(value = "/save")
    public String save(HealthOrderTest healthOrderTest){
        int savesuccess = healthCheckService.save(healthOrderTest);
        return savesuccess+"";
    }
    @RequestMapping(value = "/findAll")
    public String findAll(){
        System.out.println("进入了control");
        return healthCheckService.findAll().toString();
    }
    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
try {
    PageResult pageResult= healthCheckService.findPage(queryPageBean);
    return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
} catch(Exception e) {
    e.printStackTrace();
    return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

}
    }
    @RequestMapping(value = "/add")

    public Result add(@RequestBody CheckItem checkItem){
        try {
            healthCheckService.add(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);

        }
    }
    @RequestMapping(value = "/delete")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')")
    public Result delete(Integer id){
        try {
            healthCheckService.delete(id);
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
            CheckItem checkItem=healthCheckService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
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

    public Result edit(@RequestBody CheckItem checkItem){
        try {
           healthCheckService.edit(checkItem);
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

    @RequestMapping(value = "/findAllItems")
    public Result findAllItems(){
        try {
            List<CheckItem> list=healthCheckService.findAllItems();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }
}

