package com.soft.one.ewms.business.account.service;

import com.soft.one.ewms.domain.TimeArgs;

public interface TimeArgsService{

    void updateTimeArgs(TimeArgs timeArgs);
    TimeArgs selectTimeArgs();
}
