package com.example.mathexerciseproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ShowUsers extends Fragment {
    private EditText etUser;
    private TextView tvScore;
    private TextView tvRating;
    private Button btnAddPicture;
    private ImageView ivPFP;
    private Button btnAddUser;
    private MainViewModel vMain;
    private Button btnMain;
    private Intent intent;

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showusers, container, false);
        vMain = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initViews(view);
        updateViews();
        return view;
    }


    private void updateViews() {
        tvScore.setText("Score: "+vMain.getUserScore()+"");
        tvRating.setText("Rating: "+vMain.getUserRating()+"");
        etUser.setText("User: "+vMain.getUserName());
    }


    private void initViews(View v) {
        etUser = v.findViewById(R.id.etUser);
        tvScore = v.findViewById(R.id.tvScore);
        tvRating = v.findViewById(R.id.tvRating);
        btnAddPicture = v.findViewById(R.id.btnAddPicture);
        ivPFP = v.findViewById(R.id.ivPFP);
        btnAddUser = v.findViewById(R.id.btnAddUser);
        btnMain = v.findViewById(R.id.btnMain);
    }
}