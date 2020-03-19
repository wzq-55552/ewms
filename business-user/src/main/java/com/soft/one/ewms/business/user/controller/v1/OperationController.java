package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.OperationRangeService;
import com.soft.one.ewms.business.user.service.OperationRoleService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.commons.utils.MapperUtils;
import com.soft.one.ewms.commons.utils.OkHttpClientUtil;
import com.soft.one.ewms.domain.dtos.user.OperationDto;
import com.soft.one.ewms.domain.pojos.api.LmsWarehouseAcc;
import com.soft.one.ewms.domain.pojos.user.OperationRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title：操作资料角色
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/19
 */
@RestController
@CrossOrigin
@Api(tags = "操作资料角色")
@RequestMapping("/operation")
public class OperationController {

    @Resource
    private OperationRoleService operationRoleService;

    @Resource
    private OperationRangeService operationRangeService;


    /**
     * 获取仓库号和仓库类型json数据，返回给前端
     * 旭日那边提供的数据，不是本地数据库
     * @return
     */
    @GetMapping("/get/acc/nos")
    @ApiOperation(value = "获取仓库号和仓库类型")
    @PreAuthorize("hasAnyAuthority('OperationGetAccnos','Operation')") // 默认管理员可以访问
    public ResponseResult<List<LmsWarehouseAcc>>getAccNo(){
        //获取json,先启动api模块，不然没有数据
        ResponseResult<List<LmsWarehouseAcc>> result = new ResponseResult<>();
        Response response  = OkHttpClientUtil.getInstance().getData("http://47.113.80.250:8090/api/acc/all");
        try{
            //解析json
            String jsonString = response.body().string();//json格式数据
            List<LmsWarehouseAcc> jsonList = MapperUtils.json2listByTree(jsonString,"data",LmsWarehouseAcc.class); //找到“data”节点解析成List
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
    @GetMapping("/get/role/all")
    @ApiOperation(value = "获取所有的操作角色")
    @PreAuthorize("hasAnyAuthority('OperationGetRoleAll','Operation')") // 默认管理员可以访问
    public ResponseResult<List<OperationRole>> getOperationRoles(){
        List<OperationRole> operationRoles = operationRoleService.selectAll();
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取成功", operationRoles);
    }

    /**
     * 获取该操作资料角色的仓库访问集合
     * @param roidIdMap
     * @return
     */
    @PostMapping("/get/role/range")
    @ApiOperation(value = "获取该操作资料角色的仓库访问集合")
    @PreAuthorize("hasAnyAuthority('OperationGetRoleRange','Operation')") // 默认管理员可以访问
    public ResponseResult<List<String>> getOperationRoleRange(@RequestBody Map<String, String> roidIdMap){
        List<String> accNos = new ArrayList<>();
        if (roidIdMap != null && !StringUtils.isBlank(roidIdMap.get("roidId"))){
            accNos = operationRangeService.selectByRoidId(roidIdMap.get("roidId"));
            return new ResponseResult<List<String>>(ResponseResult.CodeStatus.OK, "获取成功", accNos);
        }
        return new ResponseResult<List<String>>(ResponseResult.CodeStatus.FAIL, "获取失败", null);
    }

    /**
     * 新增操作资料角色，公司
     * 操作资料角色id、操作资料角色名，访问的仓库集合
     * @param operationDto
     * @return
     */
    @PostMapping("/insert/role")
    @ApiOperation(value = "新增操作资料角色，公司")
    @PreAuthorize("hasAnyAuthority('OperationInsertRole','Operation')") // 默认管理员可以访问，数据库有
    public ResponseResult<Void> InsertOperationRole(@RequestBody OperationDto operationDto) {
        if (operationDto != null && !StringUtils.isBlank(operationDto.getRoidId())
                && !StringUtils.isBlank(operationDto.getRoidType())){
            //数据库已经存在此id的记录，不能新增
            if (operationRoleService.selectByPrimaryKey(operationDto.getRoidId()) != null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该操作资料角色id已存在");
            }
            // 增加操作资料角色
            OperationRole operationRole = new OperationRole();
            operationRole.setRoidId(operationDto.getRoidId());
            operationRole.setRoidType(operationDto.getRoidType());
            int k = operationRoleService.insert(operationRole);

            // 增加仓库与角色的联系
            if (operationDto.getAccNos() != null){
                operationRangeService.insertList(operationDto.getRoidId(), operationDto.getAccNos());
            }
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加操作资料角色成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数不足");
    }

    /**
     * 更新操作资料角色
     * 看前端怎么设计再可能考虑拆分，一起或拆分
     * @param operationDto 集合有打勾的发来就行
     * @return
     */
    @PostMapping("/update/role")
    @ApiOperation(value = "更新操作资料角色")
    @PreAuthorize("hasAnyAuthority('OperationUpdateRole','Operation')") // 默认管理员可以访问
    public ResponseResult<Void> updateOperationRole(@RequestBody OperationDto operationDto) {
        if (operationDto != null && !StringUtils.isBlank(operationDto.getRoidId())){
            // 更新操作资料角色
            if (operationRoleService.selectByPrimaryKey(operationDto.getRoidId()) == null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该操作资料角色id不存在");
            }
            OperationRole operationRole = new OperationRole();
            operationRole.setRoidId(operationDto.getRoidId());
            if (!StringUtils.isBlank(operationDto.getRoidType())){
                operationRole.setRoidType(operationDto.getRoidType());
            }
            operationRoleService.updateByPrimaryKeySelective(operationRole);
            // 更新仓库权限
            // 有可能为空
            //if (operationDto.getAccNos() != null && operationDto.getAccNos().size() > 0){
                operationRangeService.deleteByRoidId(operationDto.getRoidId());
                if (operationDto.getAccNos() != null){
                    operationRangeService.insertList(operationDto.getRoidId(), operationDto.getAccNos());
                }
            //}
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "修改操作资料角色成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数不足");
    }

    /**
     * 删除操作资料角色
     * @param roidIdMap
     * @return
     */
    @PostMapping("/delete/role")
    @ApiOperation(value = "删除操作资料角色")
    @PreAuthorize("hasAnyAuthority('OperationDeleteRole','Operation')") // 默认管理员可以访问
    public ResponseResult<Void> deleteOperationRole(@RequestBody Map<String, String> roidIdMap) {
        if (roidIdMap != null && !StringUtils.isBlank(roidIdMap.get("roidId"))){
            // 先删除仓库权限联系
            operationRangeService.deleteByRoidId(roidIdMap.get("roidId"));
            // 删除操作资料角色
            int k = operationRoleService.deleteByPrimary(roidIdMap.get("roidId"));
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "删除成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数错误");
    }

    /**
     * 查询操作资料角色
     * 单角色名、id跟角色名，模糊搜索
     * @param operationRole id跟角色名
     * @return
     */
    @PostMapping("/select/role")
    @ApiOperation(value = "查询操作资料角色")
    @PreAuthorize("hasAnyAuthority('OperationSelectRole','Operation')") // 默认管理员可以访问
    public ResponseResult<List<OperationRole>> selectOperationRole(@RequestBody OperationRole operationRole) {
        // 至少有一个条件
        if (operationRole != null && (!StringUtils.isBlank(operationRole.getRoidId()) ||
                !StringUtils.isBlank(operationRole.getRoidType()))){
            List<OperationRole> resultList = new ArrayList<>();

            // 组合模糊搜索
            resultList = operationRoleService.selectByAll(operationRole.getRoidId(), operationRole.getRoidType());
            return new ResponseResult<List<OperationRole>>(ResponseResult.CodeStatus.OK, "查询成功", resultList);
        }

        return new ResponseResult<List<OperationRole>>(ResponseResult.CodeStatus.FAIL, "参数错误", null);
    }
}
