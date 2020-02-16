package ch.pschatzmann.scad4j.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

/**
 * Common Utility Functions
 * 
 * @author pschatzmann
 *
 */

public class Utils {

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static String getCommand(String env, String defaultCommand) {
		String command = defaultCommand;
		String p = System.getenv(env);
		if (p != null && !p.isEmpty()) {
			command = p;
		} else {
			p = System.getProperty(env);
			if (p != null && !p.isEmpty()) {
				command = p;
			}
		}
		return command;
	}

	public static void execCommand(String command, File input, File output) throws IOException, InterruptedException {
		String cmd = command + " -o " + output.getAbsolutePath() + " " + input.getAbsolutePath();
		Process p = Runtime.getRuntime().exec(cmd);
		Utils.displayErrors(p);
	}

	/**
	 * Creates a temporary file with the indicated content and extension.
	 * 
	 * @param content
	 * @param ext
	 * @return
	 * @throws IOException
	 */
	public static File createTempFile(String content, String ext) throws IOException {
		File result = createTempFile(ext);
		write(content, result);
		return result;
	}

	public static File createTempFile(String ext) throws IOException {
		File result = File.createTempFile("tmp-", "." + ext);
		return result;
	}

	public static void write(String content, File result) throws IOException {
		FileWriter writer = new FileWriter(result, false);
		writer.write(content);
		writer.flush();
		writer.close();
	}

	protected static void displayErrors(Process p) throws IOException, InterruptedException {
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String line = null;
		while ((line = errorReader.readLine()) != null) {
			System.err.println(line);
		}

		int exitVal = p.waitFor();
		if (exitVal != 0) {
			System.err.println("Exited with error code " + exitVal);
		}
	}

	public static String binaryFile2String(File file) throws IOException {
		String base64File = "";
		FileInputStream imageInFile = new FileInputStream(file);
		byte fileData[] = new byte[(int) file.length()];
		imageInFile.read(fileData);
		base64File = Base64.getEncoder().encodeToString(fileData);
		return base64File;
	}
	
	public static String textFile2String(File file) throws IOException {
		Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
		StringBuilder contentBuilder = new StringBuilder();
		stream.forEach(s -> contentBuilder.append(s).append(System.lineSeparator()));
		return contentBuilder.toString();
	}



}
