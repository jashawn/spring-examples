/**
 * 
 */
package com.github.jashawn.examples.springcore;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * stopwatch工具类用于输出任务执行的时间
 *
 * @author jashawn
 */
public class StopWatchTest {

	@Test
	public void testStopWatch() throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start("task1");
		Thread.sleep(1000);
		stopWatch.stop();
		stopWatch.start("task2");
		Thread.sleep(1000);
		stopWatch.stop();
		
		//获取最后一个任务的执行时间
		System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());
		
		System.out.println(stopWatch.prettyPrint());
	}
}
