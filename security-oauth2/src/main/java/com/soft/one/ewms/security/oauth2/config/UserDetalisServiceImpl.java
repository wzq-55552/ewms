package com.soft.one.ewms.security.oauth2.config;

import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import com.soft.one.ewms.security.oauth2.service.FunctionMenuService;
import com.soft.one.ewms.security.oauth2.service.FunctionRangeService;
import com.soft.one.ewms.security.oauth2.service.UserInformationService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Title：Security提供的自定义用户接口
 * Description：实现security提供的认证授权接口，绑定数据库，自定义用户权限
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/16
 */
@Service
public class UserDetalisServiceImpl implements UserDetailsService {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private FunctionRangeService functionRangeService;

    @Resource
    private FunctionMenuService functionMenuService;

    // 自定义
    // 查询用户的权限集合
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        UserInformation userInformation = userInformationService.getByUserId(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (userInformation != null) {
            // 获取用户授权，根据FrId，即通过用户类型（比如管理员，普通用户之类），来得到角色权限关联表
            List<FunctionRange> functionRanges = functionRangeService.selectByFrId(userInformation.getFrId());

            // 拿到权限url、权限名
            List<FunctionMenu> functionMenus = functionMenuService.selectByList(functionRanges);

            // 声明用户授权
            if (functionMenus!=null && functionMenus.size()!=0){
                functionMenus.forEach(functionMenu -> {
                    if (functionMenu != null && !StringUtils.isEmpty(functionMenu.getFName())){
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(functionMenu.getFName());
                        grantedAuthorities.add(grantedAuthority);
                    }
                });
            }

            // 由框架完成认证工作
            return new User(userInformation.getUserId(), userInformation.getUserPsw(), grantedAuthorities);
        }

        return null;
    }
}
