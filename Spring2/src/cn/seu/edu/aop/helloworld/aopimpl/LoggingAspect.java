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
 * ��һ��������Ϊ���棺1. ����IOC����  2. Ȼ����������
 * @author Administrator
 *
 */

@Aspect
@Component
public class LoggingAspect {
	
	// ����һ��ǰ��֪ͨ ������������Ҫ�ش�һ������ ��ʲô�ط���ʲôʱ����ʲô
	@Before("execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))")
	public void beforeMethod(JoinPoint jointPoint) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName+ "." +methodName + " begins " + args);
	}
	
	// ����֪ͨ���ڷ���ִ����ɺ�ִ�У����۷����Ƿ�����������ע�����֪ͨ�в��ܷ���Ŀ�귽����ִ�н��������ڷ���ͨ��
	@After("execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))")
	public void afterMethod(JoinPoint jointPoint) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends ");
	}
	
	// ����֪ͨ���ڷ���ִ��������������غ�ִ�У�����Ϳ��Է��ʷ�����ִ�н��
	@AfterReturning(value = "execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))",
			returning = "result")
	public void afterReturningMethod(JoinPoint jointPoint, Object result) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + result);
	}
	
	// �쳣֪ͨ���ڷ���ִ��������׳��쳣��ִ�У�����Ϳ��Է��ʷ����쳣
	@AfterThrowing(value = "execution(public int cn.seu.edu.aop.helloworld.aopimpl.ArithmeticCalculatorImpl.*(int, int))",
			throwing = "ex")
	public void afterThrowingMethod(JoinPoint jointPoint, Exception ex) {
		String methodName = jointPoint.getSignature().getName();
		List<Object> args = Arrays.asList(jointPoint.getArgs());
		String typeName = jointPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + ex.getMessage());
	}

}
