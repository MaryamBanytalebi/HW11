package com.example.hw11.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.hw11.R;
import com.example.hw11.model.Task;
import com.example.hw11.repository.Repository;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class Custom_DialogFragment extends DialogFragment {
    public static final int REQUEST_CODE_DATE_PICKER = 0;
    public static final int REQUEST_CODE_TIME_PICKER = 1;
    public static final String FRAGMENT_TAG_DATE_PICKER = "date picker";
    public static final String FRAGMENT_TAG_TIME_PICKER = "time picker";
    public static final String EXTRA_USER_SELECTED_DATE = "user salected date";
    public static final String BUNDLE_KEY_TIME = "time";
    public static final String BUNDLE_KEY_DATE = "date";
    private static final int REQUEST_CODE_IMAGE_CAPTURE = 2;
    public static final String TAG = "ETF";
    public static final String AUTHORITY = "com.example.hw11.fileProvider";

    private DatePicker mDatePicker;
    private TextView mTxtTitle;
    private EditText mEdtTitle;
    private EditText mEdtDescript;
    private Button mBtnTime;
    private Button mBtnDate;
    private Button mBtnSave;
    private Button mBtnCancel;
    private RadioButton mRadioDoing,mRadioTodo,mRadioDone;
    private ImageView mImageTakePicture,mImageTaskPicture;
    private File mPhotoFile;

    private Task mTask;
    private Calendar mCalendar = Calendar.getInstance();
    private Repository mRepository=Repository.getInstance();

    public Custom_DialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Custom_DialogFragment newInstance() {
        Custom_DialogFragment fragment = new Custom_DialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createTask();
        mPhotoFile = mRepository.getPhotoFile(mTask);

    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_custom_dialog,null);
        builder.setView(view);
        mTask=new Task();
        findViews(view);
        setListeners();
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_DATE_PICKER){
            Calendar userSelectedDate = (Calendar) data.getSerializableExtra(DatePickerFragment.EXTRA_USER_SELECTED_DATE);
            updateTaskDate(userSelectedDate.getTime());
        }
        else if (requestCode == REQUEST_CODE_TIME_PICKER){
            Calendar userSelectedTime = (Calendar) data.getSerializableExtra(TimePickerFragment.EXTRA_USER_SELECTED_TIME);
            updateTaskTime(userSelectedTime.getTime());
        }
        else if (requestCode == REQUEST_CODE_IMAGE_CAPTURE) {
            Uri photoUri = generateUriForPhotoFile();
            getActivity().revokeUriPermission(photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY_TIME,mBtnTime.getText().toString());
        outState.putString(BUNDLE_KEY_DATE,mBtnDate.getText().toString());

    }

    private void findViews(View view) {
        mTxtTitle = view.findViewById(R.id.txt_title);
        mEdtTitle = view.findViewById(R.id.edt_title);
        mEdtDescript = view.findViewById(R.id.edt_descript);
        mBtnDate = view.findViewById(R.id.btn_date);
        mBtnTime = view.findViewById(R.id.btn_time);
        mBtnSave = view.findViewById(R.id.btn_save);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mRadioDoing = view.findViewById(R.id.Radio_doing);
        mRadioDone = view.findViewById(R.id.Radio_done);
        mRadioTodo = view.findViewById(R.id.Radio_todo);
        mImageTaskPicture = view.findViewById(R.id.task_picture_insert);
        //mImageTakePicture = view.findViewById(R.id.btn_picture_insert);
    }

    private void setListeners() {
        mBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(Custom_DialogFragment.this,
                        REQUEST_CODE_DATE_PICKER);

                datePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_DATE_PICKER);

            }
        });

        mBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mTask.getDate());
                timePickerFragment.setTargetFragment(Custom_DialogFragment.this,
                        REQUEST_CODE_TIME_PICKER);

                timePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_TIME_PICKER);

            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validInput()){
                    sendResult();
                    updateTasks(mTask);
                    dismiss();
                }
                else{
                    Toast toast = Toast.makeText(getActivity(), "enter data", Toast.LENGTH_SHORT);
                    toast.show();

                }

            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mImageTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureIntent();

            }
        });

    }

    private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            if (mPhotoFile != null && takePictureIntent
                    .resolveActivity(getActivity().getPackageManager()) != null) {

                // file:///data/data/com.example.ci/files/234234234234.jpg
                Uri photoUri = generateUriForPhotoFile();

                grantWriteUriToAllResolvedActivities(takePictureIntent, photoUri);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_CODE_IMAGE_CAPTURE);
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    private void grantWriteUriToAllResolvedActivities(Intent takePictureIntent, Uri photoUri) {
        List<ResolveInfo> activities = getActivity().getPackageManager()
                .queryIntentActivities(
                        takePictureIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo activity: activities) {
            getActivity().grantUriPermission(
                    activity.activityInfo.packageName,
                    photoUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
    }

    private boolean validInput() {
        if (mEdtTitle.getText() != null && mEdtDescript.getText() != null
                && mBtnDate.getText() != null && mBtnTime.getText() != null
                && mRadioDoing.isChecked() || mRadioDone.isChecked() || mRadioTodo.isChecked())
            return true;
        else
            return false;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists())
            return;


        //this has a better memory management.
        Bitmap bitmap = com.example.hw11.utils.PictureUtils.getScaledBitmap(mPhotoFile.getAbsolutePath(), getActivity());
        mImageTaskPicture.setImageBitmap(bitmap);
    }

    private Uri generateUriForPhotoFile() {
        return FileProvider.getUriForFile(
                getContext(),
                AUTHORITY,
                mPhotoFile);
    }

    private void sendResult() {
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = RESULT_OK;
        //cupdateTask();
        insertTaskToRepository(mTask);
        createTask();
        updateTasks(mTask);
        //extractDateFromDatePicker();
        //intent.putExtra(EXTRA_USER_SELECTED_DATE, userSelectedDate);

        //fragment.onActivityResult(requestCode, resultCode, intent);

        Intent intent = new Intent();
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
    }

    private void createTask(){
        String state = "";
        if (mRadioTodo.isChecked())
            state = "Todo";
        else if (mRadioDoing.isChecked())
            state = "Doing";
        else if (mRadioDone.isChecked())
            state = "Done";
        mTask = new Task(mEdtTitle.getText().toString(),mEdtDescript.getText().toString(),state,mCalendar.getTime());
    }

    private void insertTaskToRepository(Task task) {
        mRepository.insertTask(task);
    }

    private void updateTasks(Task task) {
        mRepository.insertTask(task);
    }

    public void updateTaskTime(Date userSelectedTime){
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(userSelectedTime);
       int hour = calendar.get(Calendar.HOUR_OF_DAY);
       int minute = calendar.get(Calendar.MINUTE);
       mCalendar.set(Calendar.HOUR_OF_DAY,hour);
       mCalendar.set(Calendar.MINUTE,minute);
       DateFormat timeFormat = getTimeFormat();
       mBtnTime.setText(timeFormat.format(userSelectedTime));
    }

    public void updateTaskDate(Date userSelectedDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userSelectedDate);
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        DateFormat dateFormat = getDateFormat();
        mBtnDate.setText(dateFormat.format(userSelectedDate));
    }

    private DateFormat getDateFormat() {
        //"yyyy/MM/dd"
        return new SimpleDateFormat("MMM dd,yyyy");
    }

    private DateFormat getTimeFormat() {
        //"HH:mm:ss"
        return new SimpleDateFormat("h:mm a");
    }

}