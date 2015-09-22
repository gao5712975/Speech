package com.hy.dao;

import com.hy.model.Speech;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yxd.akframe.util.encrypt.TripleDes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan on 2015/9/2.
 */
@Repository("basicDao")
public class BasicDao {
    @Autowired
    private ComboPooledDataSource dataSource;

    private Connection connection = null;
    private ResultSet result = null;
    private PreparedStatement pre = null;

    public List<Speech> find(String id) throws SQLException {
        List<Speech> list = new ArrayList<Speech>();
        String sql = "select * from v_boyin_keyunzhan where 1=1 ";

        if (id != null && id.trim() != "") {
            sql += " and company_id = '" + id + "'";
        }

        if (connection == null) {
            connection = dataSource.getConnection();
        }
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet result = pre.executeQuery();
        while (result.next()) {
            Speech s = new Speech();
            s.setId(result.getString("ID"));
            s.setTime(result.getString("发车时间"));
            s.setCarNumber(result.getString("车次"));
            s.setTerminus(result.getString("终点站"));
            s.setCheckport(result.getString("CHECKPORT"));
            s.setCarType(result.getString("车型"));
            s.setCarUnit(result.getString("车属单位"));
            list.add(s);
        }
        return list;
    }

    public String login(String user, String password) throws SQLException {
        String sql = "select company_id ID from t_akf_member where 1=1 ";
        sql += "and  login_name='" + user + "' and login_password='" + TripleDes.encrypt(password, null) + "'";
        String id = "";
        System.out.println(sql);
        if (connection == null) {
            connection = dataSource.getConnection();
        }
        PreparedStatement pre = connection.prepareStatement(sql);
        System.out.println(TripleDes.decrypt("E24F6954DC441B34", null));
        System.out.println(TripleDes.encrypt(password, null));
        ResultSet result = pre.executeQuery();
        while (result.next()) {
            id = result.getString("ID");
            System.out.println("id:" + id);
        }
        return id;
    }
}
