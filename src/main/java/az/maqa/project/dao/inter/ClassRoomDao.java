package az.maqa.project.dao.inter;

import az.maqa.project.model.ClassRoom;

import java.util.List;

public interface ClassRoomDao {

    List<ClassRoom> getClassRoomList();

    int addClass(ClassRoom classRoom);

    ClassRoom getClassRoomById(Long classRoomId);

    int updateClassRoom(ClassRoom classRoom,Long classRoomId);

    int deleteClassRoom(Long classRoomId);

    List<ClassRoom> searchClassRoom(String keyword);
}
