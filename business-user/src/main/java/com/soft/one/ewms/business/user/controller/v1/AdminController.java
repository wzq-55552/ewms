package com.soft.one.ewms.business.user.controller.v1;

import com.soft.one.ewms.business.user.service.FunctionRangeService;
import com.soft.one.ewms.business.user.service.FunctionRoleService;
import com.soft.one.ewms.business.user.service.LogInService;
import com.soft.one.ewms.business.user.service.OperationRoleService;
import com.soft.one.ewms.business.user.service.TimeArgsService;
import com.soft.one.ewms.business.user.service.UserInformationService;
import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.domain.dtos.user.LoginSearchDto;
import com.soft.one.ewms.domain.dtos.user.TimeInsertDto;
import com.soft.one.ewms.domain.dtos.user.UserInformationDto;
import com.soft.one.ewms.domain.dtos.user.UserInsertDto;
import com.soft.one.ewms.domain.dtos.user.UserRoleDto;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title：管理员用户的一些操作
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/20
 */
@RestController
public class AdminController {

    @Resource
    private UserInformationService userInformationService;

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
     * 管理员添加用户
     * @param userDto 3个字段
     * @return
     */
    @PostMapping("/user/insert")
    @PreAuthorize("hasAnyAuthority('UserInsert','User')") // 资源权限
    public ResponseResult<Void> insert(@RequestBody UserInsertDto userDto){
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
     * 管理员进去登录记录查询页面加载的起始数据
     * @return
     */
    @GetMapping("/user/get/login/all")
    @PreAuthorize("hasAnyAuthority('UserGetLoginAll','User')") // 资源权限,'User'表示/user/**
    public ResponseResult<List<LogIn>> loginAll(){
        List<LogIn> logIns = logInService.selectAll();
        if (logIns != null && logIns.size() != 0){
            return new ResponseResult<List<LogIn>>(ResponseResult.CodeStatus.OK,"加载数据成功",logIns);
        }
        return new ResponseResult<List<LogIn>>(ResponseResult.CodeStatus.OK,"没有数据",null);
    }

    /**
     * 管理员查询登录log档，查看用户的登录情况
     * @param loginSearchDto 3个条件，用户id，设备id，时间段
     *                       模糊搜索
     * @return
     */
    @PostMapping("/user/login/search")
    @PreAuthorize("hasAnyAuthority('UserLoginSearch','User')") // 资源权限,'User'表示/user/**
    public ResponseResult<List<LogIn>> loginSearch(@RequestBody LoginSearchDto loginSearchDto){

        List<LogIn> resultList = new ArrayList<>();

        // 时间段的任务条件都可以，一个不空，一个为空，默认判断一个就行
        // 起始时间不为空，结束时间为空，则查出来的就是时间>起始时间的
        if (loginSearchDto != null && (!StringUtils.isBlank(loginSearchDto.getUserId()) || !StringUtils.isBlank(loginSearchDto.getEquipment())
                || loginSearchDto.getBeginDate() != null || loginSearchDto.getEndDate() != null)){
            // 组合模糊查询
            resultList = logInService.selectByAll(loginSearchDto.getUserId(), loginSearchDto.getEquipment(),
                    loginSearchDto.getBeginDate(), loginSearchDto.getEndDate());
            return new ResponseResult<List<LogIn>>(ResponseResult.CodeStatus.OK, "查询成功",resultList);
        }

        return new ResponseResult<List<LogIn>>(ResponseResult.CodeStatus.FAIL, "条件错误，查询失败",null);
    }

    /**
     * 管理员拿到所有修改时间参数的记录
     * @return
     */
    @GetMapping("/user/get/time/all")
    @PreAuthorize("hasAnyAuthority('UserGetTimeAll','User')") // 管理员才可以
    public ResponseResult<List<TimeArgs>> userTimeAll(){
        List<TimeArgs> timeArgs = timeArgsService.selectAll();
        if (timeArgs != null && timeArgs.size() != 0){
            return new ResponseResult<List<TimeArgs>>(ResponseResult.CodeStatus.OK, "获取成功",timeArgs);
        }
        return new ResponseResult<List<TimeArgs>>(ResponseResult.CodeStatus.FAIL, "没有数据",null);
    }

    /**
     * 前端获取管理员最晚修改参数的一条数据
     * 前端拿到时间数据去实现用户停留时间自动退出
     * 自动退出，js去掉存储中的token即可，然后发送保留记录的请求
     * @return
     */
    @GetMapping("/user/time/lasted")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<TimeArgs> userTimeLasted(){
        TimeArgs timeArgs = timeArgsService.selectOneByLastedTime();
        if (timeArgs != null){
            return new ResponseResult<TimeArgs>(ResponseResult.CodeStatus.OK, "获取成功",timeArgs);
        }
        return new ResponseResult<TimeArgs>(ResponseResult.CodeStatus.FAIL, "没有数据",null);
    }

    /**
     * 管理员修改时间参数，即是增加一条数据
     * @param timeInsertDto
     * @return
     */
    @PostMapping("/user/time/insert")
    @PreAuthorize("hasAnyAuthority('UserTimeInsert','User')") // 管理员才可以
    public ResponseResult<Void> userTimeInsert(@RequestBody TimeInsertDto timeInsertDto){
        if (timeInsertDto != null && !StringUtils.isBlank(timeInsertDto.getUserId())){
            TimeArgs timeArgs = new TimeArgs();
            BeanUtils.copyProperties(timeInsertDto,timeArgs);
            timeArgs.setArgsDate(new Date());
            int k = timeArgsService.insert(timeArgs);
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "修改成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "参数为空");
    }


    // 角色分配

    /**
     * 管理员分配角色时显示用户数据
     * 忽略密码，加了中文描述，前端直接显示
     * @return
     */
    @GetMapping("/user/get/all")
    @PreAuthorize("hasAnyAuthority('UserGetAll','User')") // 资源权限,'User'表示/user/**
    public ResponseResult<List<UserInformationDto>> GetAll(){
        List<UserInformation> userInformations = userInformationService.selectAll();
        List<UserInformationDto> userInformationDtos = new ArrayList<>();
        if (userInformations != null && userInformations.size() > 0){
            userInformations.forEach(userInformation -> {
                UserInformationDto userInformationDto = new UserInformationDto();
                BeanUtils.copyProperties(userInformation, userInformationDto);
                if (!StringUtils.isBlank(userInformation.getFrId())){
                    FunctionRole functionRole = functionRoleService.selectByPrimaryKey(userInformation.getFrId());
                    userInformationDto.setFrIdDescription(functionRole.getDescription());
                }
                if (!StringUtils.isBlank(userInformation.getRoidId())){
                    OperationRole operationRole = operationRoleService.selectByPrimaryKey(userInformation.getRoidId());
                    userInformationDto.setRoidIdDescription(operationRole.getRoidType());
                }
                userInformationDtos.add(userInformationDto);
            });
            return new ResponseResult<List<UserInformationDto>>(ResponseResult.CodeStatus.OK,"加载数据成功",userInformationDtos);
        }
        return new ResponseResult<List<UserInformationDto>>(ResponseResult.CodeStatus.OK,"没有数据",null);
    }

    /**
     * 管理员分配用户角色，管理员才可以访问
     * 修改、设定、删除这个这个方法。根据角色id的值
     * @param userRoleDto 用户id,2个角色id
     * @return
     */
    @PostMapping("/user/update/role")
    @PreAuthorize("hasAnyAuthority('UserUpdateRole','User')") // 资源权限
    public ResponseResult<Void> updateRole(@RequestBody UserRoleDto userRoleDto){
        // 用户id不为空
        if (userRoleDto !=null && !StringUtils.isBlank(userRoleDto.getUserId())){
            UserInformation userInformation = userInformationService.selectByPrimaryKey(userRoleDto.getUserId());
            if (userInformation == null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"该用户id不存在");
            }

            if (functionRoleService.selectByPrimaryKey(userRoleDto.getFrId()) != null){
                userInformation.setFrId(userRoleDto.getFrId());
            }else{
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"功能角色不存在");
            }
            if (operationRoleService.selectByPrimaryKey(userRoleDto.getRoidId()) != null){
                userInformation.setRoidId(userRoleDto.getRoidId());
            }else{
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"操作资料角色不存在");
            }
            // 如果是删除角色id，那可以为空
            //if (!StringUtils.isBlank(userUpdateRoleDto.getFrId())){
                //userInformation.setFrId(userRoleDto.getFrId());
            //}
            //if (!StringUtils.isBlank(userUpdateRoleDto.getRoidId())){
                //userInformation.setRoidId(userRoleDto.getRoidId());
            //}
            int k = userInformationService.updateByPrimaryKeySelective(userInformation);
            if (k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"分配成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"内部错误");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"分配失败");
    }

    /**
     * 管理员查询分配角色的功用户
     * @param userRoleDto
     * @return
     */
    @PostMapping("/user/select/role")
    @PreAuthorize("hasAnyAuthority('UserSelectRole','User')") // 资源权限
    public ResponseResult<List<UserInformation>> SelectRole(@RequestBody UserRoleDto userRoleDto){
        // 至少一个条件不为空
        if (userRoleDto !=null && (!StringUtils.isBlank(userRoleDto.getFrId())
                || !StringUtils.isBlank(userRoleDto.getRoidId()) || !StringUtils.isBlank(userRoleDto.getUserId())) ){

            List<UserInformation> userInformations = userInformationService.selectByDto(userRoleDto);

            return new ResponseResult<List<UserInformation>>(ResponseResult.CodeStatus.OK,"查询成功",userInformations);
        }
        return new ResponseResult<List<UserInformation>>(ResponseResult.CodeStatus.FAIL,"参数不足",null);
    }
}
