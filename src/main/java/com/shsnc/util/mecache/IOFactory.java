package com.shsnc.util.mecache;



import java.io.IOException;


public final class IOFactory {
	private static Boolean isInit = Boolean.FALSE;
	private static ISerializable SERIALIZABLE = null;

	static {
		init();
	}

	public static void init() {
		if (isInit.equals(Boolean.FALSE)) {
			synchronized (isInit) {
				if (isInit.equals(Boolean.FALSE)) {
					try {
						SERIALIZABLE = new JavaSerializable();

					} catch (Throwable ex) {
						SERIALIZABLE = new JavaSerializable();
					} finally {
						isInit = Boolean.TRUE;
					}
				}
			}
		}
	}

	public static byte[] object2bytes(Object object) throws IOException {
		return SERIALIZABLE.object2bytes(object);
	}

	public static Object bytes2object(byte[] bytes) throws IOException, ClassNotFoundException {
		return SERIALIZABLE.bytes2object(bytes);
	}
}
