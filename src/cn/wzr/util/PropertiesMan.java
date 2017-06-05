package cn.wzr.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取类
 * @author root
 *
 */
public class PropertiesMan {

	private String ppFilename;
	
	/**
	 * 在PropertiesMan类内部保存配置文件对象
	 */
	private Properties properties;

	/**
	 * 
	 * @param ppFilename
	 *            :Properties配置文件名(含全路径)
	 */
	public PropertiesMan(String ppFilename) {
		properties = new Properties();
		this.ppFilename = ppFilename;
	}

	/**
	 * 通过key取value
	 * 
	 * @param keyName:键名
	 *            
	 * @return:成功返回value,失败返回空(null)
	 */
	public String getValue(String keyName) {
		String value;
		InputStream inputStream = null;
		if (properties.isEmpty()) {
			try {
				inputStream = new FileInputStream(ppFilename);
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		value = properties.getProperty(keyName).trim();
		return value;

	}

	public String getPpFilename() {
		return ppFilename;
	}

	public void setPpFilename(String ppFilename) {
		this.ppFilename = ppFilename;
	}

}
