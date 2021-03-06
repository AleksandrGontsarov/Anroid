package com.algsyn.aleksandr.agfitness.model;


import com.algsyn.aleksandr.agfitness.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutList {
    private static WorkoutList ourInstance = null;

    List<Workout> workouts;

    public static WorkoutList getInstance() {
        if (ourInstance == null) {
            ourInstance = new WorkoutList();
            return ourInstance;
        } else {
            return ourInstance;
        }
    }

    public List<Workout> getWorkoutsList() {
        return workouts;
    }

    private WorkoutList() {
        initWorkoutList();
    }

    private void initWorkoutList() {
        workouts = new ArrayList<>();
        workouts.add(new Workout(R.string.pushups, R.string.pushups_description, 0));
        workouts.add(new Workout(R.string.pullups, R.string.pullups_description, 0));
    }

}
