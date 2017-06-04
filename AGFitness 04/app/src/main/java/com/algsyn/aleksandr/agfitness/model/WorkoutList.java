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

    public List<Workout> getWorkouts() {
        return workouts;
    }

    private WorkoutList() {
        initWorkoutList();
    }

    private void initWorkoutList() {
        workouts = new ArrayList<>(); // список упражнений
        workouts.add(new Workout(R.string.pushups, R.string.pushups_description, 0));
        workouts.add(new Workout(R.string.pullups, R.string.pullups_description, 0));
        workouts.add(new Workout(R.string.swimming, R.string.swimming_description, 0));
        workouts.add(new Workout(R.string.press, R.string.press_description, 0));
        workouts.add(new Workout(R.string.plank, R.string.plank_description, 0));
        workouts.add(new Workout(R.string.squats, R.string.squats_description, 0));
        workouts.add(new Workout(R.string.running, R.string.running_description, 0));
        workouts.add(new Workout(R.string.stretching, R.string.stretching_description, 0));

    }

}
