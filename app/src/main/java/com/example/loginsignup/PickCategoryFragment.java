package com.example.loginsignup;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PickCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class    PickCategoryFragment extends Fragment {


    private TextView tvElectronics, tvClothes, tvAnimals, tvSport, tvAccessories;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PickCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PickCategoryFragment newInstance(String param1, String param2) {
        PickCategoryFragment fragment = new PickCategoryFragment();
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
        return inflater.inflate(R.layout.fragment_pick_category, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    @SuppressLint("CutPasteId")
    private void connectComponents() {

        tvAccessories = getView().findViewById(R.id.tvSportPickCategoryFragment);
        tvAccessories.setOnClickListener(v -> {
            getActivity().getIntent().putExtra("category", "Accessories");
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FrameLayoutMain, new ProductsListFragment());
            ft.commit();
        });

        tvSport = getView().findViewById(R.id.tvSportPickCategoryFragment);
        tvSport.setOnClickListener(v -> {
            getActivity().getIntent().putExtra("category", "Sport");
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FrameLayoutMain, new ProductsListFragment());
            ft.commit();
        });

        tvAnimals = getView().findViewById(R.id.tvAnimalsPickCategoryFragment);
        tvAnimals.setOnClickListener(v -> {
            getActivity().getIntent().putExtra("category", "Animals");
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FrameLayoutMain, new ProductsListFragment());
            ft.commit();
        });

        tvClothes = getView().findViewById(R.id.tvClothesPickCategoryFragment);
        tvClothes.setOnClickListener(v -> {
            getActivity().getIntent().putExtra("category", "Clothes");
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FrameLayoutMain, new ProductsListFragment());
            ft.commit();
        });



        tvElectronics = getView().findViewById(R.id.tvElectronicsPickCategoryFragment);
        tvElectronics.setOnClickListener(view -> {
            getActivity().getIntent().putExtra("category", "electronics");
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FrameLayoutMain, new ProductsListFragment());
            ft.commit();

        });
    }
}