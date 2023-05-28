package com.example.utils;

import org.springframework.transaction.support.TransactionSynchronization;

/**
 * 本地事务回调辅助类
 * @Author: suragi
 * @Date: 2023/5/27 19:24
 * @Description:
 */
public class DoTransactionCompletion implements TransactionSynchronization {
    private Runnable runnable;

    public DoTransactionCompletion(Runnable runnable){
        this.runnable = runnable;
    }

    /**
     *
     */
    @Override
    public void afterCompletion(int status) {
        if (status == TransactionSynchronization.STATUS_COMMITTED){
            this.runnable.run();
        }
    }
}


