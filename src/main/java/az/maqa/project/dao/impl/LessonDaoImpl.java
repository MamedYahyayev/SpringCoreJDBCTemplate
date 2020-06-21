package az.maqa.project.dao.impl;

import az.maqa.project.dao.inter.LessonDao;
import az.maqa.project.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class LessonDaoImpl implements LessonDao {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Lesson> getLessonList() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from qanda.lesson where  active = 1";
        List<Lesson> lessonList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Lesson.class));
        return lessonList;
    }

    @Override
    public int addLesson(Lesson lesson) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "insert into qanda.lesson(lesson_name) values(?)";
        int result = jdbcTemplate.update(sql, new Object[]{lesson.getLessonName()});
        return result;
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from qanda.lesson where active = 1 and id = ?";
        List<Lesson> lessonList = jdbcTemplate.query(sql, new Object[]{lessonId}, new BeanPropertyRowMapper(Lesson.class));
        if (lessonList.size() > 0) {
            return lessonList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int updateLesson(Lesson lesson) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "update qanda.lesson set lesson_name = ? where id = ?";
        int result = jdbcTemplate.update(sql, new Object[]{lesson.getLessonName(), lesson.getId()});
        return result;
    }

    @Override
    public int delete(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "update qanda.lesson set active = 0 where id = ?";
        int result = jdbcTemplate.update(sql, new Object[]{id});
        return result;
    }

    @Override
    public List<Lesson> searchLesson(String keyowrd) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from qanda.lesson where  active = 1 and lower(lesson_name) like lower(?)";
        List<Lesson> lessonList = jdbcTemplate.query(sql, new Object[]{"%" + keyowrd + "%"}, new BeanPropertyRowMapper(Lesson.class));
        return lessonList;
    }
}
