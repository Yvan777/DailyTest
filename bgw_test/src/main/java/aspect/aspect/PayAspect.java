package aspect.aspect;

import com.alibaba.fastjson.JSONObject;
import aspect.anno.Pay;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class PayAspect {


    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(aspect.anno.Pay)")
    public void payCut() {}

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("payCut()")
    public Object Around(ProceedingJoinPoint point) throws Throwable {
        JSONObject o = new JSONObject();
        Class targetClass = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Method method = targetClass.getMethod(methodName);
        if (method != null) {
            boolean hasAnnotation = method.isAnnotationPresent(Pay.class);
            if (hasAnnotation) {
                Pay pay = method.getAnnotation(Pay.class);
                String bank = pay.bank();
                String money = pay.money();
                o.put("bb",bank+"xxxx");
                o.put("mm",money+"vvvv");
            }
        }
        Object result = null;
        try {
            result = point.proceed();
            System.out.println("======re"+result.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }
}
