package ch.pschatzmann.scad4j.d1;

public class Parameter extends Number {
	private String value;
	public Parameter(String value) {
		this.value = value;
	}
	@Override
	public double doubleValue() {
		return Double.valueOf(value);
	}

	@Override
	public float floatValue() {
		return Float.valueOf(value);
	}

	@Override
	public int intValue() {
		return Integer.valueOf(value);
	}

	@Override
	public long longValue() {
		return Long.valueOf(value);
	}
	
	public String toString() {
		return this.value;
	}
	
	public static Parameter valueOf(Integer d) {
		return new Parameter(String.valueOf(d));
	}
	public static Parameter valueOf(Double d) {
		return new Parameter(String.valueOf(d));
	}
	public static Parameter valueOf(String s) {
		return new Parameter(s);
	}

}
