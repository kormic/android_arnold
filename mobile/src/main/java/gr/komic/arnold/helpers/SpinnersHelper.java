package gr.komic.arnold.helpers;

import android.content.Context;

import java.util.ArrayList;

import gr.komic.arnold.Models.Exercise;
import gr.komic.arnold.R;

public class SpinnersHelper {

    public static String[] getGenderValues(Context context) {
        String[] genderValues = new String[2];
        genderValues[0] = context.getString(R.string.man);
        genderValues[1] = context.getString(R.string.woman);
        return genderValues;
    }

    public static Integer[] getAgeValues() {
        Integer[] ages = new Integer[100];
        for(int i = 0; i < ages.length; i++) {
            ages[i] = i + 1;
        }
        return ages;
    }

    public static Integer[] getWeightValues() {
        Integer[] weights = new Integer[200];
        for(int i = 0; i < weights.length; i++) {
            weights[i] = i + 1;
        }
        return weights;
    }

    public static Integer[] getHeightValues() {
        Integer[] heights = new Integer[250];
        for(int i = 0; i < heights.length; i++) {
            heights[i] = i + 1;
        }
        return heights;
    }

    public static Integer[] getWorkoutsPerWeekValues() {
        Integer[] workoutsPerWeekValues = new Integer[7];
        for(int i = 0; i < workoutsPerWeekValues.length; i++) {
            workoutsPerWeekValues[i] = i + 1;
        }
        return workoutsPerWeekValues;
    }

    public static ArrayList<Exercise> getMockExercises(String group) {
        ArrayList<Exercise> exercises = new ArrayList<>();

        Exercise exercise1 = new Exercise("Chest Press");
        Exercise exercise2 = new Exercise("Peck Deck");
        exercise1.setId(1);
        exercise2.setId(2);
        exercises.add(exercise1);
        exercises.add(exercise2);

        return exercises;
    }

    public static Integer[] getNoOfSets() {
        Integer[] sets = new Integer[6];
        for(int i = 0; i < sets.length; i++) {
            sets[i] = i + 1;
        }

        return sets;
    }

    public static Integer[] getReps() {
        Integer[] reps = new Integer[20];
        for(int i = 0; i < reps.length; i++) {
            reps[i] = i + 1;
        }

        return reps;
    }
}
