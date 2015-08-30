package com.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * ���������������ļ�������
 *
 * @author ������(jianggy)
 * @date 2015-8-3
 *
 *       <p>
 *       �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 *       </p>
 */
public class PropertiesUtils {

    /**
     * ��ַ���������ļ�
     */
    public final static Map<String, Object> configMap = getPropertiesFileToMap("config.properties");

    /**
     *
     * ������������properties�����ļ�ת����Map�����ݵ�ǰ������
     *
     * @author ������(jianggy)
     *         <p>
     *         �������� ��2015-8-3
     *         </p>
     *
     * @param
     * @return
     *
     *         <p>
     *         �޸���ʷ ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
     *         </p>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getPropertiesFileToMap(String fileName) {
        /** Ĭ���ȶ�ȡ�����������������ļ������������������³������ʵ����������δ�ҵ���ʹ�ÿ������Ի��������ļ� **/
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
     * ������������classpath ·����ȡ�����ļ�
     *
     * @author ������(jianggy)
     *         <p>
     *         �������� ��2015-8-3
     *         </p>
     *
     * @param
     * @return
     *
     *         <p>
     *         �޸���ʷ ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
     *         </p>
     */
    private static Properties classPathPropertis(String path) {
        InputStream is = PropertiesUtils.class.getClassLoader()
                .getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(is);
            return properties;
        } catch (Exception e) { // �������ֱ�ӷ��ؿգ��û���δ�ṩ�����ļ���
            return null;
        }
    }
}
