package com.soft.one.ewms.security.oauth2.controller;

import com.soft.one.ewms.commons.dto.ResponseResult;
import com.soft.one.ewms.commons.utils.DateUtils;
import com.soft.one.ewms.commons.utils.IPUtil;
import com.soft.one.ewms.commons.utils.VerifyUtil;
import com.soft.one.ewms.domain.pojos.user.ControlIn;
import com.soft.one.ewms.domain.pojos.user.LogIn;
import com.soft.one.ewms.domain.pojos.user.TimeArgs;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import com.soft.one.ewms.security.oauth2.service.AuthService;
import com.soft.one.ewms.security.oauth2.service.ControlInService;
import com.soft.one.ewms.security.oauth2.service.LogInService;
import com.soft.one.ewms.security.oauth2.service.TimeArgsService;
import com.soft.one.ewms.security.oauth2.service.UserInformationService;
import com.soft.one.ewms.security.oauth2.utlis.AuthToken;
import com.soft.one.ewms.security.oauth2.utlis.LoginDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Title：
 * Description：再封装，客户端信息不泄露
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/11
 */
@RestController
@CrossOrigin
public class LoginController {

    @Resource
    private AuthService authService;

    @Resource
    private TimeArgsService timeArgsService;

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private ControlInService controlInService;

    @Resource
    private LogInService logInService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;


    /**
     * 密码模式  认证
     * @param loginDto
     * @return
     */
    //不用请求头
    @PostMapping("/oauth/login")
    public ResponseResult<AuthToken> login(@RequestBody LoginDto loginDto) {

        if (StringUtils.isEmpty(loginDto.getUserID()) || StringUtils.isEmpty(loginDto.getUserPsw())
        || StringUtils.isEmpty(loginDto.getRandString()) ){
            return new ResponseResult<AuthToken>(ResponseResult.CodeStatus.FAIL,"请输入账号、密码和验证码", null);
        }

        String str = stringRedisTemplate.boundValueOps(loginDto.getRandString()).get();
        if (StringUtils.isEmpty(str)){
            return new ResponseResult<AuthToken>(ResponseResult.CodeStatus.FAIL,"验证码错误", null);
        }

        //自己先验证账号，密码。框架也会帮我们验证,这里可自定义返回信息。
        UserInformation userInformation = userInformationService.getByUserId(loginDto.getUserID());
        if(userInformation == null) {
            return new ResponseResult<AuthToken>(ResponseResult.CodeStatus.FAIL, "用户不存在", null);
        }

        if (!bCryptPasswordEncoder.matches(loginDto.getUserPsw(),userInformation.getUserPsw())){
            return new ResponseResult<AuthToken>(ResponseResult.CodeStatus.FAIL, "密码错误", null);
        }

        ControlIn controlIn = controlInService.selectByPrimaryKey(loginDto.getUserID());
        if (controlIn != null){
            return new ResponseResult<AuthToken>(ResponseResult.CodeStatus.FAIL, "该用户已在其它设备登录", null);
        }

        //登录之后生成令牌的数据返回，请求security认证授权
        AuthToken authToken = authService.login(loginDto, clientId, clientSecret);

        if (authToken!=null){
            //纪录档
            LogIn logIn = new LogIn();
            logIn.setInDate(new Date());
            logIn.setUserId(loginDto.getUserID());
            if (!StringUtils.isEmpty(loginDto.getIP())){
                String cityInfo = IPUtil.getCityInfo(loginDto.getIP());
                cityInfo = loginDto.getIP() + "|" +cityInfo;
                logIn.setEquipment(cityInfo);

                //控制档
                controlIn = new ControlIn();
                controlIn.setUserId(loginDto.getUserID());
                controlIn.setEquipment(cityInfo);
                controlInService.insert(controlIn);
            }
            logInService.insert(logIn);


            if (userInformation.getPswDate()!=null){
                //判断该用户是否提醒修改密码
                Date pswDate = userInformation.getPswDate();
                TimeArgs timeArgs = timeArgsService.selectOneByLastedTime();
                int remindTime = timeArgs.getRemindTime();
                Date date = DateUtils.addDay(pswDate, remindTime);
                if (new Date().before(date)){
                    authToken.setIsRemind(0);
                }else{
                    authToken.setIsRemind(1);
                }
            }else{
                //为空是未改过密码
                authToken.setIsRemind(1);
            }
        }

        return new ResponseResult<AuthToken>(ResponseResult.CodeStatus.OK,"令牌生成成功", authToken);
    }

    /**
     * 用户自己退出登录，记录操作
     * 前端去掉token就可以，然后发送这个请求
     * @param userIdMap
     * @return
     */
    @PostMapping("/user/logout/own")
    @ApiOperation(value = "用户自己退出登录，记录操作")
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
    @ApiOperation(value = "系统退出用户登录，记录操作")
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
        ControlIn controlIn = controlInService.selectByPrimaryKey(userId);
        int k = controlInService.delete(controlIn);

        // 记录档记录退出时间和类型
        LogIn logIn = logInService.selectByUserIdAndOutDate(userId);
        if (logIn != null){
            logIn.setOutDate(new Date());
            logIn.setOutType(type);
            int k2 = logInService.updateByPrimaryKeySelective(logIn);
            if (k2 > 0 && k > 0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"操作成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"操作失败");
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 图形验证码生成
     * @param response
     * @throws Exception
     */
    @RequestMapping("/createImg")
    public void createImg(HttpServletResponse response) throws Exception {
        try {
            response.setContentType("image/jpeg"); //设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache"); //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            String randString = randomValidateCode.getRandcode(/*request, */response);//输出验证码图片
            System.out.println(randString);
            //将生成的随机验证码存放到redis中
            if (StringUtils.isEmpty(stringRedisTemplate.boundValueOps(randString).get())){
                stringRedisTemplate.opsForValue().set(randString, randString,3*60, TimeUnit.SECONDS);
            }
            //"RANDOMVALIDATECODEKEY"
            //(String) request.getSession().getAttribute(
            //"RANDOMREDISKEY"), Long.parseLong("60000")); //缓存一分钟
        } catch (Exception e) {
            System.err.println("获取验证码异常："+e);
        }
    }
}
