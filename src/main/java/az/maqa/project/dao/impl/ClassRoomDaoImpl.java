package az.maqa.project.dao.impl;

import az.maqa.project.dao.inter.ClassRoomDao;
import az.maqa.project.model.ClassRoom;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomDaoImpl implements ClassRoomDao {

    @Autowired
    private DataSource dataSource;


    public List<ClassRoom> getClassRoomList() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from qanda.class where qanda.class.active = 1";
        List<ClassRoom> classRoomList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ClassRoom.class));
        return classRoomList;
    }

    public int addClass(ClassRoom classRoom) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "insert into qanda.class(class_number) values(?)";
        int result = jdbcTemplate.update(sql, new Object[]{classRoom.getClassNumber()});
        return result;
    }

    public ClassRoom getClassRoomById(Long classRoomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from qanda.class where qanda.class.active = 1 and qanda.class.id = ?";
        List<ClassRoom> classRoomList = jdbcTemplate.query(sql, new Object[]{classRoomId}, new BeanPropertyRowMapper(ClassRoom.class));
        if (classRoomList.size() > 0) {
            return classRoomList.get(0);
        } else {
            return null;
        }
    }

    public int updateClassRoom(ClassRoom classRoom, Long classRoomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "update qanda.class set qanda.class.class_number = ? where qanda.class.id = ?";
        int result = jdbcTemplate.update(sql, new Object[]{classRoom.getClassNumber(), classRoomId});
        return result;
    }

    public int deleteClassRoom(Long classRoomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "update qanda.class set active = 0 where qanda.class.id = ?";
        int result = jdbcTemplate.update(sql, new Object[]{classRoomId});
        return result;
    }

    public List<ClassRoom> searchClassRoom(String keyword) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from qanda.class where active = 1 and lower(class_number) like lower(?)";
        List<ClassRoom> classRoomList = jdbcTemplate.query(sql, new Object[]{"%" + keyword + "%"}, new BeanPropertyRowMapper(ClassRoom.class));
        return classRoomList;
    }
}
