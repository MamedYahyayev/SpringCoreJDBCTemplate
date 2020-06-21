package az.maqa.project.main;

import az.maqa.project.dao.inter.ClassRoomDao;
import az.maqa.project.dao.inter.LessonDao;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.model.Lesson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ClassRoomDao classRoomDao = (ClassRoomDao) applicationContext.getBean("classRoomDao");
        LessonDao lessonDao = (LessonDao) applicationContext.getBean("lessonDao");
        System.out.println("Please choose one of theses:classRoom , lesson ");
        switch (sc.next()) {
            case "classRoom":
                System.out.println("Choose one of theses:view , add , update , delete , search");
                switch (sc.next()) {
                    case "view":
                        List<ClassRoom> classRoomList = classRoomDao.getClassRoomList();
                        for (ClassRoom classRoom : classRoomList) {
                            System.out.println(classRoom.getId() + "--" + classRoom.getClassNumber());
                        }
                        break;
                    case "add":
                        System.out.print("Add Class Number:");
                        String classNumber = sc.next();
                        ClassRoom classRoom = new ClassRoom();
                        classRoom.setClassNumber(classNumber);
                        result = classRoomDao.addClass(classRoom);
                        if (result == 1) {
                            System.out.println("ClassRoom is added...");
                        }
                        break;
                    case "update":
                        classRoomList = classRoomDao.getClassRoomList();
                        for (ClassRoom classRoomU : classRoomList) {
                            System.out.println(classRoomU.getId() + "--" + classRoomU.getClassNumber());
                        }
                        System.out.print("Please choose id of ClassRoom Table:");
                        long classRoomId = sc.nextLong();
                        classRoom = classRoomDao.getClassRoomById(classRoomId);
                        String oldClassNumber = classRoom.getClassNumber();
                        System.out.println(oldClassNumber);
                        System.out.print("Please enter the new ClassNumber to ClassRoom table:");
                        String newClassNumber = sc.next();
                        classRoom.setClassNumber(newClassNumber);
                        result = classRoomDao.updateClassRoom(classRoom, classRoomId);
                        if (result == 1) {
                            System.out.println("ClassRoom is updated...");
                        }
                        break;
                    case "delete":
                        classRoomList = classRoomDao.getClassRoomList();
                        for (ClassRoom classRoomD : classRoomList) {
                            System.out.println(classRoomD.getId() + "--" + classRoomD.getClassNumber());
                        }
                        System.out.print("Please choose classNumber which you want to delete:");
                        long classRoomIdD = sc.nextLong();
                        result = classRoomDao.deleteClassRoom(classRoomIdD);
                        if (result == 1) {
                            System.out.println("ClassRoom is Deleted...");
                        }
                        break;
                    case "search":
                        System.out.print("Enter the kewyord for search on the ClassRoom Table:");
                        String keyword = sc.next();
                        classRoomList = classRoomDao.searchClassRoom(keyword);
                        for (ClassRoom classRoomS : classRoomList) {
                            System.out.println(classRoomS.getId() + "--" + classRoomS.getClassNumber());
                        }
                        break;
                    default:
                        System.err.println("Please choose one of theses:view , add , update , delete , search ");
                        break;


                }
                break;
            case "lesson":
                System.out.println("Choose one of theses:view , add , update , delete , search");
                switch (sc.next()) {
                    case "view":
                        List<Lesson> lessonList = lessonDao.getLessonList();
                        for (Lesson lesson : lessonList) {
                            System.out.println(lesson.getId() + "--" + lesson.getLessonName());
                        }
                        break;
                    case "add":
                        System.out.print("Enter the lessonName:");
                        String lessonName = sc.next();
                        Lesson lesson = new Lesson();
                        lesson.setLessonName(lessonName);
                        result = lessonDao.addLesson(lesson);
                        if (result == 1) {
                            System.out.println("LessonName is added...");
                        }
                        break;
                    case "update":
                        lessonList = lessonDao.getLessonList();
                        for (Lesson lessonU : lessonList) {
                            System.out.println(lessonU.getId() + "--" + lessonU.getLessonName());
                        }
                        System.out.print("Please choose id of Lesson Table:");
                        Long lessonId = sc.nextLong();
                        lesson = lessonDao.getLessonById(lessonId);
                        System.out.println(lesson.getLessonName());
                        System.out.print("Enter the new LessonName:");
                        String newLessonName = sc.next();
                        lesson.setLessonName(newLessonName);
                        result = lessonDao.updateLesson(lesson);
                        if (result == 1) {
                            System.out.println("LessonName is updated...");
                        }
                        break;
                    case "delete":
                        lessonList = lessonDao.getLessonList();
                        for (Lesson lessonU : lessonList) {
                            System.out.println(lessonU.getId() + "--" + lessonU.getLessonName());
                        }
                        System.out.print("Please choose id of Lesson Table:");
                        lessonId = sc.nextLong();
                        result = lessonDao.delete(lessonId);
                        if (result == 1)
                            System.out.println("Lesson is deleted...");
                        break;

                    case "search":
                        System.out.print("Enter the kewyord for search on the Lesson Table:");
                        String keyword = sc.next();
                        lessonList = lessonDao.searchLesson(keyword);
                        for (Lesson lessonS : lessonList) {
                            System.out.println(lessonS.getId() + "--" + lessonS.getLessonName());
                        }
                        break;
                    default:
                        System.err.println("Please choose one of theses:view , add , update , delete , search ");
                        break;
                }
                break;

            default:
                System.err.println("Please choose one of theses:classRoom , lesson ");
                break;
        }


        main(args);
    }
}
