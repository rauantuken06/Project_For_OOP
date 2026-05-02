package kbtu.university.service;

import kbtu.university.exceptions.NonResearcherProjectJoinException;
import kbtu.university.exceptions.SupervisorHIndexException;
import kbtu.university.model.ResearchPaper;
import kbtu.university.model.ResearchProfile;
import kbtu.university.model.ResearchProject;
import kbtu.university.model.Researcher;

import java.time.LocalDate;

public class ResearchService {
    public static final int MIN_SUPERVISOR_H_INDEX = 3;

    public ResearchProject createProject(String title, String description, Researcher supervisor,
                                         LocalDate startDate, LocalDate endDate) throws SupervisorHIndexException {
        if (supervisor == null || supervisor.getHIndex() < MIN_SUPERVISOR_H_INDEX) {
            throw new SupervisorHIndexException("Supervisor h-index must be at least 3.");
        }
        ResearchProject project = new ResearchProject(title, description, supervisor, startDate, endDate);
        addProjectToProfile(supervisor, project);
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
        addProjectToProfile(researcher, project);
    }

    public void addPaper(Researcher researcher, ResearchPaper paper) {
        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null.");
        }
        if (paper == null) {
            throw new IllegalArgumentException("Research paper cannot be null.");
        }
        researcher.getResearchPapers().add(paper);
    }

    private void addProjectToProfile(Researcher researcher, ResearchProject project) {
        if (researcher instanceof ResearchProfile profile) {
            profile.addProject(project);
        }
    }
}
