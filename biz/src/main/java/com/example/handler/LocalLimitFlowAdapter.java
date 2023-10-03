package com.example.handler;

import org.springframework.stereotype.Component;

/**
 * @Author: suragi
 * @Date: 2023/7/12 22:12
 * @Description:
 */
@Component("LocalLimitFlowAdapter")
public class LocalLimitFlowAdapter implements LimitFlowAdapter{
    /**
     * @param adapter
     * @return
     */
    @Override
    public boolean support(String adapter) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean acquire() {
        return false;
    }
}

