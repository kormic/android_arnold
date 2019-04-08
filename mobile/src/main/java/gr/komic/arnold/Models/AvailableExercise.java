package gr.komic.arnold.Models;

import java.util.ArrayList;

public class AvailableExercise {
    long Id;
    String Name;
    String MuscleGroup;

    public AvailableExercise(String name, String muscleGroup) {
        Name = name;
        MuscleGroup = muscleGroup;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMuscleGroup(String muscleGroup) {
        muscleGroup = muscleGroup;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getMuscleGroup() {
        return MuscleGroup;
    }

    @Override
    public String toString() {
        return Name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (this.getId() != ((AvailableExercise) obj).getId()) {
            return false;
        } else {
            return true;
        }
    }
}
