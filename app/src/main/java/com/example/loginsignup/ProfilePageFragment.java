package com.example.loginsignup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePageFragment extends Fragment {


    private TextView posts, chats,editPic, profileUsername;
    private RelativeLayout relativeL, relativeL2;
    private ImageView profilePic;

    private FirebaseServices fbs;


    private ProductsCallBack ucall;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePageFragment newInstance(String param1, String param2) {
        ProfilePageFragment fragment = new ProfilePageFragment();
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
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();

    }

    private void connectComponents() {

        relativeL = getView().findViewById(R.id.relativeLayout);
        relativeL2 = getView().findViewById(R.id.relativeLayout2);

        profileUsername = getView().findViewById(R.id.tvProfileUsername);




        profilePic = getView().findViewById(R.id.ivProfilePic);
        editPic = getView().findViewById(R.id.tvEditPic);
        editPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        posts = getView().findViewById(R.id.tvPostsProfile);
        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chats.setTextColor(Color.parseColor("#A3A3A3"));
                posts.setTextColor(Color.parseColor("#B54040"));


                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.relativeLayout2, new MyPosts());
                ft.commit();

            }
        });

         chats = getView().findViewById(R.id.tvChatsProfile);
         chats.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 chats.setTextColor(Color.parseColor("#B54040"));
                 posts.setTextColor(Color.parseColor("#A3A3A3"));

                 FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                 ft.replace(R.id.relativeLayout2, new MyMessages());
                 ft.commit();

             }
         });

    }


}