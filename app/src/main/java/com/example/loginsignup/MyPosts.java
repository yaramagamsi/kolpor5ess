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

import com.example.loginsignup.Adapters.MyAdapter;
import com.example.loginsignup.Classes.Product;
import com.example.loginsignup.Interface.ProductsCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPosts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPosts extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Product> productArrayList;
    MyAdapter myAdapter;

    ArrayList<String> productListPath;
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

    public MyPosts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPosts.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPosts newInstance(String param1, String param2) {
        MyPosts fragment = new MyPosts();
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
        return inflater.inflate(R.layout.fragment_my_posts, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
}

    private void connectComponents() {

        recyclerView = getView().findViewById(R.id.rvElectronicsList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        db = FirebaseServices.getInstance();
        productArrayList = new ArrayList<Product>();
        getData();
        ucall = new ProductsCallBack() {
            @Override
            public void onCallback(ArrayList<Product> productsList) {
                myAdapter = new MyAdapter(getActivity(), productArrayList, new MyAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(Product product) {

                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.FrameLayoutMain, new TradeOrBuyFragment());
                        ft.commit();
                    }

                    @Override
                    public void onItemClick(Message message) {

                    }
                });
                recyclerView.setAdapter(myAdapter);
            }
        };
    }

    private void getData()
    {
        db.getFire().collection("MyPosts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                productArrayList.add(document.toObject(Product.class));
                            }

                            ucall.onCallback(productArrayList);


                        } else {
                            //Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}