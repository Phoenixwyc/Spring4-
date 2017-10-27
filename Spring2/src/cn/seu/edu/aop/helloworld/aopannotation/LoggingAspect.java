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
 * 将一个类声明为切面：1. 加入IOC容器 2. 然后声明切面
 * 
 * @author Administrator
 *
 */

@Aspect
@Component
@Order(value = 1)
public class LoggingAspect {

	/**
	 * 定义一个方法，专门用于声明切点表达式，没有实际内容
	 * 后面的其他通知只通过对该方法的引用充当切点表达式，如果通知和切点表达式不再一个包内，需要加上包名即可
	 */
	@Pointcut("execution(public int cn.seu.edu.aop.helloworld.aopannotation.ArithmeticCalculator.*(int, int))")
	public void declaredPointCutExpression() {
	};

	// 声明一个前置通知 ；声明切面主要回答一个问题 在什么地方、什么时候、做什么
	@Before("declaredPointCutExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " begins " + args);
	}

	// 后置通知，在方法执行完成后执行，无论方法是否正常结束，注意后置通知中不能访问目标方法的执行结果，结果在返回通中
	@After("declaredPointCutExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " after ");
	}

	// 返回通知，在方法执行完成且正常返回后执行，这里就可以访问方法的执行结果
	@AfterReturning(value = "declaredPointCutExpression()", returning = "result")
	public void afterReturningMethod(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " ends " + result);
	}

	// 异常通知，在方法执行完成且抛出异常后执行，这里就可以访问方法异常，且可以在出现指定异常时执行，类似于catch
	@AfterThrowing(value = "declaredPointCutExpression()", throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("method " + typeName + "." + methodName + " throwing " + ex.getMessage());
	}

	// 环绕通知，类似于动态代理的全过程；环绕通知必须有返回值，且返回值就是目标方法的返回值
	@Around("execution(public int cn.seu.edu.aop.helloworld.aopannotation.ArithmeticCalculator.*(int, int))")
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
