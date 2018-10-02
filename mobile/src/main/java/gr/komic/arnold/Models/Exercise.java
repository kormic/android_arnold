package gr.komic.arnold.Models;


import java.util.ArrayList;

public class Exercise {
    int id;
    String name;
    ArrayList<Set> sets = new ArrayList<Set>();
    String imageUrl;

    public Exercise(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSet(Set set) {
        this.sets.add(set);
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

    public ArrayList<Set> getSets() {
        return sets;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
