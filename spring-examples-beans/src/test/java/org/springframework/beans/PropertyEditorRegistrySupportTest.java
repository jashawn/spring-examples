/**
 * 
 */
package org.springframework.beans;

import java.beans.PropertyEditor;
import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.propertyeditors.CharsetEditor;

import static org.junit.Assert.*;

/**
 * 
 *
 * @author jashawn
 */
public class PropertyEditorRegistrySupportTest {

	@Test
	public void testName() throws Exception {
		PropertyEditorRegistrySupport propertyEditorRegistry = new PropertyEditorRegistrySupport();

		CharsetEditor e1 = new CharsetEditor();
		propertyEditorRegistry.registerCustomEditor(Charset.class, e1);

		CharsetEditor e2 = new CharsetEditor();
		propertyEditorRegistry.registerCustomEditor(String.class, "a.b", e2);
		
		CharsetEditor e3 = new CharsetEditor();
		propertyEditorRegistry.registerCustomEditor(String.class, "a.b.c", e3);

		PropertyEditor editor = propertyEditorRegistry.findCustomEditor(String.class, "a.b[1]");

		assertNotNull(editor);
		assertSame(e2, editor);
	}
}
