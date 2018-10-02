package gr.komic.arnold.Models;

import java.util.ArrayList;
import java.util.Date;

public class Program {
    int id;
    ArrayList<Integer> exercisesIds;
    Date createdAt;
    Boolean isCurrentProgram;

    public Program(int id) {
        this.id = id;
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Boolean getIsCurrentProgram() {
        return isCurrentProgram;
    }

    public void setIsCurrentProgram(Boolean isCurrentProgram) {
        isCurrentProgram = isCurrentProgram;
    }

    public void addExerciseToProgram(int exerciseId) {
        this.exercisesIds.add(exerciseId);
    }

    public void removeExerciseFromProgram(int exerciseid) {
        this.exercisesIds.remove(this.exercisesIds.indexOf(exerciseid));
    }
}
