package com.example.loginsignup;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginsignup.Adapters.UsersAdapter;
import com.example.loginsignup.Interface.UsersCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<com.example.loginsignup.Classes.User> userArrayList;
    UsersAdapter usersAdapter;
    FirebaseServices db;
    ProgressDialog progressDialog;
    private UsersCallBack ucall;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
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
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {

        recyclerView = getView().findViewById(R.id.usersRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        db = FirebaseServices.getInstance();
        userArrayList = new ArrayList<com.example.loginsignup.Classes.User>();
        getData();
        ucall = new UsersCallBack() {
            @Override
            public void onCallback(ArrayList<com.example.loginsignup.Classes.User> usersList) {
                usersAdapter = new UsersAdapter(userArrayList, new UsersAdapter.ItemClickListener() {

                    @Override
                    public void onItemClickUser(com.example.loginsignup.Classes.User user) {

                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.FrameLayoutMain, new ChatBuy());
                        ft.commit();
                    }

                    @Override
                    public void onItemClick(User user) {

                    }

                });
                recyclerView.setAdapter(usersAdapter);
            }
        };

            }


    private void getData()
    {
        db.getFire().collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                    userArrayList.add(document.toObject(com.example.loginsignup.Classes.User.class));
                            }

                            ucall.onCallback(userArrayList);


                        } else {
                            //Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}