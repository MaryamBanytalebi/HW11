package com.example.hw11.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.hw11.R;
import com.example.hw11.model.Task;
import com.example.hw11.model.User;
import com.example.hw11.repository.IRepositry;
import com.example.hw11.repository.IUserRepository;
import com.example.hw11.repository.TaskDBRepository;
import com.example.hw11.repository.UserDBRepository;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabsFragment extends Fragment {

    public static final String FRAGMENT_TAG_INSERT_TASK = "InsertTask";
    public static final int REQUEST_CODE_INSERT_TASK = 0;
    public static final String FRAGMENT_TAG_EDIT_TASK = "EditTask";
    public static final int REQUEST_CODE_EDIT_TASK = 1;
    public static final String FRAGMENT_TAG_DELETE_ALL_TASK = "DeleteAllTask";
    public static final int REQUEST_CODE_DELETE_ALL_TASK = 2;
    public static final String ARG_STATE = "arg_State";
    public static final String ARGUMENT_USERNAME = "arg_Username";
    public static final String ARGUMENT_PASSWORD = "arg_Password";
    private RecyclerView mRecyclerView;
    private TabsAdapter mAdapter;
    private IRepositry mRepository;
    private IUserRepository mIUserRepository;
    private List<Task> mTasks;
    private RelativeLayout mLayoutEmpty;
    private FloatingActionButton mActionButtonInsert;
    private FloatingActionButton mActionButtonDelete;
    private FloatingActionButton mActionButtonLogOut;
    private FloatingActionsMenu mFloatingActionsMenu;
    private boolean isVisible;
    private String mState,mUsername,mPassword;
    private User mUser;


    public TabsFragment() {
        // Required empty public constructor
    }

//    public abstract Fragment createFragment();

    public static TabsFragment newInstance(String username,String password,String state) {

        Bundle args = new Bundle();
        args.putString(ARG_STATE,state);
        args.putString(ARGUMENT_USERNAME,username);
        args.putString(ARGUMENT_PASSWORD,password);
        TabsFragment fragment = new TabsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        updateUI();
        mFloatingActionsMenu.collapse();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = TaskDBRepository.getInstance(getActivity());
        mIUserRepository = UserDBRepository.getInstance(getActivity());
        mState = getArguments().getString(ARG_STATE);
        mUsername = getArguments().getString(ARGUMENT_USERNAME);
        mPassword = getArguments().getString(ARGUMENT_PASSWORD);
        mUser = mIUserRepository.getUser(mUsername,mPassword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        findViews(view);
        checkEmptyLayout();
        initViews();
        listeners();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_INSERT_TASK || requestCode == REQUEST_CODE_EDIT_TASK) {
            updateUI();

        }
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mLayoutEmpty = view.findViewById(R.id.layout_empty);
        mActionButtonInsert = view.findViewById(R.id.fab_insert);
        mActionButtonDelete = view.findViewById(R.id.fab_delete);
        mActionButtonLogOut = view.findViewById(R.id.fab_logOut);
        mFloatingActionsMenu = view.findViewById(R.id.fam);

    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void listeners() {
        mActionButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertTaskFragment insertTaskFragment = InsertTaskFragment.newInstance(mUsername,mPassword);

                insertTaskFragment.setTargetFragment(
                        TabsFragment.this,
                        REQUEST_CODE_INSERT_TASK);

                insertTaskFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_INSERT_TASK);

            }
        });
        mActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllFragment deleteAllFragment = com.example.hw11.controller.fragment.deleteAllFragment.newInstance();

                deleteAllFragment.setTargetFragment(
                        TabsFragment.this,
                        REQUEST_CODE_DELETE_ALL_TASK);

                deleteAllFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_DELETE_ALL_TASK);

            }
        });
        mActionButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void updateUI() {

        checkEmptyLayout();
        if (mAdapter == null) {
            mAdapter = new TabsAdapter(mTasks);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setTasks(mTasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void checkEmptyLayout() {
        if (mState.equalsIgnoreCase("todo"))
            mTasks = mRepository.getTodoTask(mUser.getPrimaryId());
        else if (mState.equalsIgnoreCase("doing"))
            mTasks = mRepository.getDoingTask(mUser.getPrimaryId());
        else if (mState.equalsIgnoreCase("done"))
            mTasks = mRepository.getDoneTask(mUser.getPrimaryId());

        if (mTasks.size()==0)
            mLayoutEmpty.setVisibility(View.VISIBLE);
        else
            mLayoutEmpty.setVisibility(View.GONE);
    }

    //set offset zero
//TODO.........................................

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !isVisible) {
            //your code
        }
        isVisible = true;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isVisible) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //your code
                }
            }, 500);

        }
    }

    private class TabsHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private ImageView mImageViewProfile;
        private Task mTask;

        public TabsHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.task_title);
            mTextViewDate = itemView.findViewById(R.id.task_date);
            mImageViewProfile = itemView.findViewById(R.id.task_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(mTask.getId(),true);

                    editTaskFragment.setTargetFragment(
                            TabsFragment.this,
                            REQUEST_CODE_EDIT_TASK);

                    editTaskFragment.show(
                            getActivity().getSupportFragmentManager(),
                            FRAGMENT_TAG_EDIT_TASK);
                }
            });
        }

        public void bindTaskTabs(Task task) {
            mTask = task;
            mTextViewTitle.setText(task.getTitle());
            String date = createDateFormat(task);
            mTextViewDate.setText(date);
            int color = Color.parseColor("#ff80aa");
            String string = task.getTitle().substring(0,1);
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(string,color);
            mImageViewProfile.setImageDrawable(drawable);
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

        private DateFormat getDateFormat() {
            return new SimpleDateFormat("MMM dd,yyyy");
        }

        private DateFormat getTimeFormat() {
            return new SimpleDateFormat("h:mm a");
        }
    }

    private class TabsAdapter extends RecyclerView.Adapter<TabsHolder> {

        private List<Task> mTasks;

        public List<Task> getTasks(){
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public TabsAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        @NonNull
        @Override
        public TabsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_task_row_list,parent,false);
            TabsHolder tabsHolder = new TabsHolder(view);
            return tabsHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TabsHolder holder, int position) {

            Task task = mTasks.get(position);

            holder.bindTaskTabs(task);
        }

    }
}