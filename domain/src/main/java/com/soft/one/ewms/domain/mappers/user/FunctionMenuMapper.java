package com.soft.one.ewms.domain.mappers.user;

import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface FunctionMenuMapper extends MyMapper<FunctionMenu> {


    /**
     * 组合模糊搜索
     * @param functionMenu
     * @return
     */
    List<FunctionMenu> selectByAll(@Param("functionMenu") FunctionMenu functionMenu);

}