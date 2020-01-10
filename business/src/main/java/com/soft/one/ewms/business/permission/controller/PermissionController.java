package com.soft.one.ewms.business.permission.controller;

import com.soft.one.ewms.business.permission.service.*;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.commons.utils.MapperUtils;
import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import com.soft.one.ewms.domain.*;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Title：权限controller
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@Controller
public class PermissionController {

    @Autowired
    private FunctionMenuService functionMenuService;
    @Autowired
    private FunctionRoleService functionRoleService;
    @Autowired
    private FunctionRangeService functionRangeService;
    @Autowired
    private OperationRoleService operationRoleService;
    @Autowired
    private OperationRangeService operationRangeService;

    /**
     * 获取仓库号和仓库类型json数据
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllAccNo")
    public ResponseResult<List<LmsWarehouseAcc>>getAccNo(){
        System.out.println("111");
        //获取json,先启动api模块，不然没有数据
        ResponseResult<List<LmsWarehouseAcc>> result = new ResponseResult<>();
        Response response  = OkHttpClientUtil.getInstance().getData("http://localhost:8082/api/acc/all");
        try{
            //解析json
            String jsonString = response.body().string();//json格式数据
            List<LmsWarehouseAcc> jsonList = MapperUtils.json2listByTree(jsonString,"data",LmsWarehouseAcc.class);;//找到“data”解析解析成List
            result.setData(jsonList);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("获取成功");
        }catch (Exception e) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("获取失败");
        }
        return result;
    }

    /**
     * 获取所有的操作角色
     * @return
     */
    @ResponseBody
    @GetMapping("/getOperationRoles")
    public ResponseResult<List<OperationRole>> getOperationRoles(){
        ResponseResult<List<OperationRole>> result = new ResponseResult<>();
        result.setCode(ResponseResult.CodeStatus.OK);
        result.setMessage("获取成功");
        result.setData(operationRoleService.selectAll());
        return result;
    }

    /**
     * 新增操作资料角色
     * @param operationRole
     * @return
     */
    @ResponseBody
    @PostMapping("/addOperationRole")
    public ResponseResult<OperationRole>addOperationRole(OperationRole operationRole){
        ResponseResult<OperationRole> result = new ResponseResult<>();
        if(StringUtils.isEmpty(operationRole.getRoidId())){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("添加失败，id不能为空字符串");
        }
        //数据库已经存在此id的记录，不能新增
        else if(operationRoleService.selectById(operationRole)!=null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("添加失败，此id已经存在");
        }else{
            operationRoleService.insert(operationRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("添加成功");
            result.setData(operationRole);
        }
        return result;
    }

    /**
     * 更新操作资料角色
     * @param operationRole
     * @return
     */
    @ResponseBody
    @PostMapping("/updateOperationRole")
    public ResponseResult<OperationRole>updateOperationRole(OperationRole operationRole){
        ResponseResult<OperationRole> result = new ResponseResult<>();
        //如果传进的id不存在，不能修改
        if(operationRoleService.selectById(operationRole)==null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("更新失败，此id的记录不存在");
        }else{
            operationRoleService.update(operationRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("更新成功");
            result.setData(operationRole);
        }
        return result;
    }

    /**
     * 删除操作资料角色
     * @param operationRole
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteOperationRole")
    public ResponseResult<OperationRole>deleteOperationRole(OperationRole operationRole){
        ResponseResult<OperationRole> result = new ResponseResult<>();
        //如果传进的id不存在，不能删除
        if(operationRoleService.selectById(operationRole)==null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败，此id的记录不存在");
        }else{
            operationRoleService.delete(operationRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("删除成功");
            result.setData(operationRole);
        }
        return result;
    }

    /**
     * 获取操作资料角色所拥有的权限
     * @param operationRange
     * @return
     */
    @ResponseBody
    @GetMapping("/getAccNo")
    public ResponseResult<List<String>>getAccNoByOperationRange(OperationRange operationRange){
        ResponseResult<List<String>>result = new ResponseResult<>();
        if(operationRange.getRoidId()==null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("获取失败，操作角色id不能为空");
        }else{
            operationRange.setAccNo(null);
            result.setData(operationRangeService.selectByRoleId(operationRange));
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("获取成功");
        }
        return result;
    }



    /**
     * 获取所有的功能菜单
     */
    @ResponseBody
    @GetMapping("/getFunctionMenus")
    public ResponseResult<FunctionMenu> getFunctionMenu(){
        ResponseResult result = new ResponseResult();
        List<FunctionMenu> functionMenus = functionMenuService.selectAll();
        result.setCode(ResponseResult.CodeStatus.OK);
        result.setData(functionMenus);
        result.setMessage("获取成功");
        return result;
    }

    /**
     * 更新功能菜单
     * @param functionMenu
     * @return
     */
    @ResponseBody
    @PostMapping("/updateFunctionMenu")
    public ResponseResult<FunctionMenu>updateFunctionMenu(FunctionMenu functionMenu){
        ResponseResult<FunctionMenu> result = new ResponseResult<>();
        FunctionMenu temp = functionMenuService.selectById(functionMenu);
        if(temp==null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("更新失败，id不存在");
        }else{
            functionMenuService.update(functionMenu);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("更新成功");
            result.setData(functionMenu);
        }
        return result;
    }

    /**
     * 新增功能菜单
     * @param functionMenu
     * @return
     */
    @ResponseBody
    @PostMapping("/addFunctionMenu")
    public ResponseResult<FunctionMenu>addFunctionMenu(FunctionMenu functionMenu){
        ResponseResult<FunctionMenu> result = new ResponseResult<>();
        //传入的功能菜单id为空串，不合法
        if(StringUtils.isEmpty(functionMenu.getFId())){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败,要新增的id不能为空");
        }
        //数据已经存在该id的记录
        else if(functionMenuService.selectById(functionMenu)!=null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败,id已存在");
        }else{
            functionMenuService.insert(functionMenu);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("新增成功");
            result.setData(functionMenu);
        }
        return result;
    }

    /**
     * 删除功能菜单
     * @param functionMenu
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteFunctionMenu")
    public ResponseResult<FunctionMenu>deleteFunctionMenu(FunctionMenu functionMenu){
        ResponseResult<FunctionMenu> result = new ResponseResult<>();
        if(functionMenuService.selectById(functionMenu)!=null){
            functionMenuService.delete(functionMenu);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("删除成功");
            result.setData(functionMenu);
        }else{
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败，id不存在");
        }
        return result;
    }

    /**
     * 获取所有的功能角色
     * @return
     */
    @ResponseBody
    @GetMapping("/getFunctionRoles")
    public ResponseResult<List<FunctionRole>> getFunctionRoles(){
        ResponseResult<List<FunctionRole>> result = new ResponseResult<>();
        List<FunctionRole> functionRoles = functionRoleService.selectAll();
        result.setCode(ResponseResult.CodeStatus.OK);
        result.setData(functionRoles);
        result.setMessage("获取成功");
        return result;
    }

    /**
     * 新增功能角色
     * @param functionRole
     * @return
     */
    @ResponseBody
    @PostMapping("/addFunctionRole")
    public ResponseResult<FunctionRole>addFunctionRole(FunctionRole functionRole){
        ResponseResult<FunctionRole> result = new ResponseResult<>();
        //传入的功能角色id为空串，不合法
        if(StringUtils.isEmpty(functionRole.getFrId())){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败,要新增的id不能为空");
        }
        //数据已经存在该id的记录
        else if (functionRoleService.selectById(functionRole)!=null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败,id已经存在");
        }else{
            functionRoleService.insert(functionRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("新增成功");
            result.setData(functionRole);
        }
        return result;
    }

    /**
     * 更新功能角色
     * @param functionRole
     * @return
     */
    @ResponseBody
    @PostMapping("/updateFunctionRole")
    public ResponseResult<FunctionRole>updateFunctionRole(FunctionRole functionRole){
        ResponseResult<FunctionRole> result = new ResponseResult<>();
        if(functionRoleService.selectById(functionRole)==null){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("更新失败，id不存在");
        }else{
            functionRoleService.update(functionRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("更新成功");
            result.setData(functionRole);
        }
        return result;
    }

    /**
     * 删除功能角色
     * @param functionRole
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteFunctionRole")
    public ResponseResult<FunctionRole>deleteFunctionRole(FunctionRole functionRole){
        ResponseResult<FunctionRole>result = new ResponseResult<>();
        if(functionRoleService.selectById(functionRole)!=null){
            functionRoleService.delete(functionRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("删除成功");
            result.setData(functionRole);
        }else{
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败,id不存在");
        }
        return result;
    }

    /**
     * 根据功能角色Id获取其拥有权限的功能列表
     * @param functionRange
     * @return ResponseResult<List<String>>
     */
    @ResponseBody
    @GetMapping("/getFunctionRange")
    public ResponseResult<List<String>> getFunctionRange(FunctionRange functionRange){
        ResponseResult result = new ResponseResult();
        String frId = functionRange.getFrId();
        //如果传进来的功能角色Id为空,不合法
        if(StringUtils.isEmpty(frId)){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("功能角色id不能为空");
        }else {
            functionRange.setFId(null);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setData(functionRangeService.selectByFrId(functionRange));
            result.setMessage("获取成功");
        }
        return result;
    }
}
