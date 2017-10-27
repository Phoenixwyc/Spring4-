package cn.seu.edu.aop.helloworld.aopannotation;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ��һ��������Ϊ���棺1. ����IOC���� 2. Ȼ����������
 * 
 * @author Administrator
 *
 */

@Aspect
@Component
@Order(value = 1)
public class LoggingAspect {

	/**
	 * ����һ��������ר�����������е���ʽ��û��ʵ������
	 * ���������ֻ֪ͨͨ���Ը÷��������ó䵱�е���ʽ�����֪ͨ���е���ʽ����һ�����ڣ���Ҫ���ϰ�������
	 */
	@Pointcut("execution(public int cn.seu.edu.aop.helloworld.aopannotation.ArithmeticCalculator.*(int, int))")
	public void declaredPointCutExpression() {
	};

	// ����һ��ǰ��֪ͨ ������������Ҫ�ش�һ������ ��ʲô�ط���ʲôʱ����ʲô
	@Before("declaredPointCutExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " begins " + args);
	}

	// ����֪ͨ���ڷ���ִ����ɺ�ִ�У����۷����Ƿ�����������ע�����֪ͨ�в��ܷ���Ŀ�귽����ִ�н��������ڷ���ͨ��
	@After("declaredPointCutExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " after ");
	}

	// ����֪ͨ���ڷ���ִ��������������غ�ִ�У�����Ϳ��Է��ʷ�����ִ�н��
	@AfterReturning(value = "declaredPointCutExpression()", returning = "result")
	public void afterReturningMethod(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + result);
	}

	// �쳣֪ͨ���ڷ���ִ��������׳��쳣��ִ�У�����Ϳ��Է��ʷ����쳣���ҿ����ڳ���ָ���쳣ʱִ�У�������catch
	@AfterThrowing(value = "declaredPointCutExpression()", throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " throwing " + ex.getMessage());
	}

	// ����֪ͨ�������ڶ�̬�����ȫ���̣�����֪ͨ�����з���ֵ���ҷ���ֵ����Ŀ�귽���ķ���ֵ
	@Around("execution(public int cn.seu.edu.aop.helloworld.aopannotation.ArithmeticCalculator.*(int, int))")
	public Object aroundgMethod(ProceedingJoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		Object result = null;
		try {
			// �������ǰ��֪ͨ
			// somecode

			// ִ�з���
			result = joinPoint.proceed();

			// ������Ƿ���֪ͨ
			// someode
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			// ��������쳣֪ͨ
			e.printStackTrace();
		}
		// ������Ǻ���֪ͨ
		System.out.println("method " + typeName + "." + methodName + " around ");
		return result;
	}

}
