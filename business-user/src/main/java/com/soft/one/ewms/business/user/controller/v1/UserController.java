package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.FunctionRoleService;
import com.soft.one.ewms.business.user.service.OperationRoleService;
import com.soft.one.ewms.business.user.service.UserInformationService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.dtos.user.UserInformationDto;
import com.soft.one.ewms.domain.dtos.user.UserUpdateDto;
import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import com.soft.one.ewms.domain.pojos.user.OperationRole;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Title：用户相关的业务
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/19
 */
@RestController
@CrossOrigin
@Api(tags = "用户相关的业务")
public class UserController {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private FunctionRoleService functionRoleService;

    @Resource
    private OperationRoleService operationRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return
     */
    @GetMapping("/user/select/{id}")
    @ApiOperation(value = "通过用户id获取用户信息")
    @PreAuthorize("isAuthenticated()") // 任何用户都可以
    public ResponseResult<UserInformationDto> selectOne(@PathVariable("id") String id){
        // 用户id不为空
        if (!StringUtils.isBlank(id)){

            UserInformation userInformation = userInformationService.selectByPrimaryKey(id);
            UserInformationDto userInformationDto = new UserInformationDto();

            if (userInformation != null){
                BeanUtils.copyProperties(userInformation, userInformationDto);
                // 其实可以mybatis多表join连查
                if (!StringUtils.isBlank(userInformation.getFrId())){
                    FunctionRole functionRole = functionRoleService.selectByPrimaryKey(userInformation.getFrId());
                    userInformationDto.setFrIdDescription(functionRole.getDescription());
                }
                if (!StringUtils.isBlank(userInformation.getRoidId())){
                    OperationRole operationRole = operationRoleService.selectByPrimaryKey(userInformation.getRoidId());
                    userInformationDto.setRoidIdDescription(operationRole.getRoidType());
                }
                return new ResponseResult<UserInformationDto>(ResponseResult.CodeStatus.OK,"获取用户信息成功",userInformationDto);
            }else{
                return new ResponseResult<UserInformationDto>(ResponseResult.CodeStatus.FAIL,"用户id不存在",null);
            }
        }
        return new ResponseResult<UserInformationDto>(ResponseResult.CodeStatus.FAIL,"用户id为空",null);
    }

    /**
     * 用户修改个人信息，包括密码
     * @param userUpdateDto 必须带用户id，其他3个参数可以修改
     * @return
     */
    @PostMapping("/user/update")
    @ApiOperation(value = "用户修改个人信息，包括密码，只传id和密码也行")
    @PreAuthorize("hasAnyAuthority('UserUpdate','User')") // 资源权限
    public ResponseResult<Void> update(@RequestBody UserUpdateDto userUpdateDto){
        // 用户id不为空
        if (userUpdateDto!=null && !StringUtils.isBlank(userUpdateDto.getUserId())){
            // 修改了密码，需要加密
            if (!StringUtils.isBlank(userUpdateDto.getUserPsw())){
                userUpdateDto.setUserPsw(passwordEncoder.encode(userUpdateDto.getUserPsw()));
            }
            UserInformation userInformation = new UserInformation();
            BeanUtils.copyProperties(userUpdateDto,userInformation);
            int k = userInformationService.updateByPrimaryKeySelective(userInformation);
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"修改成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"修改失败");
    }

}
