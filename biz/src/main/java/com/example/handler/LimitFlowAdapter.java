package com.example.handler;

public interface LimitFlowAdapter {
    boolean support(String adapter);

    boolean acquire();
}
