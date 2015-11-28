
package ru.todo100.activer.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Igor Bobko
 */
@Aspect
public class Profiler
{
	@Pointcut("within(ru.todo100.activer.dao..*)")
	public void dao(){}

	@Before("ru.todo100.activer.aspect.Profiler.dao()")
	public void g(){
		System.out.println("RUNNING!!!!");
	}
}
