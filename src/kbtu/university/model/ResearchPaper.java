package kbtu.university.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ResearchPaper implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String journal;
    private LocalDate publishedDate;
    private int citations;
    private int pages;

    public ResearchPaper() {
    }

    public ResearchPaper(String title, String journal, LocalDate publishedDate, int citations, int pages) {
        setTitle(title);
        setJournal(journal);
        setPublishedDate(publishedDate);
        setCitations(citations);
        setPages(pages);
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

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        if (journal == null || journal.isBlank()) {
            throw new IllegalArgumentException("Journal cannot be empty.");
        }
        this.journal = journal;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        if (publishedDate == null) {
            throw new IllegalArgumentException("Published date cannot be empty.");
        }
        this.publishedDate = publishedDate;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        if (citations < 0) {
            throw new IllegalArgumentException("Citations cannot be negative.");
        }
        this.citations = citations;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive.");
        }
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", journal='" + journal + '\'' +
                ", publishedDate=" + publishedDate +
                ", citations=" + citations +
                ", pages=" + pages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper that)) return false;
        return Objects.equals(title, that.title) &&
                Objects.equals(journal, that.journal) &&
                Objects.equals(publishedDate, that.publishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, journal, publishedDate);
    }
}
