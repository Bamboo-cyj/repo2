package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.entity.Result;

import com.fengk.pojo.Setmeal;
import com.fengk.service.HealthSetmealService;

import com.fengk.utils.MessageConstant;
import com.fengk.utils.QiniuUtils;
import com.fengk.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/**
 *
 */
@RestController
@RequestMapping(value = "/setmeal")
public class HealthSetmealController {
    @Reference
    HealthSetmealService healthSetmealService;




    @RequestMapping(value = "/upload")

    public Result upload(MultipartFile imgFile){
        try {
            String filename = imgFile.getOriginalFilename();
            filename= UploadUtils.getUUIDName(filename);
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);


            healthSetmealService.addWebRedis(filename);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,filename);

        } catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }

    }


    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
try {
    PageResult pageResult= healthSetmealService.findPage(queryPageBean);
    System.out.println(pageResult);
    return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, pageResult);
} catch(Exception e) {
    e.printStackTrace();
    return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

}
    }

    @RequestMapping(value = "/add")

    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            healthSetmealService.add(setmeal,checkgroupIds);
            healthSetmealService.addDatabaseRedis(setmeal.getImg());
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);

        }
    }
   /* @RequestMapping(value = "/delete")
    public Result delete(Integer id){
        try {
            healthSetmealService.delete(id);
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
            List<Integer> checkItemIds=healthSetmealService.getCheckItemGroupById(id);

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
           healthSetmealService.edit(checkGroup,checkitemIds);
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

    @RequestMapping(value = "/getCheckGroupById")
    public Result getCheckGroupById(Integer id){
        try {
            CheckGroup checkGroup=healthSetmealService.getCheckGroupById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }*/
}

