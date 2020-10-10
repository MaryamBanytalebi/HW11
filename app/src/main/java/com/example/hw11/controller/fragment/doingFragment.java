package com.example.hw11.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.hw11.R;
import com.example.hw11.model.Task;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.TextDrawableHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class doingFragment extends Fragment {
    private FloatingActionButton mAdd_doing;
    private RecyclerView mRecyclerViewDoing;
    private RelativeLayout mLayoutEmptyDoing;
    public static final int REQUEST_CODE_EDIT_TASK_DOING = 1;
    public static final String FRAGMENT_TAG_EDIT_TASK_DOING = "EditTaskDoing";
    private List<Task> mTasks;
    private TaskAdapter mTaskAdapter;


    public doingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static doingFragment newInstance() {
        doingFragment fragment = new doingFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doing, container, false);
        findViews(view);
        initViews();
        setListeners();
        checkEmptyLayout();
        return view;
    }

    private void initViews() {
        mRecyclerViewDoing.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void setListeners() {
        mAdd_doing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Custom_DialogFragment dialogFragment = Custom_DialogFragment.newInstance();
                dialogFragment.show(getActivity().getSupportFragmentManager(),"add task");

            }
        });
    }

    private void findViews(View view) {
        mAdd_doing = view.findViewById(R.id.add_button_doing);
        mRecyclerViewDoing = view.findViewById(R.id.recycler_doing);
        mLayoutEmptyDoing = view.findViewById(R.id.layout_empty);
    }

    private void checkEmptyLayout(){
        if (mTasks.size() == 0){
            mLayoutEmptyDoing.setVisibility(View.VISIBLE);
        }
        else {
            mLayoutEmptyDoing.setVisibility(View.GONE);
        }
    }

    private void updateUI() {

        checkEmptyLayout();
        if (mTaskAdapter == null) {
            mTaskAdapter = new doingFragment.TaskAdapter(mTasks);
            mRecyclerViewDoing.setAdapter(mTaskAdapter);
        }
        else {
            mTaskAdapter.setTasks(mTasks);
            mTaskAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder{
        private TextView mTxtTitle;
        private TextView mTxtDate;
        private ImageView mImageTask;
        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTxtTitle = itemView.findViewById(R.id.task_title);
            mTxtDate = itemView.findViewById(R.id.task_date);
            mImageTask = itemView.findViewById(R.id.task_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(mTask.getId());
                    editTaskFragment.setTargetFragment(doingFragment.this,REQUEST_CODE_EDIT_TASK_DOING);

                    editTaskFragment.show(
                            getActivity().getSupportFragmentManager(),
                            FRAGMENT_TAG_EDIT_TASK_DOING);

                }
            });
        }

        public void bindTask(Task task) {
            mTask = task;
            mTxtTitle.setText(task.getTitle());
            String date = createDateFormat(task);
            mTxtDate.setText(date);
            String string = task.getTitle().substring(0,1);
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(string, Color.RED);
            mImageTask.setImageDrawable(drawable);
        }
        private DateFormat getDateFormat() {
            return new SimpleDateFormat("MMM dd,yyyy");
        }

        private DateFormat getTimeFormat() {
            return new SimpleDateFormat("h:mm a");
        }
        private String createDateFormat (Task task){
            String totalDate = "";
            DateFormat dateFormat = getDateFormat();
            String date = dateFormat.format(task.getDate());

            DateFormat timeFormat = getTimeFormat();
            String time = timeFormat.format(task.getDate());

            totalDate = date + "  " + time;

            return totalDate;
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> mTasks;

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_task_detail,parent,false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);

            holder.bindTask(task);

        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

    }
}