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

/**
 * We re-use saved SCAD code which can be loaded from a file or URL
 * 
 * @author pschatzmann
 *
 */
public class SCADObject extends SCAD4JObject {
	private String commands;

	public SCADObject(){
		super();
	}

	public SCADObject(String cmd){
		this.commands = cmd;
	}

	public SCADObject load(File file, Parameters parameters) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
		Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));
		stream.forEach(s -> contentBuilder.append(parameters.resolve(s)).append(System.lineSeparator()));
		commands = contentBuilder.toString();

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
			commands = sb.toString();
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
		
		this.commands = sb.toString();
		return this;
	}
	

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append(commands);
	}
	
	/**
	 * Returns the command added to the current scad commands. The model is not updated. You might
	 * use this to trigger the rendering of some parts to be displayed on the screen.
	 * @param command
	 * @return
	 */
	
	public SCADObject evaluate(String command) {
		return new SCADObject(this.commands + System.lineSeparator()+command);
	}

	
	/** 
	 * Removes the indicated string from the commands
	 * @param regex
	 * @return
	 */
	public SCADObject remove(String regex) {
		String newCommands = commands.replaceAll(regex,"");
		return new SCADObject(newCommands);
	}
	
	@Override
	public String toString() {
		return this.commands;
	}
	

}
