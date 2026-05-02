package kbtu.university.util;
import java.util.Comparator;
import kbtu.university.model.ResearchPaper;

public class ResearchPaperComparators {
    public static final Comparator<ResearchPaper> BY_DATE =
            Comparator.comparing(ResearchPaper::getPublishedDate);

    public static final Comparator<ResearchPaper> BY_CITATIONS =
            Comparator.comparingInt(ResearchPaper::getCitations).reversed();

    public static final Comparator<ResearchPaper> BY_PAGES =
            Comparator.comparingInt(ResearchPaper::getPages);

    private ResearchPaperComparators() {
    }
  
}
