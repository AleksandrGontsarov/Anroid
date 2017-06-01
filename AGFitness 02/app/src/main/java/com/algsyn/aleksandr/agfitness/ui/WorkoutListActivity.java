package com.algsyn.aleksandr.agfitness.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algsyn.aleksandr.agfitness.R;
import com.algsyn.aleksandr.agfitness.model.Workout;
import com.algsyn.aleksandr.agfitness.model.WorkoutList;

import java.util.List;

public class WorkoutListActivity extends AppCompatActivity {

    List<Workout> workouts;
    RecyclerView workoutsList;
    WorkoutAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workouts = WorkoutList.getInstance().getWorkouts();
        workoutsList = (RecyclerView) findViewById(R.id.workout_list);
        adapter = new WorkoutAdapter(workouts);




        workoutsList.setLayoutManager(new LinearLayoutManager(this));
        workoutsList.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutHolder> {
        List<Workout> workouts;

        public WorkoutAdapter(List<Workout> workouts) {
            this.workouts = workouts;
        }


        @Override
        public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View workoutItem = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.workout_list_item, parent, false);
            return new WorkoutHolder(workoutItem);
        }

        @Override
        public void onBindViewHolder(final WorkoutHolder holder, int position) {
            holder.titleTextView.setText(workouts.get(holder.getAdapterPosition()).getTitle());
            holder.lastRepsCount.setText(String.valueOf(workouts.get(holder.getAdapterPosition()).getRepCount()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    WorkoutActivity.startActivity(getApplicationContext(), holder.getAdapterPosition());
                    WorkoutActivity.startActivity(WorkoutListActivity.this, holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            if (workouts != null)

                return workouts.size();
            else return 0;
        }

    }

    private class WorkoutHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView lastRepsCount;

        public WorkoutHolder(View itemView) {
            super(itemView);
//            titleTextView = (TextView) itemView.findViewById(R.id.workout_title_text_view);
            titleTextView = (TextView) itemView.findViewById(R.id.workout_list_title_text_view);
            lastRepsCount = (TextView) itemView.findViewById(R.id.workout_list_last_count);

        }

    }
}



