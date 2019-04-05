package gr.komic.arnold.Models;

import java.util.ArrayList;
import java.util.Date;

public class Program {
    long Id;
    String Title;
    ArrayList<Long> ExercisesIds = new ArrayList<Long>();
    Date CreatedAt;
    Boolean IsCurrentProgram = false;

    public Program() {
        CreatedAt = new Date();
    }

    public long getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public Boolean getIsCurrentProgram() {
        return IsCurrentProgram;
    }

    public ArrayList<Long> getExerciseIds() { return ExercisesIds; }

    public void setId(long id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setCreatedAt(Date createdAt) { CreatedAt = createdAt; }

    public void setIsCurrentProgram(Boolean isCurrentProgram) { IsCurrentProgram = isCurrentProgram; }

    public void addExerciseToProgram(long exerciseId) {
        ExercisesIds.add(exerciseId);
    }

    public void removeExerciseFromProgram(int exerciseid) { ExercisesIds.remove(ExercisesIds.indexOf(exerciseid)); }
}
