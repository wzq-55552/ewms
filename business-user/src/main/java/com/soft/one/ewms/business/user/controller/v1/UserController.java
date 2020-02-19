package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.UserInformationService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.dtos.user.UserDto;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Title：用户相关
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInformationService userInformationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 管理员添加用户
     * @param userDto 3个字段
     * @return
     */
    @PostMapping("/insert")
    @PreAuthorize("hasAnyAuthority('UserInsert','Admin')") // 资源权限
    public ResponseResult<Void> insert(@RequestBody UserDto userDto){
        // 空异常让前端校验
        if (userDto!=null && !StringUtils.isBlank(userDto.getUserId()) && !StringUtils.isBlank(userDto.getUserName())
                && !StringUtils.isBlank(userDto.getUserPsw())){
            if (userInformationService.selectByPrimaryKey(userDto.getUserId())!=null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"添加失败，该用户id已存在");
            }
            UserInformation userInformation = new UserInformation();
            // 复制字段
            BeanUtils.copyProperties(userDto, userInformation);
            // spring security加密器加密
            userInformation.setUserPsw(passwordEncoder.encode(userDto.getUserPsw()));
            int k = userInformationService.insert(userInformation);
            if (k>0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"添加用户成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"添加失败，数据为空或部分数据为空");
    }

    /**
     * 用户修改个人信息
     * @param userInformation
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('UserUpdate','User')") // 资源权限
    public ResponseResult<Void> update(@RequestBody UserInformation userInformation){
        if (userInformation!=null){
            int k = userInformationService.updateByPrimaryKeySelective(userInformation);
            if (k>0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"修改成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"修改失败");
    }
}
