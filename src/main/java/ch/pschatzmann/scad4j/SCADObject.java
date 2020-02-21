package ch.pschatzmann.scad4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import ch.pschatzmann.scad4j.d1.Parameters;
import ch.pschatzmann.scad4j.format.ModuleParser;

/**
 * We re-use saved SCAD code which can be loaded from a file or URL
 * 
 * @author pschatzmann
 *
 */
public class SCADObject extends SCAD4JObject {
	private String commands;
	private String entryPoint;
	private ModuleParser moduleParser = new ModuleParser();


	public SCADObject(SCAD scad, String cmd){
		super(scad);
		this.setSCAD(cmd);
	}

	public SCADObject(SCAD scad) {
		super(scad);
	}

	public SCADObject load(File file, Parameters parameters) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
		Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));
		stream.forEach(s -> contentBuilder.append(parameters.resolve(s)).append(System.lineSeparator()));
		this.setSCAD(contentBuilder.toString());

		return this;
	}
	

	public SCADObject load(URL url,Parameters parameters) throws IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer sb = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(parameters.resolve(inputLine)+System.lineSeparator());
			}
			this.setSCAD(sb.toString());
		} finally {
			if (in!=null) in.close();
		}
		return this;
	}
	
	/**
	 * Defines the current SCAD comands
	 * @param scad
	 * @return
	 */
	public SCADObject setSCAD(String scad) {
		this.commands = scad;
		return this;
	}
	
//	public void resolveModules() {
//		moduleParser.parse(this.getParent(), this.commands);
//		this.setSCAD(moduleParser.getContent());
//	}
//
	/**
	 * Returns the SCAD Commands
	 * @return
	 */
	public String getSCAD() {
		return this.commands;
	}


	public SCADObject load(String scadCommands,Parameters parameters) {
		String[] sa = scadCommands.split(System.lineSeparator());
		StringBuffer sb = new StringBuffer();
		for (String str : sa) {
			sb.append(parameters.resolve(str)+System.lineSeparator());
		}
		
		this.setSCAD(sb.toString());
		return this;
	}
	

	@Override
	public void appendSCAD(StringBuffer sb) {
		if (this.getEntryPoint()==null) {
			appendActions(this.getActions(),sb);
			sb.append(commands);
		} else {
			// remove the entry point
			String raw = this.commands.replace(this.getEntryPoint(),"");
			sb.append(raw);
			// add entry point back with the relevant operations
			sb.append(System.lineSeparator());
			appendActions(this.getActions(),sb);
			sb.append(" ");
			sb.append(this.getEntryPoint());			
			sb.append(System.lineSeparator());
		}
	}
	
	
	
	/**
	 * Returns the command added to the current scad commands. The model is not updated. You might
	 * use this to trigger the rendering of some parts to be displayed on the screen.
	 * @param command
	 * @return
	 */
	
	public SCADObject evaluate(String command) {
		return new SCADObject(this.getParent(),this.commands + System.lineSeparator()+command);
	}

	
	/** 
	 * Removes the indicated string from the commands
	 * @param regex
	 * @return
	 */
	public SCADObject remove(String regex) {
		String newCommands = commands.replaceAll(regex,"");
		return new SCADObject(this.getParent(), newCommands);
	}

	/**
	 * The module call which will display the object in openscad
	 * @return
	 */
	public String getEntryPoint() {
		return entryPoint;
	}

	/**
	 * Defines the module call which will display the object in openscad
	 * @param entryPoint
	 */
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	

}
