
package com.hy.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.util.ApplicationContextHelper;
/**
 * Created by Yuan on 2015/8/30.
 */
public class DateSource {
    private static ComboPooledDataSource dataSource = null;

    public static ComboPooledDataSource getDataSource() {
        if(dataSource == null){
            dataSource = (ComboPooledDataSource)ApplicationContextHelper.getBean("dataSource",ComboPooledDataSource.class);
        }
        return dataSource;
    }


}

