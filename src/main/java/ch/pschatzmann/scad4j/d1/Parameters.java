package ch.pschatzmann.scad4j.d1;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Manage Parameters in scad files
 * 
 * @author pschatzmann
 *
 */
public class Parameters {
	private Map<String, Object> map = new TreeMap();
	private Parameters parent;

	/**
	 * Default Constructor
	 */
	public Parameters(){
		this.parent = null;
	}
	
	/**
	 * Constructor which provides some inherited parameters
	 * @param parent
	 */
	public Parameters(Parameters parent){
		this.parent = parent;
	}

	/**
	 * Same as add
	 * @param name
	 * @param value
	 * @return
	 */
	public Parameters addParameter(String name, Object value) {
		return this.add(name,value);
	}

	/**
	 * Short form to add a parameter value
	 * @param name
	 * @param value
	 * @return
	 */
	public Parameters add(String name, Object value) {
		map.put(name, value);
		return this;
	}

	public Collection<String> getKeys() {
		Collection<String> result = map.keySet();
		if(parent!=null) {
			result.addAll(parent.getKeys());
		}
		return result;
	}

	public String getValue(String name) {
		Object value = map.get(name);
		if (value==null && parent!=null) {
			value = parent.getValue(name);
		}
		return value.toString();
	}

	/**
	 * Sets a parameter value in a line which contains the parameter
	 * 
	 * @param input to be resolved
	 * @return String
	 */
	public String resolve(String input) {
		String result = input;
		if (input != null) {
			for (String key : getKeys()) {
				int start = getStart(input, key);
				int end = getEnd(input, key);
				if (start >= 0 && end >= 0) {
					StringBuffer sb = new StringBuffer();
					sb.append(input.substring(0, start));
					String value = map.get(key).toString().replaceAll("\"", "");
					sb.append(value);
					sb.append(input.substring(end));
					result = sb.toString();
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Determine the start of the actual value which needs to be replaced
	 * 
	 * @param input
	 * @param key
	 * @return
	 */
	private int getEnd(String input, String key) {
		int pos = input.indexOf(";");
		if (pos >= 0) {
			while (input.substring(pos - 1, pos).equals(" "))
				pos--;
			if (input.substring(pos - 1, pos).equals("\"")) {
				pos--;
			}
		}
		return pos;

	}

	/**
	 * Determines the end of the value which needs to be replaced
	 * 
	 * @param input
	 * @param key
	 * @return
	 */
	private int getStart(String input, String key) {
		int start = input.indexOf(key);
		int pos = start + key.length();		
		boolean equalsFound = false;
		if (start >= 0 && onlyLeadingSpaces(input, start)) {
			int maxLen = input.length()-1;
			while (pos < maxLen && input.substring(pos, pos + 1).equals(" "))
				pos++;
			if (pos < maxLen && input.substring(pos, pos + 1).equals("=")) {
				equalsFound = true;
				pos++;
			}
			while (pos < maxLen && input.substring(pos, pos + 1).equals(" "))
				pos++;
			if (pos < maxLen && input.substring(pos, pos + 1).equals("\"")) {
				pos++;
			}
		}
		return equalsFound ? pos : -1;
	}

	public boolean onlyLeadingSpaces(String input, int pos) {
		String prefix = "";
		if (pos>0) {
		   prefix = input.substring(0,pos-1).trim();
		}
		return prefix.isEmpty();
	}

	
	public Parameters add(Parameters parameters) {
		this.map.putAll(parameters.map);
		return this;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Entry<String,Object> e : map.entrySet()) {
			sb.append(e.getKey());
			sb.append("=");
			sb.append(e.getValue());
			sb.append("; ");
		}
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
