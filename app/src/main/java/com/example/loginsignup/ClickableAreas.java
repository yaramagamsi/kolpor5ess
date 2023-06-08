package com.example.loginsignup;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClickableAreas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClickableAreas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClickableAreas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClickableAreas.
     */
    // TODO: Rename and change types and number of parameters
    public static ClickableAreas newInstance(String param1, String param2) {
        ClickableAreas fragment = new ClickableAreas();
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
        return inflater.inflate(R.layout.fragment_clickable_areas, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {

        ImageView imageView = getView().findViewById(R.id.ivCategoryClickable);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (action == MotionEvent.ACTION_UP) {
                    // Handle click event for specific areas
                    if (isInClickableArea(x, y)) {

                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.FrameLayoutMain, new ProductsListFragment());
                        ft.commit();
                        // Do something when the area is clicked
                        // Example: launch an activity or show a dialog
                    }
                }
                return true;
            }
        });

    }
    private boolean isInClickableArea(int x, int y) {
        // Define the coordinates of your clickable areas
        Rect clickableArea = new Rect(0, 170, 144, 0);
        return clickableArea.contains(x, y);
    }

}