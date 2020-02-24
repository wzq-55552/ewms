package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRange;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.FunctionMenuMapper;
import com.soft.one.ewms.business.user.service.FunctionMenuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionMenuServiceImpl implements FunctionMenuService{

    @Resource
    private FunctionMenuMapper functionMenuMapper;

    @Override
    public List<FunctionMenu> selectAll() {
        return functionMenuMapper.selectAll();
    }

    @Override
    public FunctionMenu selectByPrimaryKey(String fId) {
        return functionMenuMapper.selectByPrimaryKey(fId);
    }

    @Override
    public int insert(FunctionMenu functionMenu) {
        return functionMenuMapper.insert(functionMenu);
    }

    @Override
    public int updateByPrimaryKeySelective(FunctionMenu functionMenu) {
        return functionMenuMapper.updateByPrimaryKeySelective(functionMenu);
    }

    @Override
    public int deleteByPrimaryKey(String fId) {
        return functionMenuMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public FunctionMenu selectOne(FunctionMenu functionMenu) {
        return functionMenuMapper.selectOne(functionMenu);
    }

    @Override
    public List<FunctionMenu> selectByAll(FunctionMenu functionMenu) {
        if (functionMenu != null){
            return functionMenuMapper.selectByAll(functionMenu);
        }
        return null;
    }

    @Override
    public List<FunctionMenu> selectByList(List<FunctionRange> functionRanges) {
        List<FunctionMenu> functionMenus = new ArrayList<>();
        if (functionRanges.size() > 0){
            functionRanges.forEach(functionRange -> {
                FunctionMenu functionMenu = new FunctionMenu();
                functionMenu = functionMenuMapper.selectByPrimaryKey(functionRange.getFId());
                if (functionMenu != null){
                    functionMenus.add(functionMenu);
                }
            });
            return functionMenus;
        }
        return null;
    }
}
