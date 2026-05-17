package kbtu.university.service;

import kbtu.university.enums.School;
import kbtu.university.exceptions.NonResearcherProjectJoinException;
import kbtu.university.exceptions.SupervisorHIndexException;
import kbtu.university.model.Employee;
import kbtu.university.model.ResearchPaper;
import kbtu.university.model.ResearchProject;
import kbtu.university.model.Researcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchService {
    public static final int MIN_SUPERVISOR_H_INDEX = 3;

    private final List<Researcher> researchers;

    public ResearchService() {
        this.researchers = new ArrayList<>();
    }

    public ResearchService(List<Researcher> researchers) {
        if (researchers == null) {
            throw new IllegalArgumentException("Researchers list cannot be null.");
        }
        this.researchers = new ArrayList<>(researchers);
    }

    public void addResearcher(Researcher researcher) {
        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null.");
        }

        if (!researchers.contains(researcher)) {
            researchers.add(researcher);
        }
    }

    public List<Researcher> getResearchers() {
        return new ArrayList<>(researchers);
    }

    public ResearchProject createProject(String title,
                                         String description,
                                         Researcher supervisor,
                                         LocalDate startDate,
                                         LocalDate endDate) throws SupervisorHIndexException {
        if (supervisor == null) {
            throw new IllegalArgumentException("Supervisor cannot be null.");
        }

        if (supervisor.getHIndex() < MIN_SUPERVISOR_H_INDEX) {
            throw new SupervisorHIndexException("Supervisor h-index must be at least 3.");
        }

        ResearchProject project = new ResearchProject(
                title,
                description,
                supervisor,
                startDate,
                endDate
        );

        project.addParticipant(supervisor);
        addResearcher(supervisor);

        return project;
    }

    public void joinProject(ResearchProject project, Object candidate) throws NonResearcherProjectJoinException {
        if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null.");
        }

        if (!(candidate instanceof Researcher researcher)) {
            throw new NonResearcherProjectJoinException("Only researchers can join research projects.");
        }

        project.addParticipant(researcher);
        addResearcher(researcher);
    }

    public void addPaper(Researcher researcher, ResearchPaper paper) {
        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null.");
        }

        if (paper == null) {
            throw new IllegalArgumentException("Research paper cannot be null.");
        }

        researcher.getResearchPapers().add(paper);
        addResearcher(researcher);
    }

    public void printAllPapers(Comparator<ResearchPaper> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        List<ResearchPaper> allPapers = new ArrayList<>();

        for (Researcher researcher : researchers) {
            allPapers.addAll(researcher.getResearchPapers());
        }

        allPapers.sort(comparator);

        if (allPapers.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        for (ResearchPaper paper : allPapers) {
            System.out.println(paper);
        }
    }

    public Researcher getTopCitedResearcher() {
        Researcher bestResearcher = null;
        int maxCitations = -1;

        for (Researcher researcher : researchers) {
            int totalCitations = getTotalCitations(researcher);

            if (totalCitations > maxCitations) {
                maxCitations = totalCitations;
                bestResearcher = researcher;
            }
        }

        return bestResearcher;
    }

    public Researcher getTopCitedResearcherBySchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School cannot be null.");
        }

        Researcher bestResearcher = null;
        int maxCitations = -1;

        for (Researcher researcher : researchers) {
            if (researcher instanceof Employee employee) {
                if (employee.getSchool() != school) {
                    continue;
                }
            } else {
                continue;
            }

            int totalCitations = getTotalCitations(researcher);

            if (totalCitations > maxCitations) {
                maxCitations = totalCitations;
                bestResearcher = researcher;
            }
        }

        return bestResearcher;
    }

    private int getTotalCitations(Researcher researcher) {
        int total = 0;

        for (ResearchPaper paper : researcher.getResearchPapers()) {
            total += paper.getCitations();
        }

        return total;
    }
}