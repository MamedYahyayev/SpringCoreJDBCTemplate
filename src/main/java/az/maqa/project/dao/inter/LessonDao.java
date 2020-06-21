package az.maqa.project.dao.inter;

import az.maqa.project.model.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> getLessonList();

    int addLesson(Lesson lesson);

    Lesson getLessonById(Long lessonId);

    int updateLesson(Lesson lesson);

    int delete(Long id);

    List<Lesson> searchLesson(String keyowrd);
}
