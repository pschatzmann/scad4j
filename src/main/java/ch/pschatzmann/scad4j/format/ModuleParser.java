package ch.pschatzmann.scad4j.format;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCADObject;

public class ModuleParser {
	private String content;
	
	public void parse(SCAD scad, String str){
		int pos = str.indexOf("module");
		int endPos=0;
		String head="";
		String tail="";
		
		if (pos>0) {
			head = str.substring(0,pos);
			while (pos>0) {
				if (pos>0) {
					endPos = findEnd(pos, str);
					String module = str.substring(pos, endPos);
					String name = getName(module);
					scad.modules().add(name, new SCADObject(scad, getContent(module)));
				}
				pos = str.indexOf("module");
			}
		}
		if (endPos>0) {
			tail = str.substring(endPos);
		}
		parseParameters(scad, head+System.lineSeparator()+tail);
	}
	
	private void parseParameters(SCAD scad, String string) {
		StringBuffer sb = new StringBuffer();
		for (String line : string.split(System.lineSeparator())) {
			if (line.contains("=")&& line.contains(";")) {
				String name = getParameterName(line);
				String value = getParameterValue(line);
				scad.addParameter(name,value);
				
			} else {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		}
		this.content = sb.toString();
	}

	private String getParameterValue(String line) {
		int start = line.indexOf("=");
		int end = line.indexOf(";");
		return line.substring(start+1,end-1).trim();
	}

	private String getParameterName(String line) {
		int end = line.indexOf("=");
		return line.substring(0,end-1).trim();
	}

	public String getContent() {
		return content;
	}
	
	private int findEnd(int pos, String str) {
		int count = 0;
		boolean opened = false;
		for (int j=pos; j<str.length();j++) {
			char c = str.charAt(j);
			if (c=='{') {
				count++;
				opened = true;
			}
			if (c=='}') {
				count--;
				if (opened && count==0) {
					return j;
				}
			}
		}
		return str.length()-1;
	}
	
	private String getName(String module) {
		int start = module.indexOf(" ");
		int end1 = module.indexOf("(");
		int end2 = module.indexOf("{");
		int end = Math.min(end1,end2);
		String name = module.substring(start,end);
		return name;
	}
	
	private String getContent(String module) {
		int start = module.indexOf("{");
		String name = module.substring(start);
		return name;
	}
	
	
}
