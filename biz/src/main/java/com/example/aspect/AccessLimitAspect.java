package com.example.aspect;

/**
 * @Author: suragi
 * @Date: 2023/7/8 12:32
 * @Description:
 */
/*@Slf4j
@Component
@Aspect
public class AccessLimitAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private List<LimitFlowAdapter> limitFlowAdapters;

    @Pointcut("@annotation(com.example.annotation.AccessLimit)")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String ip = httpServletRequest.getHeader("X-Real-IP");

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String methodName = proceedingJoinPoint.getTarget().getClass().getName() + "." + methodSignature.getName();
        String recordKey = ip + "->" + methodName;
        AccessLimit limit = methodSignature.getMethod().getAnnotation(AccessLimit.class);

        if( ! tryAccess(limit.value())) {
            log.error("ip -> method {} 单位时间内请求次数过多", recordKey);
            throw new RuntimeException("操作过快");
        }
        return proceedingJoinPoint.proceed();
    }

    *//**
     * 限流方法实现
     * @return true:当次请求允许访问，false:拒绝访问
     *//*
    private Boolean tryAccess(int times){
        if (!CollectionUtils.isEmpty(limitFlowAdapters)){
            for (LimitFlowAdapter adapter : limitFlowAdapters) {
                adapter.acquire();
            }
        }
        return true;
    }

}*/

