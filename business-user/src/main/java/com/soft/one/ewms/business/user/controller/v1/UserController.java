package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.ControlInService;
import com.soft.one.ewms.business.user.service.FunctionRoleService;
import com.soft.one.ewms.business.user.service.LogInService;
import com.soft.one.ewms.business.user.service.OperationRoleService;
import com.soft.one.ewms.business.user.service.UserInformationService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.dtos.user.LogoutDto;
import com.soft.one.ewms.domain.dtos.user.UserInformationDto;
import com.soft.one.ewms.domain.dtos.user.UserUpdateDto;
import com.soft.one.ewms.domain.pojos.user.ControlIn;
import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import com.soft.one.ewms.domain.pojos.user.LogIn;
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
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Title：用户相关的业务
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/19
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户相关的业务")
public class UserController {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private FunctionRoleService functionRoleService;

    @Resource
    private OperationRoleService operationRoleService;

    @Resource
    private LogInService logInService;

    @Resource
    private ControlInService controlInService;

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
            UserInformation userInformation = new UserInformation();
            // 修改了密码，需要加密
            if (!StringUtils.isBlank(userUpdateDto.getUserPsw())){
                userUpdateDto.setUserPsw(passwordEncoder.encode(userUpdateDto.getUserPsw()));
                userInformation.setPswDate(new Date());
            }
            BeanUtils.copyProperties(userUpdateDto,userInformation);
            int k = userInformationService.updateByPrimaryKeySelective(userInformation);
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"修改成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"修改失败");
    }

    /**
     * 用户自己退出登录，记录操作
     * 前端去掉token就可以，然后发送这个请求
     * @param logoutDto
     * @return
     */
    @PostMapping("/user/logout/own")
    @ApiOperation(value = "用户自己退出登录，记录操作")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> LogoutUser(@RequestBody LogoutDto logoutDto){
        if (logoutDto != null && !StringUtils.isEmpty(logoutDto.getUserId())
                && !StringUtils.isEmpty(logoutDto.getRefreshToken()) ){
            return content( logoutDto.getUserId(), 0, logoutDto.getRefreshToken());
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数数据为空");
    }

    /**
     * 系统退出用户登录，记录操作
     * 前端去掉token就可以
     * 前端去掉token就可以，然后发送这个请求
     * @param logoutDto
     * @return
     */
    @PostMapping("/user/logout/system")
    @ApiOperation(value = "系统退出用户登录，记录操作")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> LogoutSystem(@RequestBody LogoutDto logoutDto){
        if (logoutDto != null && !StringUtils.isEmpty(logoutDto.getUserId())
                && !StringUtils.isEmpty(logoutDto.getRefreshToken()) ){
            return content( logoutDto.getUserId(), 1, logoutDto.getRefreshToken());
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数数据为空");
    }

    @Resource
    private RestTemplate restTemplate;

    // 登出刷新token http://localhost:8089/oauth/refresh/token
    // "refreshToken"
    // 登出的主要业务
    public ResponseResult<Void> content(String userId, Integer type,String rToken){
        // 控制档删除
        ControlIn controlIn = controlInService.selectByPrimaryKey(userId);
        int k = controlInService.delete(controlIn);

        // 记录档记录退出时间和类型
        LogIn logIn = logInService.selectByUserIdAndOutDate(userId);
        if (logIn != null){
            logIn.setOutDate(new Date());
            logIn.setOutType(type);
            int k2 = logInService.updateByPrimaryKeySelective(logIn);
            if (k2 > 0 && k > 0){
                // 刷新下token，保证权限更新同步
                Map<String, String> token = new HashMap<>();
                token.put("refreshToken",rToken);
                ResponseResult responseResult = restTemplate.postForObject("http://localhost:8089/oauth/refresh/token", token, ResponseResult.class);
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"操作成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"操作失败");
    }

}
