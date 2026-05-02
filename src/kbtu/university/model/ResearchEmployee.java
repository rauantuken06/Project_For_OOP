package kbtu.university.model;

import kbtu.university.enums.School;
import kbtu.university.enums.UserRole;

import java.util.Comparator;
import java.util.List;

public class ResearchEmployee extends Employee implements Researcher {
    private static final long serialVersionUID = 1L;

    private ResearchProfile researchProfile;

    public ResearchEmployee() {
        super();
        this.role = UserRole.EMPLOYEE;
        this.researchProfile = new ResearchProfile(0);
    }

    public ResearchEmployee(String id,
                            String login,
                            String password,
                            String fullName,
                            String email,
                            String employeeId,
                            School school,
                            double salary,
                            int hIndex) {
        super(id, login, password, fullName, email, employeeId, school, salary);
        this.researchProfile = new ResearchProfile(hIndex);
    }

    @Override
    public int getHIndex() {
        return researchProfile.getHIndex();
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchProfile.getResearchPapers();
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        researchProfile.printPapers(comparator);
    }

    public ResearchProfile getResearchProfile() {
        return researchProfile;
    }

    public void setResearchProfile(ResearchProfile researchProfile) {
        if (researchProfile == null) {
            throw new IllegalArgumentException("Research profile cannot be null.");
        }
        this.researchProfile = researchProfile;
    }

    @Override
    public String getUserInfo() {
        return "Research Employee: " + fullName +
                ", school: " + school +
                ", h-index: " + getHIndex();
    }

    @Override
    public String toString() {
        return "ResearchEmployee{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", school=" + school +
                ", hIndex=" + getHIndex() +
                '}';
    }
}