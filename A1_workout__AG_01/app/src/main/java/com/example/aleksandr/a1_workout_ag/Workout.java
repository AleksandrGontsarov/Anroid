package com.example.aleksandr.a1_workout_ag;

public class Workout {
    int title;         // название
    int description;   // описание
    int imageReisid;   // картинка



    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getImageReisid() {
        return imageReisid;
    }

    public Workout(int title, int description, int imageReisid) {
        this.title = title;
        this.description = description;
        this.imageReisid = imageReisid;
    }
}
