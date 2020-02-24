package com.soft.one.ewms.domain.mappers.user;

import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface FunctionRoleMapper extends MyMapper<FunctionRole> {

    /**
     * 组合模糊搜索
     * @param functionRole
     * @return
     */
    List<FunctionRole> selectByAll(@Param("functionRole") FunctionRole functionRole);
}