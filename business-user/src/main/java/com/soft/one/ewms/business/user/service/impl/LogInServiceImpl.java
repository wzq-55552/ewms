package com.soft.one.ewms.business.user.service.impl;

import com.soft.one.ewms.domain.pojos.user.LogIn;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.LogInMapper;
import com.soft.one.ewms.business.user.service.LogInService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LogInServiceImpl implements LogInService{

    @Resource
    private LogInMapper logInMapper;

    @Override
    public LogIn selectByUserIdAndOutDate(String userId) {
        return logInMapper.selectByUserIdAndOutDate(userId);
    }

    @Override
    public int insert(LogIn logIn) {
        return logInMapper.insert(logIn);
    }

    @Override
    public LogIn selectOne(LogIn logIn) {
        return logInMapper.selectOne(logIn);
    }

    @Override
    public List<LogIn> selectAll() {
        return logInMapper.selectAll();
    }

    @Override
    public List<LogIn> select(LogIn logIn) {
        return logInMapper.select(logIn);
    }

    // 起始可以，结束时间相差8小时
    @Override
    public List<LogIn> selectByAll(String userId, String equipment, Date beginDate, Date endDate) {
        // 结束时间加8小时
        if (endDate != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.add(Calendar.HOUR, 8); // 24小时制
            // 更新后的时间
            endDate = cal.getTime();
        }
        return logInMapper.selectByAll(userId, equipment, beginDate, endDate);
    }

    @Override
    public int updateByPrimaryKeySelective(LogIn logIn) {
        return logInMapper.updateByPrimaryKeySelective(logIn);
    }

}
