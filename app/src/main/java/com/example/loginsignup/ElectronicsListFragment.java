package com.example.loginsignup;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ElectronicsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ElectronicsListFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Product> productArrayList;
    MyAdapter myAdapter;
    FirebaseServices db;
    ProgressDialog progressDialog;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ElectronicsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClothesListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ElectronicsListFragment newInstance(String param1, String param2) {
        ElectronicsListFragment fragment = new ElectronicsListFragment();
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
        return inflater.inflate(R.layout.fragment_electronics_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {

        Context context = getActivity();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();



        recyclerView = getView().findViewById(R.id.rvElectronicsList);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        db = FirebaseServices.getInstance();
        productArrayList = new ArrayList<Product>();


        myAdapter = new MyAdapter(ElectronicsListFragment.this.productArrayList);


        recyclerView.setAdapter(myAdapter);

        EventChangeListener();


    }

    private void EventChangeListener() {

        db.getFire().collection("Products").orderBy("Item Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                       if (error != null){

                           if (progressDialog.isShowing())
                               progressDialog.dismiss();

                           Log.e("Firestore error", error.getMessage());
                           return;
                       }

                       for (DocumentChange documentChange : value.getDocumentChanges()){

                           if (documentChange.getType() == DocumentChange.Type.ADDED){

                               productArrayList.add(documentChange.getDocument().toObject(Product.class));

                           }

                           myAdapter.notifyDataSetChanged();
                           if (progressDialog.isShowing())
                               progressDialog.dismiss();


                       }


                    }
                });




    }
}