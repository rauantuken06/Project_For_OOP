package kbtu.university.service;

import kbtu.university.model.RecommendationLetter;
import kbtu.university.model.Student;
import kbtu.university.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {
    private final List<RecommendationLetter> letters = new ArrayList<>();

    public RecommendationLetter createLetter(Student student, Teacher teacher, String text) {
        RecommendationLetter letter = new RecommendationLetter(student, teacher, text);
        letters.add(letter);
        return letter;
    }

    public List<RecommendationLetter> getLettersByStudent(Student student) {
        List<RecommendationLetter> result = new ArrayList<>();

        for (RecommendationLetter letter : letters) {
            if (letter.getStudent().equals(student)) {
                result.add(letter);
            }
        }

        return result;
    }
}
