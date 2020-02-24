package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.ControlInService;
import com.soft.one.ewms.business.user.service.FunctionRoleService;
import com.soft.one.ewms.business.user.service.LogInService;
import com.soft.one.ewms.business.user.service.OperationRoleService;
import com.soft.one.ewms.business.user.service.TimeArgsService;
import com.soft.one.ewms.business.user.service.UserInformationService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.dtos.user.LoginInsertDto;
import com.soft.one.ewms.domain.dtos.user.TimeDto;
import com.soft.one.ewms.domain.dtos.user.UserInformationDto;
import com.soft.one.ewms.domain.dtos.user.UserUpdateDto;
import com.soft.one.ewms.domain.pojos.user.ControlIn;
import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import com.soft.one.ewms.domain.pojos.user.LogIn;
import com.soft.one.ewms.domain.pojos.user.OperationRole;
import com.soft.one.ewms.domain.pojos.user.TimeArgs;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title：用户相关的业务
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/19
 */
@RestController
public class UserController {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private ControlInService controlInService;

    @Resource
    private LogInService logInService;

    @Resource
    private TimeArgsService timeArgsService;

    @Resource
    private FunctionRoleService functionRoleService;

    @Resource
    private OperationRoleService operationRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 通过用户id获取用户信息
     * @param userIdMap 用户id
     * @return
     */
    @GetMapping("/user/select/one")
    @PreAuthorize("isAuthenticated()") // 任何用户都可以
    public ResponseResult<UserInformationDto> selectOne(@RequestBody Map<String,String> userIdMap){
        // 用户id不为空
        if (!StringUtils.isBlank(userIdMap.get("userId"))){

            UserInformation userInformation = userInformationService.selectByPrimaryKey(userIdMap.get("userId"));
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

    /**
     * 1
     * 判断该用户是否被其他设备登录，通过控制档
     * 设备id这里先给前端实现
     * 如果返回成功，则前端再获取token
     * @param controlIn 用户id和设备id
     * @return
     */
    @PostMapping("/is/login") // 不用token也可以访问，登录成功前
    public ResponseResult<Void> IsLogin(@RequestBody ControlIn controlIn){
        if (controlIn!=null && !StringUtils.isBlank(controlIn.getUserId()) && !StringUtils.isBlank(controlIn.getEquipment())){
            List<ControlIn> controlIns = controlInService.selectByUserId(controlIn.getUserId());
            // 该用户未被登录
            if (controlIns == null || controlIns.size() == 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"该用户未被登录");
            }
            // 同一个设备，不同浏览器可同时登吧。token只是同一个浏览器可识别到，不同浏览器不可以
            if (controlIns.get(0).getEquipment().equals(controlIn.getEquipment())){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"用户可以在同一个设备登录");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"该用户已被其他设备登录");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

    /**
     * 2获取到token
     * 3登录成功后的一些操作
     * 记录档、控制档
     * @param loginInsertDto 用户id、设备
     * @return
     */
    @PostMapping("/user/login/insert")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<TimeDto> LoginInsert(@RequestBody LoginInsertDto loginInsertDto){
        if (loginInsertDto != null){
            // 记录档添加
            LogIn logIn = new LogIn();
            logIn.setInDate(new Date());
            BeanUtils.copyProperties(loginInsertDto, logIn);
            int k = logInService.insert(logIn);

            // 控制档增加
            ControlIn controlIn = new ControlIn();
            BeanUtils.copyProperties(loginInsertDto, controlIn);
            int k2 = controlInService.insert(controlIn);

            // 修改密码提醒
            UserInformation information = userInformationService.selectByPrimaryKey(loginInsertDto.getUserId());
            // 拿到最新的时间参数
            TimeArgs timeArgs = timeArgsService.selectOneByLastedTime();

            TimeDto timeDto = new TimeDto();

            if (k>0 && k2>0 && timeArgs != null && information != null){
                if (information.getPswDate() != null){
                    timeDto.setPswDate(information.getPswDate());
                }
                timeDto.setRemindTime(timeArgs.getRemindTime());
                // 给2个时间参数给前端判断是否提醒用户修改密码
                // 当然，information.getPswDate() == null 就一定要提醒
                return new ResponseResult<TimeDto>(ResponseResult.CodeStatus.OK,"登录后的记录增加成功",timeDto);
            }
            return new ResponseResult<TimeDto>(ResponseResult.CodeStatus.FAIL,"内部错误",null);
        }
        return new ResponseResult<TimeDto>(ResponseResult.CodeStatus.FAIL,"参数数据为空",null);
    }

    /**
     * 用户自己退出登录，记录操作
     * 前端去掉token就可以，然后发送这个请求
     * @param userIdMap
     * @return
     */
    @PostMapping("/user/logout/own")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> LogoutUser(@RequestBody Map<String,String> userIdMap){
        if (userIdMap != null){
            String userId = userIdMap.get("userId");
            return content(userId, 0);
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数数据为空");
    }

    /**
     * 系统退出用户登录，记录操作
     * 前端去掉token就可以
     * 前端去掉token就可以，然后发送这个请求
     * @param userIdMap
     * @return
     */
    @PostMapping("/user/logout/system")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> LogoutSystem(@RequestBody Map<String,String> userIdMap){
        if (userIdMap != null){
            String userId = userIdMap.get("userId");
            return content(userId, 1);
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数数据为空");
    }

    // 登出的主要业务
    public ResponseResult<Void> content(String userId, Integer type){
        // 控制档删除
        List<ControlIn> controlIns = controlInService.selectByUserId(userId);
        // 随便删除一个
        int k = 0;
        if (controlIns.size() > 0){
            k = controlInService.delete(controlIns.get(0));
        }

        // 记录档记录退出时间和类型
        List<LogIn> logIns = logInService.selectByUserIdAndOutDate(userId);
        if (logIns.size() > 0){
            LogIn logIn = logIns.get(0);
            logIn.setOutDate(new Date());
            logIn.setOutType(type);
            int k2 = logInService.updateByPrimaryKeySelective(logIn);
            if (k2 > 0 && k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"操作成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"操作失败");
    }
}
