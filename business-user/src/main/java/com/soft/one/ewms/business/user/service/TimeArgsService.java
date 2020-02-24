package com.soft.one.ewms.business.user.service;

import com.soft.one.ewms.domain.pojos.user.TimeArgs;

import java.util.List;

public interface TimeArgsService{

    /**
     * 查询一条修改时间最近的数据
     * @return
     */
    TimeArgs selectOneByLastedTime();

    List<TimeArgs> selectAll();

    int insert(TimeArgs timeArgs);

}
