package com.zycats.srs.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zycats.srs.entity.Comment;
import com.zycats.srs.entity.Report;
import com.zycats.srs.service.ICommentService;
import com.zycats.srs.service.ReportService;

@Aspect
@Component
public class DemoAspect {
	
	@Autowired
	private ICommentService commentService; 
	
	@Autowired
	private ReportService reportService;
	
	
	/*@Around("@annotation(com.zycats.srs.aspects.Demo) && execution(public * *(..))")
	public Object performDemo(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		System.out.println("this has happen before join point");
		
		Object value = null ;
		
		try{
			value = proceedingJoinPoint.proceed();
		}catch(Throwable throwable){
			throw throwable;
		}
		finally {
			System.out.println("===============================");
			System.out.println(proceedingJoinPoint.getTarget().getClass().getName());
			System.out.println(proceedingJoinPoint.getSignature().getName());
			System.out.println("===============================");
			System.out.println(value);
			System.out.println("===============================");
			System.out.println("this happened after join point");
		}
		
		return value;
		
		
	}*/
	
	@AfterReturning(pointcut="@annotation(com.zycats.srs.aspects.Demo) && execution(public * *(..))", returning="comment")
    public void createReportOnComment(Object comment) throws Throwable
    {
       System.out.println("status changed ============ ");
       System.out.println((Comment)comment);
       Comment comm = (Comment)comment;
       Report report = new Report();
       report.setTicket( comm.getTicket() );
       report.setStatusFrom( comm.getStatusFrom());
       report.setStatusTo( comm.getStatusTo());
       report.setComment(comm);
       report.setExecutive(comm.getTicket().getEngineer());
       
       reportService.addReport(report);
       
    }
	
	

}


