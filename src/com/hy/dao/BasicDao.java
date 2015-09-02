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
 *
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
        String sql = "select sys_guid() ID , to_char(t.depart_time, 'HH24:mi') 发车时间,t.bus_code 车次, \n" +
                "(select depot_name from t_depot where depot_id = t.e_depot_id)终点站, \n" +
                "(select e.ticketgate_namefrom t_ebc_depot c, t_line_depot d, t_ticketgate e where e.ticketgate_id = c.ticketgate_id and c.linedepot_id = d.linedepot_id and c.ebc_id = t.ebc_id and d.depot_id = t.i_depot_id) checkport, \n" +
                "substr((select BUSTYPE_NAME from t_bustype where bustype_id = t.BUS_TYPE), 0, 4) 车型, \n" +
                "(case when BUSINFO_ID is not null then (select b.buscompany_name from t_businfo a, t_buscompany b where a.buscompany_id = b.buscompany_id and businfo_id = t.businfo_id) \n" +
                "else (select b.buscompany_name from t_businfo a, t_buscompany b where a.buscompany_id = b.buscompany_id and businfo_id = t.ebc_businfo_id) end) 车属单位 \n" +
                "from t_exec_buscode t where t.is_valid = 1 and t.ebc_state = '042001' and trunc(t.depart_time) = trunc(sysdate) ";

        if (id != null && id.trim() != "") {
            sql += " and t.company_id = '" + id + "' ";
        }
        String orderBy = " order by 发车时间 asc ;";
        sql += orderBy;
        try {
            Connection con = this.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Speech s = new Speech();
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
