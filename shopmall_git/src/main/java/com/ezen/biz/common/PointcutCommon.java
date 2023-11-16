package com.ezen.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	// com.ezen.biz..로 시작하는 Impl 의 클래스에 대해 포인트컷 지정
	@Pointcut("execution(* com.ezen.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.ezen.biz..*Impl.get*(..))")
	public void getPointcut() {}
}
