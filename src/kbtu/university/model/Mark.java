package kbtu.university.model;

import java.io.Serializable;
import java.util.Objects;

public class Mark implements Serializable, Comparable<Mark>, Cloneable {
    private static final long serialVersionUID = 1L;

    private double attestation1;
    private double attestation2;
    private double finalExam;

    public Mark() {
    }

    public Mark(double attestation1, double attestation2, double finalExam) {
        setAttestation1(attestation1);
        setAttestation2(attestation2);
        setFinalExam(finalExam);
    }

    public double getTotal() {
        return attestation1 + attestation2 + finalExam;
    }

    public String getGrade() {
        double total = getTotal();
        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        return "F";
    }

    public boolean isPassed() {
        return getTotal() >= 50;
    }

    @Override
    public int compareTo(Mark other) {
        if (other == null) {
            return 1;
        }
        return Double.compare(this.getTotal(), other.getTotal());
    }

    @Override
    public Mark clone() {
        try {
            return (Mark) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public double getAttestation1() {
        return attestation1;
    }

    public void setAttestation1(double attestation1) {
        validatePart(attestation1, 30, "First attestation");
        this.attestation1 = attestation1;
    }

    public double getAttestation2() {
        return attestation2;
    }

    public void setAttestation2(double attestation2) {
        validatePart(attestation2, 30, "Second attestation");
        this.attestation2 = attestation2;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        validatePart(finalExam, 40, "Final exam");
        this.finalExam = finalExam;
    }

    private void validatePart(double value, double max, String partName) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(partName + " mark must be from 0 to " + max + ".");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark mark)) return false;
        return Double.compare(mark.attestation1, attestation1) == 0 &&
                Double.compare(mark.attestation2, attestation2) == 0 &&
                Double.compare(mark.finalExam, finalExam) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attestation1, attestation2, finalExam);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "attestation1=" + attestation1 +
                ", attestation2=" + attestation2 +
                ", finalExam=" + finalExam +
                ", total=" + getTotal() +
                ", grade='" + getGrade() + '\'' +
                '}';
    }
}
