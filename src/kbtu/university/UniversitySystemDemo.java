package kbtu.university;

import kbtu.university.enums.*;
import kbtu.university.exceptions.*;
import kbtu.university.model.*;
import kbtu.university.patterns.*;
import kbtu.university.service.*;
import kbtu.university.util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;

public class UniversitySystemDemo {
    public static void main(String[] args) {
        System.out.println("===== Research-Oriented University System Demo version =====");
        System.out.println();

        DataStore dataStore = DataStore.getInstance();

        List<User> users = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        List<Researcher> researchers = new ArrayList<>();

        System.out.println("1) Creating users...");

        Admin admin = new Admin(
                "U001",
                "admin",
                "1234",
                "System Admin",
                "admin@kbtu.kz",
                "E001",
                School.SITE,
                500000
        );

        Manager manager = new Manager(
                "U002",
                "manager",
                "1234",
                "Academic Manager",
                "manager@kbtu.kz",
                "E002",
                School.SITE,
                600000,
                ManagerType.OR_MANAGER
        );

        Student student1 = new Student(
                "U003",
                "student1_aruzhan",
                "123weri567",
                "Aruzhan Sapargali",
                "a_sapargali@kbtu.kz",
                "S001",
                School.SITE,
                DegreeLevel.BACHELOR,
                4
        );

        Student student2 = new Student(
                "U004",
                "student2_dias",
                "123weru567",
                "Dias Omar",
                "d_omar@kbtu.kz",
                "S002",
                School.SITE,
                DegreeLevel.BACHELOR,
                2
        );

        Student student3 = new Student(
                "U005",
                "student3_mike",
                "mike_loves_boxing228",
                "Mike Tyson",
                "m_tyson@kbtu.kz",
                "S003",
                School.SITE,
                DegreeLevel.BACHELOR,
                2
        );

        Teacher professor = new Teacher(
                "U006",
                "professor",
                "qwerty10",
                "Dr. Alan Smith",
                "a_smith@kbtu.kz",
                "T001",
                School.SITE,
                900000,
                TeacherTitle.PROFESSOR
        );

        Teacher lector = new Teacher(
                "U007",
                "lector",
                "1234",
                "Peter Petrovich",
                "p_petrovich@kbtu.kz",
                "T002",
                School.SITE,
                650000,
                TeacherTitle.LECTOR
        );

        ResearchEmployee researchEmployee = new ResearchEmployee(
                "U008",
                "researcher",
                "1234",
                "Alem Alemov",
                "a_alemov@kbtu.kz",
                "R001",
                School.SITE,
                750000,
                5
        );

        admin.addUser(users, admin);
        admin.addUser(users, manager);
        admin.addUser(users, student1);
        admin.addUser(users, student2);
        admin.addUser(users, student3);
        admin.addUser(users, professor);
        admin.addUser(users, lector);
        admin.addUser(users, researchEmployee);

        students.add(student1);
        students.add(student2);
        students.add(student3);

        teachers.add(professor);
        teachers.add(lector);

        researchers.add(professor);
        researchers.add(researchEmployee);

        System.out.println("Users were created and added by admin.");
        System.out.println();

        System.out.println("2) Authentication demo...");

        AuthService authService = new AuthService(users);

        try {
            User loggedUser = authService.login("student1_aruzhan", "123weri567");
            System.out.println("Login successful: " + loggedUser.getFullName());
        } catch (AuthenticationException e) {
            System.out.println("Login failed: " + e.getMessage());
        }

        try {
            authService.login("student1_aruzhan", "wrongPassword");
        } catch (AuthenticationException e) {
            System.out.println("Wrong login example: " + e.getMessage());
        }

        System.out.println();

        System.out.println("3) Creating courses and lessons...");

        Course oop = new Course(
                1,
                "CSS216",
                "Object-Oriented Programming",
                5,
                CourseType.MAJOR,
                "Computer Science"
        );

        Course databases = new Course(
                2,
                "CSS230",
                "Database Systems",
                5,
                CourseType.MAJOR,
                "Computer Science"
        );

        Course researchMethods = new Course(
                3,
                "CSS350",
                "Research Methods",
                6,
                CourseType.MINOR,
                "Computer Science"
        );

        Lesson oopLecture = new Lesson(
                LessonType.LECTURE,
                new Date(),
                "Room 301",
                professor
        );

        Lesson oopPractice = new Lesson(
                LessonType.PRACTICE,
                new Date(),
                "Room 405",
                lector
        );

        oop.addLesson(oopLecture);
        oop.addLesson(oopPractice);

        courses.add(oop);
        courses.add(databases);
        courses.add(researchMethods);

        manager.addCourseForRegistration(courses, oop);
        manager.addCourseForRegistration(courses, databases);

        System.out.println("Courses and lessons were created.");
        System.out.println();

        System.out.println("4) Manager assigns teachers to courses...");

        manager.assignCourseToTeacher(oop, professor);
        manager.assignCourseToTeacher(oop, lector);
        manager.assignCourseToTeacher(databases, lector);
        manager.assignCourseToTeacher(researchMethods, professor);

        System.out.println("OOP course instructors");
        for (Teacher teacher : oop.getInstructors()) {
            System.out.println("- " + teacher.getFullName());
        }

        System.out.println();

        System.out.println("5) Student course registration...");

        try {
            student1.registerCourse(oop);
            student1.registerCourse(databases);
            student1.registerCourse(researchMethods);

            System.out.println(student1.getFullName() + " registered for courses.");
        } catch (CreditLimitException | IllegalArgumentException e) {
            System.out.println("Course registration problem: " + e.getMessage());
        }

        try {
            Course bigCourse = new Course(
                    "CSS999",
                    "Very Big Course",
                    10,
                    CourseType.FREE_ELECTIVE,
                    "Business School"
            );

            student1.registerCourse(bigCourse);
        } catch (CreditLimitException | IllegalArgumentException e) {
            System.out.println("Credit limit check works: " + e.getMessage());
        }

        System.out.println();

        System.out.println("6) Teacher puts marks...");

        Mark mark1 = new Mark(25, 27, 35);
        Mark mark2 = new Mark(20, 23, 30);

        professor.putMark(student1, oop, mark1);
        lector.putMark(student1, databases, mark2);

        System.out.println("Marks were added.");
        System.out.println();

        System.out.println("7) Student transcript...");

        System.out.println(student1.getTranscript());

        System.out.println();

        System.out.println("8) Simple academic report...");

        student1.setGpa(3.5);
        student2.setGpa(3.1);

        manager.createAcademicPerformanceReport(students);

        System.out.println();

        System.out.println("9) Research papers demo...");

        ResearchPaper paper1 = new ResearchPaper(
                "Machine Learning in Education",
                "IEEE Access",
                LocalDate.of(2024, 3, 15),
                35,
                10
        );

        ResearchPaper paper2 = new ResearchPaper(
                "AI Based University Management",
                "Springer Journal",
                LocalDate.of(2023, 6, 10),
                80,
                8
        );

        ResearchPaper paper3 = new ResearchPaper(
                "Data Analysis for Research Projects",
                "ACM Digital Library",
                LocalDate.of(2025, 1, 20),
                20,
                12
        );

        professor.getResearchProfile().addPaper(paper1);
        professor.getResearchProfile().addPaper(paper2);

        researchEmployee.getResearchProfile().addPaper(paper3);

        System.out.println("Professor papers sorted by citations");
        professor.printPapers(ResearchPaperComparators.BY_CITATIONS);

        System.out.println();

        System.out.println("Professor papers sorted by date:");
        professor.printPapers(ResearchPaperComparators.BY_DATE);

        System.out.println();

        System.out.println("10) Research supervisor check...");

        try {
            student1.setResearchSupervisor(professor);
            System.out.println("Supervisor was assigned: " + professor.getFullName());
        } catch (SupervisorHIndexException | IllegalArgumentException e) {
            System.out.println("Supervisor problem: " + e.getMessage());
        }

        Teacher weakResearcher = new Teacher(
                "U008",
                "weak",
                "1234",
                "Weak Researcher",
                "weak@kbtu.kz",
                "T003",
                School.SITE,
                500000,
                TeacherTitle.LECTOR
        );

        weakResearcher.makeResearcher(1);

        try {
            student1.setResearchSupervisor(weakResearcher);
        } catch (SupervisorHIndexException | IllegalArgumentException e) {
            System.out.println("H-index exception works: " + e.getMessage());
        }

        System.out.println();

        System.out.println("11) Research project demo...");

        ResearchProject project = new ResearchProject(
                "Artificial Intelligence for University System",
                "This project is about using AI to improve university management.",
                professor,
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2025, 5, 30)
        );

        project.addParticipant(professor);
        project.addParticipant(researchEmployee);

        project.addPublishedPaper(paper1);
        project.addPublishedPaper(paper3);

        System.out.println("Research project was created");
        System.out.println(project);

        try {
            project.addParticipantWithCheck(student2);
        } catch (NonResearcherProjectJoinException e) {
            System.out.println("Non-researcher exception works: " + e.getMessage());
        }

        System.out.println();

        System.out.println("12) Printing all research papers in university...");

        ResearchService researchService = new ResearchService();

        for (Researcher researcher : researchers) {
            researchService.addResearcher(researcher);
        }

        System.out.println("All papers sorted by citations:");
        researchService.printAllPapers(ResearchPaperComparators.BY_CITATIONS);

        System.out.println();

        System.out.println("All papers sorted by date:");
        researchService.printAllPapers(ResearchPaperComparators.BY_DATE);

        System.out.println();

        System.out.println("All papers sorted by pages:");
        researchService.printAllPapers(ResearchPaperComparators.BY_PAGES);

        System.out.println("13) News observer pattern demo...");

        NewsPublisher newsPublisher = new NewsPublisher();

        newsPublisher.addSubscriber(new NewsSubscriber() {
            @Override
            public void update(String news) {
                System.out.println("Student received news: " + news);
            }
        });

        newsPublisher.addSubscriber(new NewsSubscriber() {
            @Override
            public void update(String news) {
                System.out.println("Teacher received news: " + news);
            }
        });

        newsPublisher.notifySubs("Registration week starts on Monday.");

        System.out.println();

        System.out.println("14) Serialization demo...");

        try {
            for (User user : users) {
                dataStore.addUser(user);
            }

            SerializationService.save(dataStore, "university-data.ser");

            Object loadedObject = SerializationService.load("university-data.ser");

            if (loadedObject instanceof DataStore loadedDataStore) {
                System.out.println("DataStore was saved and loaded successfully.");
                System.out.println("Loaded users count: " + loadedDataStore.getUsers().size());
            } else {
                System.out.println("Loaded object is not DataStore.");
            }

        } catch (Exception e) {
            System.out.println("Serialization problem: " + e.getMessage());
        }

        System.out.println();

        System.out.println("BONUS 1) Attendance demo...");

        AttendanceService attendanceService = new AttendanceService();

        attendanceService.markAttendance(
                student1,
                oop,
                oopLecture,
                LocalDate.now(),
                AttendanceStatus.PRESENT
        );

        attendanceService.markAttendance(
                student1,
                oop,
                oopPractice,
                LocalDate.now(),
                AttendanceStatus.LATE
        );

        attendanceService.printStudentAttendance(student1);

        System.out.println();

        System.out.println("BONUS 2) Recommendation letter demo...");

        RecommendationService recommendationService = new RecommendationService();

        RecommendationLetter letter = recommendationService.createLetter(
                student1,
                professor,
                "I recommend this student for research internship because of strong academic performance and active participation in university research projects."
        );

        letter.printLetter();

        System.out.println();

        System.out.println("===== Demo finished =====");
    }
}
