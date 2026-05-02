package kbtu.university.model;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    int getHIndex();

    List<ResearchPaper> getResearchPapers();

    void printPapers(Comparator<ResearchPaper> comparator);
}
