package com.example.loginsignup;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyMessages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyMessages extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Message> messageArrayList;
    MessageAdapter messageAdapter;
    FirebaseServices db;
    ProgressDialog progressDialog;

    private ProductsCallBack ucall;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyMessages() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyMessages.
     */
    // TODO: Rename and change types and number of parameters
    public static MyMessages newInstance(String param1, String param2) {
        MyMessages fragment = new MyMessages();
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
        return inflater.inflate(R.layout.fragment_my_messages, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
}

    private void connectComponents() {

        recyclerView = getView().findViewById(R.id.rvMyPosts);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        db = FirebaseServices.getInstance();
        messageArrayList = new ArrayList<Message>();
        getData();
        ucall = new ProductsCallBack() {
            @Override
            public void onCallback(ArrayList<Product> productsList) {
                messageAdapter = new MessageAdapter(getActivity(), messageArrayList, new MessageAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(Product product) {

                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.FrameLayoutMain, new TradeOrBuyFragment());
                        ft.commit();
                    }
                });
                recyclerView.setAdapter(messageAdapter);
            }
        };
    }
    private void getData()
    {
        db.getFire().collection("MyMessages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Message message = document.toObject(Message.class);
                                messageArrayList.add(document.toObject(Message.class));
                            }

                            ucall.onCallback(messageArrayList);


                        } else {
                            //Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}