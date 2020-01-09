package com.soft.one.ewms.business.permission.service.impl;

import com.soft.one.ewms.domain.FunctionMenu;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.business.permission.mapper.FunctionMenuMapper;
import com.soft.one.ewms.business.permission.service.FunctionMenuService;

import java.util.List;

@Service
public class FunctionMenuServiceImpl implements FunctionMenuService{

    @Resource
    private FunctionMenuMapper functionMenuMapper;

    @Override
    public void insert(FunctionMenu functionMenu) {
        functionMenuMapper.insert(functionMenu);
    }

    @Override
    public List<FunctionMenu> selectAll() {
        return functionMenuMapper.selectAll();
    }

    @Override
    public FunctionMenu selectById(FunctionMenu functionMenu) {
        return functionMenuMapper.selectByPrimaryKey(functionMenu);
    }

    @Override
    public void update(FunctionMenu functionMenu) {
        functionMenuMapper.updateByPrimaryKey(functionMenu);
    }

    @Override
    public void delete(FunctionMenu functionMenu) {
        functionMenuMapper.deleteByPrimaryKey(functionMenu);
    }
}
