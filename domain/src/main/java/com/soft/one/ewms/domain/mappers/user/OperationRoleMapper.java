package com.soft.one.ewms.domain.mappers.user;

import com.soft.one.ewms.domain.pojos.user.OperationRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface OperationRoleMapper extends MyMapper<OperationRole> {

    /**
     * 组合模糊搜索
     * @param roidId
     * @param roidType
     * @return
     */
    List<OperationRole> selectByAll(@Param("roidId") String roidId, @Param("roidType")String roidType);

}