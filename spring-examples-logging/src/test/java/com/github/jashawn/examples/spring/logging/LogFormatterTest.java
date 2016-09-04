/**
 * 
 */
package com.github.jashawn.examples.spring.logging;

import java.text.MessageFormat;

import org.junit.Test;
import org.springframework.util.StopWatch;

import static org.junit.Assert.*;

/**
 * 
 *
 * @author jashawn
 */
public class LogFormatterTest {

	@Test
	public void test1() {
		String log = LogFormatter.log("hello, world!");
		assertEquals("hello, world!", log);
	}

	@Test
	public void test3() throws Exception {
		String log = LogFormatter.log("hello, {}!");
		assertEquals("hello, {}!", log);
	}

	@Test
	public void test2() throws Exception {
		String log = LogFormatter.log("hello, {0}!", "jim");
		assertEquals("hello, jim!", log);
	}

	@Test
	public void test4() throws Exception {
		String log = LogFormatter.log("hello, {{{0}}}!", "jim");
		assertEquals("hello, {{jim}}!", log);
	}

	@Test
	public void test5() throws Exception {
		String log = LogFormatter.log("hello, {0a}!");
		assertEquals("hello, {0a}!", log);
	}

	@Test
	public void test6() throws Exception {
		String log = LogFormatter.log("hello, {0a}!", "jim");
		assertEquals("hello, {0a}!", log);
	}

	@Test
	public void test7() throws Exception {
		String log = LogFormatter.log("hello, {a0}!", "jim");
		assertEquals("hello, {a0}!", log);
	}

	@Test
	public void test8() throws Exception {
		String log = LogFormatter.log("hello, {0} and {1}!", "jim", "all");
		assertEquals("hello, jim and all!", log);
	}

	@Test
	public void test9() throws Exception {
		String log = LogFormatter.log("{0} say {1} to {2}", "jim", "hello", "lily");
		assertEquals("jim say hello to lily", log);
	}

	@Test
	public void test10() throws Exception {
		String log = LogFormatter.log("{{{0}}} say {1} to {{{2}}}", "jim", "hello", "lily");
		assertEquals("{{jim}} say hello to {{lily}}", log);
	}

	@Test
	public void testBenchmarker() throws Exception {
		String template = "{0} say {1} to {2}";
		String[] array = new String[] { "jim", "hello", "lily" };

		StopWatch stopWatch = new StopWatch("日志工具性能对比");
		stopWatch.start("jdk format");
		for (int i = 0; i < 10000000; i++) {
			MessageFormat.format(template, "jim", "hello", "lily");
		}
		stopWatch.stop();
		stopWatch.start("jdk cache format");
		MessageFormat format = new MessageFormat(template);
		for (int i = 0; i < 10000000; i++) {
			format.format(array);
		}
		stopWatch.stop();
		stopWatch.start("self format");
		for (int i = 0; i < 10000000; i++) {
			LogFormatter.compile(template).format("jim", "hello", "lily");
		}
		stopWatch.stop();
		stopWatch.start("cache format");
		for (int i = 0; i < 10000000; i++) {
			LogFormatter.log(template, "jim", "hello", "lily");
		}
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}
}
