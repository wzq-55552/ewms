package com.soft.one.ewms.business.permission.controller;

import com.soft.one.ewms.business.permission.service.*;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.commons.utils.MapperUtils;
import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import com.soft.one.ewms.domain.*;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Title：权限controller
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/1/8
 */
@RestController
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
    @GetMapping("/get/acc/nos")
    public ResponseResult<List<LmsWarehouseAcc>>getAccNo(){
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
    @GetMapping("/get/operation/roles")
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
    @PostMapping("/add/operation/role")
    public ResponseResult<OperationRole> addOperationRole(@RequestBody OperationRole operationRole) {
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
    @PostMapping("/update/operation/role")
    public ResponseResult<OperationRole> updateOperationRole(@RequestBody OperationRole operationRole) {
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
    @PostMapping("/delete/operation/role")
    public ResponseResult<OperationRole> deleteOperationRole(@RequestBody OperationRole operationRole) {
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
    @GetMapping("/get/operation/range")
    public ResponseResult<List<String>> getOperationRange(@RequestBody OperationRange operationRange) {
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
     * 更新操作角色拥有的操作权限
     *
     * @param source,target
     * @return
     */

    //暂时不可用
    @PostMapping("/update/operation/range")
    public ResponseResult<OperationRange> updateOperationRange(@RequestBody OperationRange source, OperationRange target) {
        ResponseResult<OperationRange> result = new ResponseResult<>();
        //数据库中没有找到，不能更新
        if (operationRangeService.selectById(source) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("更新失败");
        } else {
            operationRangeService.update(target);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("更新成功");
            result.setData(target);
        }
        return result;


    }

    /**
     * 新增操作角色拥有的操作权限
     *
     * @param operationRange
     * @return
     */
    @PostMapping("/add/operation/range")
    public ResponseResult<OperationRange> addOperationRange(@RequestBody OperationRange operationRange) {
        ResponseResult<OperationRange> result = new ResponseResult<>();
        Response response = OkHttpClientUtil.getInstance().getData("http://localhost:8081/api/acc/all");
        List<LmsWarehouseAcc> jsonList = null;
        try {
            //解析json
            String jsonString = response.body().string();//json格式数据
            jsonList = MapperUtils.json2listByTree(jsonString, "data", LmsWarehouseAcc.class);
            ;//找到“data”解析解析成List
        } catch (Exception e) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败，获取失败AccNo失败");
            return result;
        }
        //操作权限并不存在，非法新增
        if (!jsonList.contains(new LmsWarehouseAcc(operationRange.getAccNo(), null))) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败，该操作权限不存在");
            return result;
        }
        //操作角色并不存在,非法新增
        if (operationRoleService.selectById(new OperationRole(operationRange.getRoidId(), "")) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败,该操作角色不存在");
            return result;
        }
        //数据库已有该记录
        if (operationRangeService.selectById(operationRange) != null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败,数据库中已经存在");
            return result;
        } else {
            operationRangeService.insert(operationRange);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setData(operationRange);
            result.setMessage("新增成功");
        }
        return result;
    }

    /**
     * 删除操作角色拥有的操作权限
     *
     * @param operationRange
     * @return
     */
    @PostMapping("/delete/operation/range")
    public ResponseResult<OperationRange> deleteOperationRange(@RequestBody OperationRange operationRange) {
        ResponseResult<OperationRange> result = new ResponseResult<>();
        if (operationRangeService.selectById(operationRange) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败，数据库无此记录");
        } else {
            operationRangeService.delete(operationRange);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("删除成功");
            result.setData(operationRange);
        }
        return result;
    }

    /**
     * 获取所有的功能菜单
     */
    @GetMapping("/get/function/menus")
    public ResponseResult<List<FunctionMenu>> getFunctionMenu() {
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
    @PostMapping("/update/function/menu")
    public ResponseResult<FunctionMenu> updateFunctionMenu(@RequestBody FunctionMenu functionMenu) {
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
    @PostMapping("/add/function/menu")
    public ResponseResult<FunctionMenu> addFunctionMenu(@RequestBody FunctionMenu functionMenu) {
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
    @PostMapping("/delete/function/menu")
    public ResponseResult<FunctionMenu> deleteFunctionMenu(@RequestBody FunctionMenu functionMenu) {
        ResponseResult<FunctionMenu> result = new ResponseResult<>();


        if (functionMenuService.selectById(functionMenu) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败，id不存在");
        }
        //检查functionRange是否有该功能角色，有则不允许删除
        else if (functionRangeService.selectFrIdByExample(new FunctionRange(null, functionMenu.getFId())).size() != 0) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败,请先删除被分配的功能");
            return result;
        } else {
            functionMenuService.delete(functionMenu);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("删除成功");
            result.setData(functionMenu);
        }

        return result;
    }

    /**
     * 获取所有的功能角色
     * @return
     */
    @GetMapping("/get/function/roles")
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
    @PostMapping("/add/function/role")
    public ResponseResult<FunctionRole> addFunctionRole(@RequestBody FunctionRole functionRole) {
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
    @PostMapping("/update/function/role")
    public ResponseResult<FunctionRole> updateFunctionRole(@RequestBody FunctionRole functionRole) {
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
    @PostMapping("/delete/function/role")
    public ResponseResult<FunctionRole> deleteFunctionRole(@RequestBody FunctionRole functionRole) {
        ResponseResult<FunctionRole>result = new ResponseResult<>();

        if (functionRoleService.selectById(functionRole) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败,记录不存在");
            return result;
        }
        //检查functionRange是否有该功能角色，有则不允许删除
        else if (functionRangeService.selectFIdByExample(new FunctionRange(functionRole.getFrId(), null)).size() != 0) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("删除失败,请先删除该功能角色拥有的功能");
            return result;
        } else {
            functionRoleService.delete(functionRole);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("删除成功");
            result.setData(functionRole);
        }
        return result;
    }

    /**
     * 根据功能角色Id获取其拥有权限的功能列表
     * @param functionRange
     * @return ResponseResult<List < String>>
     */
    @GetMapping("/get/function/range")
    public ResponseResult<List<String>> getFunctionRange(@RequestBody FunctionRange functionRange) {
        ResponseResult result = new ResponseResult();
        String frId = functionRange.getFrId();
        //如果传进来的功能角色Id为空,不合法
        if(StringUtils.isEmpty(frId)){
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("功能角色id不能为空");
        } else {
            functionRange.setFId(null);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setData(functionRangeService.selectFIdByExample(functionRange));
            result.setMessage("获取成功");
        }
        return result;
    }

    /**
     * 新增功能角色的功能
     *
     * @param functionRange
     * @return
     */
    @PostMapping("/add/function/range")
    public ResponseResult<FunctionRange> addFunctionRange(@RequestBody FunctionRange functionRange) {
        ResponseResult<FunctionRange> result = new ResponseResult<>();
        if (functionRoleService.selectById(new FunctionRole(functionRange.getFrId(), null)) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败，该功能角色不存在");
            return result;
        } else if (functionMenuService.selectById(new FunctionMenu(functionRange.getFId(), null, null)) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败，该功能菜单不存在");
            return result;
        } else if (functionRangeService.selectById(functionRange) != null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败，该记录已存在");
            return result;
        } else {
            functionRangeService.insert(functionRange);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("新增成功");
            result.setData(functionRange);
        }
        return result;
    }

    /**
     * 删除功能角色的功能
     * @param functionRange
     * @return
     */
    @PostMapping("/delete/function/range")
    public ResponseResult<FunctionRange> deleteFunctionRange(@RequestBody FunctionRange functionRange) {
        ResponseResult<FunctionRange> result = new ResponseResult<>();
        if (functionRangeService.selectById(functionRange) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("新增失败，该记录不存在");
        } else {
            functionRangeService.delete(functionRange);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("新增成功");
            result.setData(functionRange);
        }
        return result;
    }


    //不可用
    /**
     * 修改功能角色的功能
     *
     * @param functionRange
     * @return
     */
    @PostMapping("/update/function/range")
    public ResponseResult<FunctionRange> updateFunctionRange(@RequestBody FunctionRange functionRange) {
        ResponseResult<FunctionRange> result = new ResponseResult<>();
        if (functionRangeService.selectById(functionRange) == null) {
            result.setCode(ResponseResult.CodeStatus.FAIL);
            result.setMessage("更新失败，该记录不存在");
        } else {
            functionRangeService.update(functionRange);
            result.setCode(ResponseResult.CodeStatus.OK);
            result.setMessage("修改成功");
            result.setData(functionRange);
        }
        return result;
    }
}
