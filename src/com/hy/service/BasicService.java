package com.hy.service;

import com.hy.dao.BasicDao;
import com.hy.model.Speech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yuan on 2015/9/2.
 */
@Service("basicService")
public class BasicService {
    @Autowired
    private BasicDao basicDao;

    public List<Speech> find(String id){
        return basicDao.find(id);
    }
}
