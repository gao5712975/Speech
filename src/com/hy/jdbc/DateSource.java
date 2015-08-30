package com.hy.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.util.AppliactionContextHelper;

/**
 * Created by Yuan on 2015/8/30.
 */
public class DateSource {
    private ComboPooledDataSource dataSource = null;

    public ComboPooledDataSource getDataSource() {
        if(dataSource == null){
            dataSource = (ComboPooledDataSource)AppliactionContextHelper.getBean("dataSource",ComboPooledDataSource.class);
        }
        return dataSource;
    }
}
