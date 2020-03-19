package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.FunctionMenuService;
import com.soft.one.ewms.business.user.service.FunctionRangeService;
import com.soft.one.ewms.business.user.service.FunctionRoleService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.dtos.user.FunctionDto;
import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * Title：功能角色
 * Description：功能菜单和功能角色
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/19
 */
@RestController
@CrossOrigin
@Api(tags = "功能角色和功能菜单")
@RequestMapping("/function")
public class FunctionController {

    @Resource
    private FunctionMenuService functionMenuService;

    @Resource
    private FunctionRoleService functionRoleService;

    @Resource
    private FunctionRangeService functionRangeService;


    // 功能菜单

    /**
     * 获取所有功能菜单
     * @return
     */
    @GetMapping("/get/menu/all")
    @ApiOperation(value = "获取所有功能菜单")
    @PreAuthorize("hasAnyAuthority('FunctionGetMenuAll','Function')") // 默认管理员可以访问
    public ResponseResult<List<FunctionMenu>> getFunctionMenus(){
        List<FunctionMenu> functionMenus = functionMenuService.selectAll();
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取成功", functionMenus);
    }

    /**
     * 新增功能
     * @param functionMenu id和fName必填
     * @return
     */
    @PostMapping("/insert/menu")
    @ApiOperation(value = "新增功能")
    @PreAuthorize("hasAnyAuthority('FunctionInsertMenu','Function')") // 默认管理员可以访问，数据库有
    public ResponseResult<Void> InsertFunctionMenu(@RequestBody FunctionMenu functionMenu) {
        if (functionMenu != null && !StringUtils.isBlank(functionMenu.getFId()) && !StringUtils.isBlank(functionMenu.getFName())){
            //数据库已经存在此id的记录，不能新增
            if (functionMenuService.selectByPrimaryKey(functionMenu.getFId()) != null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该功能id已存在");
            }
            // 增加功能
            int k = functionMenuService.insert(functionMenu);
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加功能成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "内部错误");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数不足");
    }

    /**
     * 更新功能内容
     * @param functionMenu id和fName必填
     * @return
     */
    @PostMapping("/update/menu")
    @ApiOperation(value = "更新功能")
    @PreAuthorize("hasAnyAuthority('FunctionUpdateMenu','Function')") // 默认管理员可以访问
    public ResponseResult<Void> updateFunctionMenu(@RequestBody FunctionMenu functionMenu) {
        if (functionMenu != null && !StringUtils.isBlank(functionMenu.getFId()) && !StringUtils.isBlank(functionMenu.getFName())){
            // 更新功能
            if (functionMenuService.selectByPrimaryKey(functionMenu.getFId()) == null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该功能id不存在");
            }
            int k = functionMenuService.updateByPrimaryKeySelective(functionMenu);
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "修改功能成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "内部错误");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数不足");
    }

    /**
     * 删除功能
     * @param fIdMap
     * @return
     */
    @PostMapping("/delete/menu")
    @ApiOperation(value = "删除功能")
    @PreAuthorize("hasAnyAuthority('FunctionDeleteMenu','Function')") // 默认管理员可以访问
    public ResponseResult<Void> deleteFunctionMenu(@RequestBody Map<String, String> fIdMap) {
        if (fIdMap != null && !StringUtils.isBlank(fIdMap.get("fId"))){
            // 删除功能
            int k = functionMenuService.deleteByPrimaryKey(fIdMap.get("fId"));
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "删除成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "内部错误");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数错误");
    }

    /**
     * 查询功能
     * 组合模糊搜索
     * @param functionMenu
     * @return
     */
    @PostMapping("/select/menu")
    @ApiOperation("查询功能，可单一，也可以组合")
    @PreAuthorize("hasAnyAuthority('FunctionSelectMenu','Function')") // 默认管理员可以访问
    public ResponseResult<List<FunctionMenu>> selectFunctionMenu(@RequestBody FunctionMenu functionMenu) {
        if (functionMenu != null){
            List<FunctionMenu> resultList = new ArrayList<>();

            //至少一个不为空
            if (!StringUtils.isBlank(functionMenu.getDescription()) || !StringUtils.isBlank(functionMenu.getFName())
                || !StringUtils.isBlank(functionMenu.getFId()) || !StringUtils.isBlank(functionMenu.getFUrl())){

                // 组合模糊搜索
                resultList = functionMenuService.selectByAll(functionMenu);
                return new ResponseResult<List<FunctionMenu>>(ResponseResult.CodeStatus.OK, "查询成功", resultList);

            }
            return new ResponseResult<List<FunctionMenu>>(ResponseResult.CodeStatus.FAIL, "内部错误",null);
        }
        return new ResponseResult<List<FunctionMenu>>(ResponseResult.CodeStatus.FAIL, "参数错误", null);
    }


    // 功能角色

    /**
     * 获取所有的功能角色
     * @return
     */
    @GetMapping("/get/role/all")
    @ApiOperation("获取所有的功能角色")
    @PreAuthorize("hasAnyAuthority('FunctionGetRoleAll','Function')") // 默认管理员可以访问
    public ResponseResult<List<FunctionRole>> getFunctionRoles(){
        List<FunctionRole> functionRoles = functionRoleService.selectAll();
        return new ResponseResult<List<FunctionRole>>(ResponseResult.CodeStatus.OK, "获取成功", functionRoles);
    }

    /**
     * 获取该功能角色的权限集合
     * 前端主要拿到中文名和id
     * @param frIdMap
     * @return
     */
    @PostMapping("/get/role/range")
    @ApiOperation("获取该功能角色的权限集合")
    @PreAuthorize("hasAnyAuthority('FunctionGetRoleRange','Function')") // 默认管理员可以访问
    public ResponseResult<List<FunctionMenu>> getFunctionRoleRange(@RequestBody Map<String, String> frIdMap){
        List<FunctionMenu> functionMenus = new ArrayList<>();
        if (frIdMap != null && !StringUtils.isBlank(frIdMap.get("frId"))){
            // 拿到权限id集合
            List<FunctionRange> functionRanges = new ArrayList<>();
            functionRanges = functionRangeService.selectByFrId(frIdMap.get("frId"));
            // 查到权限名之类的数据
            if (functionRanges != null){
                functionMenus = functionMenuService.selectByList(functionRanges);
            }
            return new ResponseResult<List<FunctionMenu>>(ResponseResult.CodeStatus.OK, "获取成功", functionMenus);
        }
        return new ResponseResult<List<FunctionMenu>>(ResponseResult.CodeStatus.FAIL, "获取失败", null);
    }

    /**
     * 新增功能角色，管理员，物流部
     * id和FrName英文名、描述、功能id集合
     * @param functionDto
     * @return
     */
    @PostMapping("/insert/role")
    @ApiOperation(value = "新增功能角色，管理员，物流部")
    @PreAuthorize("hasAnyAuthority('FunctionInsertRole','Function')") // 默认管理员可以访问，数据库有
    public ResponseResult<Void> InsertFunctionRole(@RequestBody FunctionDto functionDto) {
        if (functionDto != null && !StringUtils.isBlank(functionDto.getFrId()) && !StringUtils.isBlank(functionDto.getDescription()) ){
            //数据库已经存在此id的记录，不能新增
            if (functionRoleService.selectByPrimaryKey(functionDto.getFrId()) != null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该功能角色id已存在");
            }
            // 增加功能角色
            FunctionRole functionRole = new FunctionRole();
            functionRole.setFrId(functionDto.getFrId());
            functionRole.setDescription(functionDto.getDescription());
            if (!StringUtils.isBlank(functionDto.getFrName())){
                functionRole.setFrName(functionDto.getFrName());
            }
            int k = functionRoleService.insert(functionRole);
            // 权限集合不为空
            // 增加功能与角色的联系
            if (functionDto.getFIds() != null && functionDto.getFIds().size() > 0){
                functionRangeService.insertList(functionDto.getFrId(), functionDto.getFIds());
            }
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加功能角色成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数不足");
    }

    /**
     * 更新功能角色
     * 看前端怎么设计再可能考虑拆分，一起或拆分
     * @param functionDto 功能集合有打勾的发来就行
     * @return
     */
    @PostMapping("/update/role")
    @ApiOperation(value = "更新功能角色")
    @PreAuthorize("hasAnyAuthority('FunctionUpdateRole','Function')") // 默认管理员可以访问
    public ResponseResult<Void> updateFunctionRole(@RequestBody FunctionDto functionDto) {
        if (functionDto != null && !StringUtils.isBlank(functionDto.getFrId()) && !StringUtils.isBlank(functionDto.getDescription())){
            // 更新操作资料角色
            if (functionRoleService.selectByPrimaryKey(functionDto.getFrId()) == null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该功能角色id不存在");
            }
            FunctionRole functionRole = new FunctionRole();
            functionRole.setFrId(functionDto.getFrId());
            functionRole.setDescription(functionDto.getDescription());
            if (!StringUtils.isBlank(functionDto.getFrName())){
                functionRole.setFrName(functionDto.getFrName());
            }
            int k = functionRoleService.updateByPrimaryKeySelective(functionRole);
            // 更新功能角色权限
            // 有可能权限为空
            functionRangeService.deleteByFrId(functionDto.getFrId());
            if (functionDto.getFIds() != null){
                functionRangeService.insertList(functionDto.getFrId(), functionDto.getFIds());
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "修改功能角色成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数不足");
    }

    /**
     * 删除功能角色
     * @param frIdMap
     * @return
     */
    @PostMapping("/delete/role")
    @ApiOperation(value = "删除功能角色")
    @PreAuthorize("hasAnyAuthority('FunctionDeleteRole','Function')") // 默认管理员可以访问
    public ResponseResult<Void> deleteFunctionRole(@RequestBody Map<String, String> frIdMap) {
        if (frIdMap != null && !StringUtils.isBlank(frIdMap.get("frId"))){
            // 先删除功能权限联系
            functionRangeService.deleteByFrId(frIdMap.get("frId"));
            // 删除操作功能角色
            int k = functionRoleService.deleteByPrimaryKey(frIdMap.get("frId"));
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "删除成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "该功能角色不存在");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数错误");
    }

    /**
     * 查询功能角色
     * id、描述名、角色中文名，英文名，模糊搜索
     * @param functionRole id跟角色名
     * @return
     */
    @PostMapping("/select/role")
    @ApiOperation("查询功能角色")
    @PreAuthorize("hasAnyAuthority('FunctionSelectRole','Function')") // 默认管理员可以访问
    public ResponseResult<List<FunctionRole>> selectFunctionRole(@RequestBody FunctionRole functionRole) {
        // 至少有一个条件
        if (functionRole != null && (!StringUtils.isBlank(functionRole.getFrId()) ||
                !StringUtils.isBlank(functionRole.getDescription()) || !StringUtils.isBlank(functionRole.getFrName()))){
            List<FunctionRole> resultList = new ArrayList<>();

            // 组合模糊搜索
            resultList = functionRoleService.selectByAll(functionRole);
            return new ResponseResult<List<FunctionRole>>(ResponseResult.CodeStatus.OK, "查询成功", resultList);

        }
        return new ResponseResult<List<FunctionRole>>(ResponseResult.CodeStatus.FAIL, "参数错误", null);
    }
}
