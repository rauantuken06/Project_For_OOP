package kbtu.university.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private Researcher supervisor;
    private LocalDate startDate;
    private LocalDate endDate;
    private final Set<Researcher> participants;

    public ResearchProject() {
        this.participants = new LinkedHashSet<>();
    }

    public ResearchProject(String title, String description, Researcher supervisor, LocalDate startDate, LocalDate endDate) {
        this();
        setTitle(title);
        setDescription(description);
        setSupervisor(supervisor);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Researcher supervisor) {
        if (supervisor == null) {
            throw new IllegalArgumentException("Supervisor cannot be null.");
        }
        this.supervisor = supervisor;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be empty.");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public Set<Researcher> getParticipants() {
        return participants;
    }

    public void addParticipant(Researcher participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Participant cannot be null.");
        }
        participants.add(participant);
    }

    public void removeParticipant(Researcher participant) {
        participants.remove(participant);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "title='" + title + '\'' +
                ", supervisorHIndex=" + supervisor.getHIndex() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participants=" + participants.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject that)) return false;
        return Objects.equals(title, that.title) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate);
    }
}
