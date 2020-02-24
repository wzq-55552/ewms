package com.soft.one.ewms.business.user.test;

import com.soft.one.ewms.business.user.service.OperationRangeService;
import com.soft.one.ewms.domain.pojos.user.OperationRange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRangeTest {

    @Resource
    private OperationRangeService operationRangeService;

    @Test
    public void testDelete(){
        int k = operationRangeService.deleteByRoidId("3");
        System.out.println(k);
    }

}
