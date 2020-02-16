package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Output of Text 
 * 
 * @author pschatzmann
 *
 */
public class Text extends SCAD4JObject {
	public enum HorizontalAligh {
		left, center, right
	}

	public enum VerticallAligh {
		top, center, bottom
	}

	public enum Direction {
		ltr, rtl, ttb, btt
	}

	private String text = "Undefined text";
	private double size;
	private String font;
	private VerticallAligh verticalAligh;
	private HorizontalAligh horizontalAligh;
	private double spacing;
	private Direction direction;
	private String language;
	private String script;
	private boolean first = true;

	public Text text(String text) {
		this.text = text;
		return this;
	}

	public Text size(double size) {
		this.size = size;
		return this;
	}

	public Text font(String font) {
		this.font = font;
		return this;
	}

	public Text verticalAligh(VerticallAligh align) {
		this.verticalAligh = align;
		return this;
	}

	public Text horizontalAligh(HorizontalAligh align) {
		this.horizontalAligh = align;
		return this;
	}

	public Text spacing(double spacing) {
		this.spacing = spacing;
		return this;
	}

	public Text direction(Direction direction) {
		this.direction = direction;
		return this;
	}

	public Text language(String language) {
		this.language = language;
		return this;
	}

	public Text script(String script) {
		this.script = script;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);
		if (text != null) {
			sb.append("text(");
			sb.append(this.text);
			this.first = false;
			appendParameter(sb, "size", size);
			appendParameter(sb, "font", font);
			appendParameter(sb, "halign", horizontalAligh);
			appendParameter(sb, "valign", verticalAligh);
			appendParameter(sb, "spacing", spacing);
			appendParameter(sb, "direction", direction);
			appendParameter(sb, "language", language);
			appendParameter(sb, "script", script);
			sb.append("); ");
		}

	}

	protected void appendParameter(StringBuffer sb, String name, Object value) {
		if (value != null) {
			if (!first) {
				sb.append(",");
			}
			sb.append(name);
			sb.append("=");
			sb.append(value);
			first = false;
		}
	}

}
