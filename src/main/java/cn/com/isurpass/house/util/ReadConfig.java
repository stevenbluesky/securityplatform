package cn.com.isurpass.house.util;
//测试
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	private ReadConfig() {
	}

	private static final Logger log = LoggerFactory.getLogger(ReadConfig.class);
	private static final String fileName = "/security.properties";
	private static final Properties properties = new Properties();
	static {
		try {
			InputStream inputStream = ReadConfig.class.getResourceAsStream(fileName);
			properties.load(inputStream);
		} catch (IOException e) {
			log.error("Load Properties error！", e);
		} catch (Throwable e) {
			log.error("Load Properties error！", e);
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
}
