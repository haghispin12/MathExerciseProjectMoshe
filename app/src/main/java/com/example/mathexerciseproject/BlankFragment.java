package com.example.mathexerciseproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class BlankFragment extends Fragment {
    private EditText etUser;
    private TextView tvScore;
    private TextView tvRating;
    private Button btnAddPicture;
    private ImageView ivPFP;
    private Button btnAddUser;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showusers, container, false);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initviews(view);
        return view;
    }

    private void initviews(View v) {
        etUser = v.findViewById(R.id.etUser);
        tvScore = v.findViewById(R.id.tvScore);
        tvRating = v.findViewById(R.id.tvRating);
        btnAddPicture = v.findViewById(R.id.btnAddPicture);
        ivPFP = v.findViewById(R.id.ivPFP);
        btnAddUser = v.findViewById(R.id.btnAddUser);
    }
}