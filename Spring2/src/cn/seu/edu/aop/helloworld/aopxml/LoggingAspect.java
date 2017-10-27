package cn.seu.edu.aop.helloworld.aopxml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class LoggingAspect {



	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " begins " + args);
	}

	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " after ");
	}

	public void afterReturningMethod(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + result);
	}

	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " throwing " + ex.getMessage());
	}

	public Object aroundgMethod(ProceedingJoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		Object result = null;
		try {
			// 这里就是前置通知
			// somecode

			// 执行方法
			result = joinPoint.proceed();

			// 这里就是返回通知
			// someode
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			// 这里就是异常通知
			e.printStackTrace();
		}
		// 这里就是后置通知
		System.out.println("method " + typeName + "." + methodName + " around ");
		return result;
	}

}
