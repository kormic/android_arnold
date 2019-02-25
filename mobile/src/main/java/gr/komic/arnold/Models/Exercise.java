package gr.komic.arnold.Models;

import java.util.ArrayList;

public class Exercise {
    int id;
    String name;
    ArrayList<ExerciseSet> sets = new ArrayList<ExerciseSet>();
    String imageUrl;

    public Exercise(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSet(ExerciseSet exerciseSet) {
        this.sets.add(exerciseSet);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ExerciseSet> getSets() {
        return sets;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
