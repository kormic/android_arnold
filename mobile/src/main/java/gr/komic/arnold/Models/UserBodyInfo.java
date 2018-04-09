package gr.komic.arnold.Models;

import gr.komic.arnold.helpers.SpinnersHelper;

public class UserBodyInfo {
    private String gender = "Άντρας";
    private int age = SpinnersHelper.getAgeValues()[34];
    private int weight = SpinnersHelper.getWeightValues()[79];
    private int height = SpinnersHelper.getHeightValues()[179];
    private int workoutsPerWeek = SpinnersHelper.getWorkoutsPerWeekValues()[3];

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWorkoutsPerWeek() {
        return workoutsPerWeek;
    }

    public void setWorkoutsPerWeek(int workoutsPerWeek) {
        this.workoutsPerWeek = workoutsPerWeek;
    }
}
