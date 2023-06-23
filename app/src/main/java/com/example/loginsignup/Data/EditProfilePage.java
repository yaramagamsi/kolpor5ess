package com.example.loginsignup.Data;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.loginsignup.AddProductProfilePage;
import com.example.loginsignup.FirebaseServices;
import com.example.loginsignup.HomePageFragment;
import com.example.loginsignup.ProfilePageFragment;
import com.example.loginsignup.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfilePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfilePage extends Fragment {

   private TextView tvAddPost;
    private ImageView addPost,backEditProfile;
    private Bitmap image;
    private Button btnAdd;
    private Spinner spnCategory;
    private FirebaseServices fbs;
    ActivityResultLauncher<String> mTakePhoto;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfilePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfilePage.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfilePage newInstance(String param1, String param2) {
        EditProfilePage fragment = new EditProfilePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile_page, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {

        backEditProfile = getView().findViewById(R.id.ivBackEditProfile);
        backEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new ProfilePageFragment());
                ft.commit();
            }
        });

        addPost = getView().findViewById(R.id.ivAddPost);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new AddProductProfilePage());
                ft.commit();
            }
        });
    }
}