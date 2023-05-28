package utils;

import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author: suragi
 * @Date: 2023/5/27 19:07
 * @Description:
 */
public class TransactionUtils {
    /**
     * 注册事务执行成功之后的回调操作
     * @param doTransactionCompletion
     */
    public static void doAfterTransaction(DoTransactionCompletion  doTransactionCompletion){
        if(TransactionSynchronizationManager.isActualTransactionActive()){
            TransactionSynchronizationManager.registerSynchronization(doTransactionCompletion);
        }
    }
}



