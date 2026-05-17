package kbtu.university.service;

import kbtu.university.enums.AttendanceStatus;
import kbtu.university.model.AttendanceRecord;
import kbtu.university.model.Course;
import kbtu.university.model.Lesson;
import kbtu.university.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private final List<AttendanceRecord> records = new ArrayList<>();

    public void markAttendance(Student student,
                               Course course,
                               Lesson lesson,
                               LocalDate date,
                               AttendanceStatus status) {

        if (status == null) {
            throw new IllegalArgumentException("Attendance status cannot be null in AttendanceService.");
        }

        AttendanceRecord record = new AttendanceRecord(
                student,
                course,
                lesson,
                date,
                status
        );

        records.add(record);
    }

    public List<AttendanceRecord> getRecordsByStudent(Student student) {
        List<AttendanceRecord> result = new ArrayList<>();

        for (AttendanceRecord record : records) {
            if (record.getStudent().equals(student)) {
                result.add(record);
            }
        }

        return result;
    }

    public double getAttendancePercentage(Student student) {
        int total = 0;
        int attended = 0;

        for (AttendanceRecord record : records) {
            if (record.getStudent().equals(student)) {
                total++;

                if (record.getStatus() == AttendanceStatus.PRESENT ||
                        record.getStatus() == AttendanceStatus.LATE) {
                    attended++;
                }
            }
        }

        if (total == 0) {
            return 0.0;
        }

        return attended * 100.0 / total;
    }

    public void printStudentAttendance(Student student) {
        List<AttendanceRecord> studentRecords = getRecordsByStudent(student);

        System.out.println("Attendance for " + student.getFullName() + ":");

        for (AttendanceRecord record : studentRecords) {
            System.out.println(record);
        }

        System.out.println("Attendance percentage: " + getAttendancePercentage(student) + "%");
    }
}
