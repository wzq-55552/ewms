package com.soft.one.ewms.domain.mappers.user;

import com.soft.one.ewms.domain.pojos.user.TimeArgs;
import tk.mybatis.mapper.MyMapper;

public interface TimeArgsMapper extends MyMapper<TimeArgs> {

    /**
     * 查询一条修改时间最近的数据
     */
    TimeArgs SelectOneByLastedTime();

}