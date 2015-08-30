package com.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * 功能描述：配置文件工具类
 *
 * @author 江国湧(jianggy)
 * @date 2015-8-3
 *
 *       <p>
 *       修改历史：(修改人，修改时间，修改原因/内容)
 *       </p>
 */
public class PropertiesUtils {

    /**
     * 地址请求配置文件
     */
    public final static Map<String, Object> configMap = getPropertiesFileToMap("config.properties");

    /**
     *
     * 功能描述：将properties属性文件转换成Map（根据当前环境）
     *
     * @author 江国湧(jianggy)
     *         <p>
     *         创建日期 ：2015-8-3
     *         </p>
     *
     * @param
     * @return
     *
     *         <p>
     *         修改历史 ：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getPropertiesFileToMap(String fileName) {
        /** 默认先读取开发环境测试配置文件，避免新增参数导致程序错误，实现新增参数未找到则使用开发测试环境配置文件 **/
        Properties p;
        p = PropertiesUtils.classPathPropertis(fileName);
        Enumeration en = p.propertyNames();
        Map<String, Object> map = new HashMap<String, Object>();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = p.getProperty(key);
            map.put(key, value);

        }
        return map;
    }

    /**
     *
     * 功能描述：从classpath 路径获取属性文件
     *
     * @author 江国湧(jianggy)
     *         <p>
     *         创建日期 ：2015-8-3
     *         </p>
     *
     * @param
     * @return
     *
     *         <p>
     *         修改历史 ：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    private static Properties classPathPropertis(String path) {
        InputStream is = PropertiesUtils.class.getClassLoader()
                .getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(is);
            return properties;
        } catch (Exception e) { // 出错情况直接返回空（该环境未提供配置文件）
            return null;
        }
    }
}
