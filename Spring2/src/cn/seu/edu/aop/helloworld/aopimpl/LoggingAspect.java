package cn.seu.edu.aop.helloworld.aopimpl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 将一个类声明为切面：1. 加入IOC容器  2. 然后声明切面
 * @author Administrator
 *
 */

@Aspect
@Component
public class LoggingAspect {
	
	// 声明一个前置通知 ；声明切面主要回答一个问题 在什么地方、什么时候、做什么
	@Before("execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))")
	public void beforeMethod(JoinPoint jointPoint) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName+ "." +methodName + " begins " + args);
	}
	
	// 后置通知，在方法执行完成后执行，无论方法是否正常结束，注意后置通知中不能访问目标方法的执行结果，结果在返回通中
	@After("execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))")
	public void afterMethod(JoinPoint jointPoint) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends ");
	}
	
	// 返回通知，在方法执行完成且正常返回后执行，这里就可以访问方法的执行结果
	@AfterReturning(value = "execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))",
			returning = "result")
	public void afterReturningMethod(JoinPoint jointPoint, Object result) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + result);
	}
	
	// 异常通知，在方法执行完成且抛出异常后执行，这里就可以访问方法异常
	@AfterThrowing(value = "execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))",
			throwing = "ex")
	public void afterThrowingMethod(JoinPoint jointPoint, Exception ex) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + ex.getMessage());
	}

}
