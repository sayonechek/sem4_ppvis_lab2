package sample.model;


public class Student {
    private String fullName;
    private int group;
    private int numberOfAbsencesDueToIllness;
    private int numberOfAbsencesDueToAnotherReason;
    private int numberOfUnjustifiedAbsences;
    private int totalOfAbsences;

    public Student (String fullName, Integer group, Integer numberOfAbsencesDueToIllness,
                    Integer numberOfAbsencesDueToAnotherReason,
                    Integer numberOfUnjustifiedAbsences, Integer totalOfAbsences) {
        this.fullName = fullName;
        this.group = group;
        this.numberOfAbsencesDueToIllness = numberOfAbsencesDueToIllness;
        this.numberOfAbsencesDueToAnotherReason = numberOfAbsencesDueToAnotherReason;
        this.numberOfUnjustifiedAbsences = numberOfUnjustifiedAbsences;
        this.totalOfAbsences = totalOfAbsences;
    }
    public String getFullName() { return this.fullName; }

    public String getSecondName() {
        String fullName = this.fullName + ' ';
        String secondName = this.fullName.split(" ")[0];
        return secondName;
    }

    public Integer getGroup() { return  this.group; }

    public Integer getNumberOfAbsencesDueToIllness() { return this.numberOfAbsencesDueToIllness; }

    public Integer getNumberOfAbsencesDueToAnotherReason() { return this.numberOfAbsencesDueToAnotherReason; }

    public Integer getNumberOfUnjustifiedAbsences() { return this.numberOfUnjustifiedAbsences; }

    public Integer getTotalOfAbsences() { return this.totalOfAbsences; }

}
