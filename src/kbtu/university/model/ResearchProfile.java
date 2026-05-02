package kbtu.university.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchProfile implements Researcher, Serializable {
    private static final long serialVersionUID = 1L;

    private int hIndex;
    private final List<ResearchPaper> papers;
    private final List<ResearchProject> projects;

    public ResearchProfile() {
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public ResearchProfile(int hIndex) {
        this();
        setHIndex(hIndex);
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    public void setHIndex(int hIndex) {
        if (hIndex < 0) {
            throw new IllegalArgumentException("H-index cannot be negative.");
        }
        this.hIndex = hIndex;
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return papers;
    }

    public List<ResearchProject> getResearchProjects() {
        return projects;
    }

    public void addPaper(ResearchPaper paper) {
        if (paper == null) {
            throw new IllegalArgumentException("Research paper cannot be null.");
        }
        papers.add(paper);
    }

    public void addProject(ResearchProject project) {
        if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null.");
        }
        if (!projects.contains(project)) {
            projects.add(project);
        }
    }

    public void removeProject(ResearchProject project) {
        projects.remove(project);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sortedPapers = new ArrayList<>(papers);
        if (comparator != null) {
            sortedPapers.sort(comparator);
        }
        for (ResearchPaper paper : sortedPapers) {
            System.out.println(paper);
        }
    }
}
