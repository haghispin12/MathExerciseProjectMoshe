package com.example.mathexerciseproject.mathproject;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathexerciseproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ShowUsers extends Fragment implements MenuProvider {
    private EditText etUser;
    private TextView tvScore;
    private TextView tvRating;
    private Button btnAddPicture;
    private ImageView ivPFP;
    private Button btnAddUser;
    private MainViewModel vMain;
    private Button btnFruit;
    private Intent intent;
    private Uri uri;
    private RecyclerView rcShowUsers;
    private MenuItem itemEdit;
    private MenuItem itemDelete;
    EditText ETEditUser;
    private User currentUser;


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
        requireActivity().addMenuProvider(this);
        return view;
    }

//    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater
//            menuInflater) {
//        menuInflater.inflate(R.menu.main_menu, menu);// חיבור המניו לאקס.מ.ל
//        itemDelete = menu.findItem(R.id.action_delete); // יצירת אובייקט של
//        itemDelete.setVisible(false); // להסתיר אותו אם רוצים בטעינה הראשונה
//        itemEdit = menu.findItem(R.id.action_edit);
//        itemEdit.setVisible(false);
//        super.onCreateOptionsMenu(menu,menuInflater);
//
//    }
//    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
//        int id = menuItem.getItemId();
//        switch (id) {
//            case R.id.action_delete:
//                //TBD
//                return true;
//            case R.id.action_edit:
//                //TBD
//                return true;
//        }
//        return false;
//
//    }


    private void updateViews() {
        tvScore.setText("Score: "+vMain.getUserScore()+"");
        tvRating.setText("Rating: "+vMain.getUserRating()+"");
        etUser.setText("User: "+vMain.getUserName());
    }


    private void initViews(View v) {
        etUser = v.findViewById(R.id.etUser1);
        tvScore = v.findViewById(R.id.tvScore);
        tvRating = v.findViewById(R.id.tvRating);
        btnAddPicture = v.findViewById(R.id.btnAddPicture);
        ivPFP = v.findViewById(R.id.ivPFP);
        btnAddUser = v.findViewById(R.id.btnAddUser);
        rcShowUsers = v.findViewById(R.id.rcShowUsers);
        ETEditUser = v.findViewById(R.id.ETEditUser);
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
        /*
        button Edit User Name
         */

    }
    public void Observers(){
        vMain.vArrUser.observe(requireActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                UserAdapter userAdapter = new UserAdapter(users, new UserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(User item) {
                        itemDelete.setVisible(true);
                        itemEdit.setVisible(true);
                        ETEditUser.setText(item.getUserName()+"");
                        currentUser = item;
                    }
                });

                rcShowUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
                rcShowUsers.setAdapter(userAdapter);
                rcShowUsers.setHasFixedSize(true);

            }

        });

    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);// חיבור המניו לאקס.מ.ל
        itemDelete = menu.findItem(R.id.action_delete); // יצירת אובייקט של המחיקה
        itemDelete.setVisible(false); // להסתיר אותו אם רוצים בטעינה הראשונה
        itemEdit = menu.findItem(R.id.action_edit);
        itemEdit.setVisible(false);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.action_delete:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Delete User");
                alertDialog.setMessage("Are you sure you want to delete user, "+currentUser.getUserName()+"?");
                alertDialog.setIcon(R.drawable.ic_baseline_warning_24);
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        vMain.dbDeleteUser(getActivity(), currentUser.getId());
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();

                return true;
            case R.id.action_edit:
                currentUser.setUserName(ETEditUser.getText()+"");
                vMain.dbEditUsername(getActivity(), currentUser);
                return true;
        }
        return false;
    }
}