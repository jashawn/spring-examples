/**
 * 
 */
package org.springframework.beans;

import java.util.Map;

/**
 * 
 *
 * @author jashawn
 */
public class ManyMap {

	private Map<String, String> strmap;
	private Map<String, String> props;

	public Map<String, String> getStrmap() {
		return strmap;
	}

	public void setStrmap(Map<String, String> strmap) {
		this.strmap = strmap;
	}

	public Map<String, String> getProps() {
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}

}
