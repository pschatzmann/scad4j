package ch.pschatzmann.scad4j.format;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

/**
 * Returns a com.twosigma.beakerx.mimetype.MIMEContainer. The Jupyter 3D mine viewer expects
 * Base64 encoded content for all 3D data types
 * 
 * @author pschatzmann
 *
 */
public class Mime {
	public static boolean defaultEncoded = true;

	public static Object display(File file) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		Path path = file.toPath();
		String mimeType = Files.probeContentType(path);
		return display(mimeType,file);
	}
	
	public static Object display(String mime, File file) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		return display(mime, file, defaultEncoded);
	}

	public static Object display(String mime, File file, boolean encoded)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		return encoded ? display(mime, Utils.binaryFile2String(file)): display(mime, Utils.textFile2String(file));
	}

	public static Object display(String mime, Object content) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return display(mime, content, defaultEncoded);
	}
	
	public static Object display(String mime, Object content, boolean encoded)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		Object resultContent = encoded ? encode(content) : content;
		Class mimeContainer = Class.forName("com.twosigma.beakerx.mimetype.MIMEContainer");
		Constructor constructor = mimeContainer.getConstructor(String.class, Object.class);
		Object[] args = { mime, resultContent };
		Object result = constructor.newInstance(args);
		return result;
	}
	
	public static Object encode(Object obj) {
		return obj instanceof String ? Base64.getEncoder().encodeToString(((String)obj).getBytes()) : obj;
	}

	public static String decode(String obj) {
		return new String(Base64.getDecoder().decode(obj.getBytes()));
	}
	
}
