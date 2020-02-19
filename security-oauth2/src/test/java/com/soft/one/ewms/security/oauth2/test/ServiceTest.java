package com.soft.one.ewms.security.oauth2.test;

import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import com.soft.one.ewms.domain.pojos.user.UserInformation;
import com.soft.one.ewms.security.oauth2.service.FunctionMenuService;
import com.soft.one.ewms.security.oauth2.service.FunctionRangeService;
import com.soft.one.ewms.security.oauth2.service.UserInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private FunctionMenuService functionMenuService;

    @Autowired
    private FunctionRangeService functionRangeService;

    @Test
    public void test() {
        UserInformation userInformation = userInformationService.getByUserId("2");
        System.out.println(userInformation.getUserPsw());

        if (userInformation != null) {
            // 获取用户授权
            List<FunctionRange> functionRanges = functionRangeService.selectByFrId(userInformation.getFrId());

            List<FunctionMenu> functionMenus = functionMenuService.selectByList(functionRanges);

            // 声明用户授权
            functionMenus.forEach(functionMenu -> {
                if (functionMenu != null && functionMenu.getFName() != null) {
                    System.out.println(functionMenu.getFName());
                }
            });
        }
    }
}
