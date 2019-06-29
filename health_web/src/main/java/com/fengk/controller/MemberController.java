package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.entity.Result;


import com.fengk.pojo.CheckGroup;
import com.fengk.pojo.Member;
import com.fengk.pojo.MemberGroup;
import com.fengk.service.MemberService;
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
@RequestMapping(value = "member")
public class MemberController {
@Reference
MemberService memberService;

    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult= memberService.findPageGroup(queryPageBean);

            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
        } catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }

    @RequestMapping(value = "/findAllItems")
    public Result findAllItems(){
        try {
            List<MemberGroup> list=memberService.findAllItems();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,list);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);

        }
    }

    @RequestMapping(value = "/edit")

    public Result edit(@RequestBody Member member, Integer[] checkitemIds){
        try {
            memberService.edit(member,checkitemIds);
            return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_MEMBER_FAIL);

        }
    }
    @RequestMapping(value = "/add")

    public Result add(@RequestBody Member member,Integer[] checkitemIds){
        try {
            memberService.add(member,checkitemIds);

            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_MEMBER_FAIL);

        }
    }


    @RequestMapping(value = "/delete")

    public Result delete(Integer id){
        try {
            memberService.delete(id);
            return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_MEMBER_FAIL);

        }
    }

    @RequestMapping(value = "/getMemberGroupItemById")
    public Result getMemberGroupItemById(Integer id){

        try {
            List<Integer> checkItemIds=memberService.getMemberGroupItemById(id);

            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,checkItemIds);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);

        }
    }

    @RequestMapping(value = "/getMemberById")
    public Result getMemberById(Integer id){
        try {
            Member member=memberService.getMemberById(id);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,member);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);

        }
    }

}
