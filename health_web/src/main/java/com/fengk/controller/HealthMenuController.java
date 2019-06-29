package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.entity.Result;
import com.fengk.pojo.CheckItem;
import com.fengk.pojo.Menu;
import com.fengk.service.HealthMenuService;
import com.fengk.utils.MessageConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.awt.SystemColor.menu;

@RestController
@RequestMapping("/menu")
public class HealthMenuController {

    @Reference
    private HealthMenuService healthMenuService;

    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = healthMenuService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);        }
    }

    @RequestMapping(value = "/add")
    public Result add(@RequestBody Menu menu){
        try {
            healthMenuService.add(menu);
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
    public Result delete(Integer id){
        try {
            healthMenuService.delete(id);
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


    @RequestMapping(value = "/edit")
    public Result edit(@RequestBody Menu menu){
        try {
            healthMenuService.edit(menu);
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

    @RequestMapping(value = "/findById")
    public Result findById(Integer id){
        try {
            Menu menu=healthMenuService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,menu);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        List<Menu> menuList = healthMenuService.findAll();
        if(menuList != null && menuList.size() > 0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
            result.setData(menuList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
    }
}
