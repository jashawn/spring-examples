/**
 * 
 */
package com.github.jashawn.examples.springcore.objenesis;

import org.junit.Test;
import org.springframework.objenesis.SpringObjenesis;

import static org.junit.Assert.*;

/**
 * 
 *
 * @author jashawn
 */
public class ObjenesisTest {

	@Test
	public void testCreateWorld() {
		SpringObjenesis obj = new SpringObjenesis();
		World world = obj.newInstance(World.class);

		System.out.println(world.getClass() + ":" + world);
		assertNotNull(world);
	}

	@Test
	public void testCreateCountry() {
		SpringObjenesis obj = new SpringObjenesis();
		Country world = obj.newInstance(Country.class);

		System.out.println(world.getClass() + ":" + world);
		assertNotNull(world);
	}

	@Test
	public void testCreateLanguage() {
		SpringObjenesis obj = new SpringObjenesis();
		Language lang = obj.newInstance(Language.class);

		System.out.println(lang.getClass() + ":" + lang);
		assertNotNull(lang);
		assertNull(lang.name());
	}

	@Test(expected = InstantiationError.class)
	public void testCreateAccessable() {
		SpringObjenesis obj = new SpringObjenesis();
		Accessable acc = obj.newInstance(Accessable.class);

		System.out.println(acc.getClass() + ":" + acc);
		assertNotNull(acc);
	}

}
