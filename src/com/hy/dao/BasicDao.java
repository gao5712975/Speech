package com.hy.dao;

import com.hy.model.Speech;
import com.mchange.v2.c3p0.ComboPooledDataSource;
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

    private Connection getConnection() {
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public List<Speech> find(String id) {
        List<Speech> list = new ArrayList<Speech>();
        String sql = "select * from v_boyin_keyunzhan where 1=1 ";
//        company_id = 'f9a8fa794de86ecb014df0cf3b43065c'

//        if (id != null && id.trim() != "") {
//            sql += " and company_id = '" + id + "' ";
//        }

        try {
            Connection con = this.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
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
//                s.setTime(result.getString("depart_time"));
//                s.setCarNumber(result.getString("bus_code"));
//                s.setTerminus(result.getString("depot_name"));
//                s.setCheckport(result.getString("ticketgate_name"));
//                s.setCarType(result.getString("BUSTYPE_NAME"));
//                s.setCarUnit(result.getString("buscompany_name"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
