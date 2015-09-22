
package com.hy.service;

import com.hy.dao.BasicDao;
import com.hy.model.Speech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yuan on 2015/9/2.
 */
@Service("basicService")
public class BasicService {
    @Autowired
    private BasicDao basicDao;

    public List<Speech> find(String id)throws SQLException{
        return basicDao.find(id);
    }

    public String login(String user,String password)throws SQLException{
        return basicDao.login(user,password);
    }
}

