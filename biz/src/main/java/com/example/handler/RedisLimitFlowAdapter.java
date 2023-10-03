package com.example.handler;

import com.example.constants.BizConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: suragi
 * @Date: 2023/7/12 22:10
 * @Description:
 */
@Component("RedisLimitAdapter")
public class RedisLimitFlowAdapter implements LimitFlowAdapter{

    /**
     * @param adapter
     * @return
     */
    @Override
    public boolean support(String adapter) {
        return adapter != null && StringUtils.equalsIgnoreCase(BizConstants.RedisLimitMode, adapter);
    }

    /**
     * @return
     */
    @Override
    public boolean acquire() {
        return false;
    }
}

