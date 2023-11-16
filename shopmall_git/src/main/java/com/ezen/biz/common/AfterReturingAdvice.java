package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
@Service
@Aspect
public class AfterReturingAdvice {
//	@Pointcut("execution(* com.ezen.biz..*Impl.get*(..))")
//	public void getPointcut() {}
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")	// 바인딩 변수 지정
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		
		if(returnObj != null) {
			System.out.printf("[사후처리] 메소드명: %s(), 리턴값: %s\n",
								method, returnObj, toString());
		}else {
			System.out.printf("[사후처리] 메소드명: %s(), 리턴값: %s\n",
								method, "리턴값 없음");
		}
		
		
		System.out.printf("[사후처리] 메소드명: %s(), 리턴값: %s\n",
							method, returnObj.toString());
	}
}
