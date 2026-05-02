package kbtu.university.model;

import kbtu.university.enums.LessonType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Lesson implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private LessonType type;
    private Date time;
    private String room;
    private Teacher instructor;

    public Lesson() {
    }

    public Lesson(LessonType type, Date time, String room, Teacher instructor) {
        setType(type);
        setTime(time);
        setRoom(room);
        setInstructor(instructor);
    }

    public boolean isTaughtBy(Teacher teacher) {
        return instructor != null && instructor.equals(teacher);
    }

    @Override
    public Lesson clone() {
        try {
            Lesson copy = (Lesson) super.clone();
            copy.time = this.time == null ? null : new Date(this.time.getTime());
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        if (type == null) {
            throw new IllegalArgumentException("Lesson type cannot be null.");
        }
        this.type = type;
    }

    public Date getTime() {
        return time == null ? null : new Date(time.getTime());
    }

    public void setTime(Date time) {
        if (time == null) {
            throw new IllegalArgumentException("Lesson time cannot be null.");
        }
        this.time = new Date(time.getTime());
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        if (room == null || room.isBlank()) {
            throw new IllegalArgumentException("Room cannot be empty.");
        }
        this.room = room;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor cannot be null.");
        }
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson lesson)) return false;
        return type == lesson.type &&
                Objects.equals(time, lesson.time) &&
                Objects.equals(room, lesson.room) &&
                Objects.equals(instructor, lesson.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, time, room, instructor);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + type +
                ", time=" + time +
                ", room='" + room + '\'' +
                ", instructor=" + (instructor == null ? "not assigned" : instructor.getFullName()) +
                '}';
    }
}
