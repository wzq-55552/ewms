package com.soft.one.ewms.security.oauth2.service.impl;

import com.soft.one.ewms.domain.mappers.user.FunctionMenuMapper;
import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import com.soft.one.ewms.security.oauth2.service.FunctionMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionMenuServiceImpl implements FunctionMenuService {

    @Resource
    private FunctionMenuMapper functionMenuMapper;

    @Override
    public List<FunctionMenu> selectByList(List<FunctionRange> functionRanges) {
        List<FunctionMenu> functionMenus = new ArrayList<>();
        if (functionRanges!=null && functionRanges.size()!=0){
            functionRanges.forEach(functionRange -> {
                FunctionMenu functionMenu = functionMenuMapper.selectByPrimaryKey(functionRange.getFId());
                if (functionMenu!=null){
                    functionMenus.add(functionMenu);
                }
            });
            return functionMenus;
        }
        return null;
    }
}
