package com.github.andyshao.lang;

public final class PublicKey {
	private PublicKey() {
		throw new AssertionError("No " + PublicKey.class.getName() + " instances for you!");
	}

	public static final String OS_NAME = System.getProperty("os.name");
	public static final String OS_ARC = System.getProperty("os.arch");
	public static final String OS_VERSION = System.getProperty("os.version");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String PATH_SEPARATOR = System.getProperty("path.separator");
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String USER_NAME = System.getProperty("user.name");
	public static final String USER_HOME = System.getProperty("user.home");
	public static final String USER_DIR = System.getProperty("user.dir");
}
