package gr.komic.arnold.Models;

import java.util.ArrayList;
import java.util.Date;

public class Program {
    long id;
    String title;
    ArrayList<Long> exercisesIds = new ArrayList<Long>();
    Date createdAt;
    Boolean isCurrentProgram = false;

    public Program() {
        this.createdAt = new Date();
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Boolean getIsCurrentProgram() {
        return isCurrentProgram;
    }

    public ArrayList<Long> getExerciseIds() {
        return this.exercisesIds;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setIsCurrentProgram(Boolean isCurrentProgram) {
        this.isCurrentProgram = isCurrentProgram;
    }

    public void addExerciseToProgram(long exerciseId) {
        this.exercisesIds.add(exerciseId);
    }

    public void removeExerciseFromProgram(int exerciseid) {
        this.exercisesIds.remove(this.exercisesIds.indexOf(exerciseid));
    }
}
