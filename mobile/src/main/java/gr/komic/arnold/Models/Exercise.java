package gr.komic.arnold.Models;

import java.util.ArrayList;

public class Exercise {
    long id;
    String name;
    ArrayList<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>();
    String muscleGroup;
    String imageUrl;
    long programId;

    public Exercise(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public void addExerciseSet(ExerciseSet exerciseSet) {
        this.exerciseSets.add(exerciseSet);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProgramId(long programId) { this.programId = programId; }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public ArrayList<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getProgramId() {
        return programId;
    }

    @Override
    public String toString() {
        return name;
    }
}
