package com.github.jashawn.examples.spring.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * 
 */

/**
 * 
 *
 * @author jashawn
 */
public class JCLLogTest {

	@Test
	public void test() {

		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		Log log = LogFactory.getLog(this.getClass());

		log.info(contextClassLoader.getClass().getName());
		log.info(this.getClass().getClassLoader().getClass().getName());
		log.info(String.class.getClassLoader().getClass().getName());
		// log.info(contextClassLoader.getClass().getName());

		log.info(log.getClass() + "@" + LogFactory.getFactory().getClass() + ":hello, world!");
	}

}
