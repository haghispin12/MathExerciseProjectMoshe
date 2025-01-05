package com.example.mathexerciseproject;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
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
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ShowUsers extends Fragment {
    private EditText etUser;
    private TextView tvScore;
    private TextView tvRating;
    private Button btnAddPicture;
    private ImageView ivPFP;
    private Button btnAddUser;
    private MainViewModel vMain;
    private Button btnFruit;
    private Intent intent;
    Uri uri;
    private RecyclerView rcShowUsers;

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        ivPFP.setImageURI(uri);
                        vMain.vUser.setUri(uri);
                    }
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
        onClickListeners();
        Observers();
        vMain.dbSelectAll(getActivity());
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
        btnFruit = v.findViewById(R.id.btnFruit);

    }
    public void onClickListeners(){
        /*
        Button addUser
         */
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null) {
                    long id = vMain.dbAddUser(getContext());
                    Toast.makeText(getActivity(), "id= "+id, Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*
        button AddPicture
         */
        btnAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                uri =
                        requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startCamera.launch(cameraIntent);
            }
        });
    }
    public void Observers(){
        vMain.vArrUser.observe(requireActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {

            }
        });

    }
}