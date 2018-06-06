package com.clipperl.springboottest.dao;

import com.clipperl.springboottest.model.DeveloperModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("developerDAOImpl")
public class DeveloperDAOImpl implements DeveloperDAO {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DeveloperModel> getAllDevelopers() {
        String sql = "select * from developer";
        return query(sql);
    }

    @Override
    public DeveloperModel getDeveloper(String developerId) {
        String sql = "select * from developer where id=" + developerId;
        List<DeveloperModel> developerList = query(sql);
        if (developerList.size() > 0) {
            return developerList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public boolean addDeveloper(DeveloperModel developerModel) {
        String sql = " INSERT INTO developer(name,site,avatar) VALUES(" +
                "'" + developerModel.getName() + "'," +
                "'" + developerModel.getSite() + "'," +
                "'" + developerModel.getAvatar() + "'" + ");";
        System.out.println("sql=" + sql);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDeveloper(String id, String name) {
        String sql = "UPDATE developer SET name='" + name +"' WHERE id=" +id;
        System.out.println("sql = " + sql);
        try {
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDeveloper(String id) {
        String sql = "DELETE FROM developer WHERE id=" + id;
        System.out.println("sql = " + sql);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<DeveloperModel> query(String sql) {
        final List<DeveloperModel> developerList = new ArrayList<>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
                developerList.add(developerModel);
            }
        });
        return developerList;
    }
}
