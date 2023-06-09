package com.example.loginsignup.Data;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginsignup.FirebaseServices;
import com.example.loginsignup.HomePageFragment;
import com.example.loginsignup.LogInFragment;
import com.example.loginsignup.Classes.Product;
import com.example.loginsignup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragments extends Fragment {


    private static final int RESULT_LOAD_IMG = 23;

    private EditText etName, etPrice;
    private ImageView ivPhoto;
    private Bitmap image;
    private Button btnAdd;
    private Spinner spnCategory;
    private FirebaseServices fbs;
    ActivityResultLauncher<String> mTakePhoto;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String imgLocation = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductsFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragments newInstance(String param1, String param2) {
        ProductsFragments fragment = new ProductsFragments();
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
        return inflater.inflate(R.layout.fragment_products_fragments, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {

        etName = getView().findViewById(R.id.etNameProducts);
        etPrice = getView().findViewById(R.id.etPriceProducts);
        ivPhoto = getView().findViewById(R.id.ivPhotoProducts);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });
        btnAdd = getView().findViewById(R.id.btnAddProducts);
        spnCategory = getView().findViewById(R.id.spnCategoryProducts);
        fbs = FirebaseServices.getInstance();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                String category = spnCategory.getSelectedItem().toString();
                if (name.trim().isEmpty() || price.trim().isEmpty() || category.trim().isEmpty()/*check the */) {
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int priceValue = Integer.valueOf(price);

                // (String category, String name, int price, String owner, String photo)
                Product p;

                if (imgLocation == null)
                    p = new Product(category, name, priceValue, "", "");
                else
                    p = new Product(category, name, priceValue, "",  UploadImageToFirebase());
                addProduct(p);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new HomePageFragment());
                ft.commit();
                }

            });

    }

    public void addProduct(Product p) {
        fbs.getFire().collection("Products")
                .add(p)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Log.d(TAG, "DocumentSnapshot added with ID:" + documentReference.getId());


                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v(TAG, "Error adding document", e);

                    }
                });
    }

    private void startIntentSenderForResult(int requestCode, int resultCode, Intent data) throws IOException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            image = BitmapFactory.decodeStream(getActivity().getAssets().open(selectedImage.getPath()));
            ivPhoto.setImageURI(selectedImage);
            // TODO: upload image and save location string
              UploadImageToFirebase();
        }
    }

    public void gotoLoginFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new LogInFragment());
        ft.commit();
    }

    private String UploadImageToFirebase() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageReference ref = fbs.getStorage().getReference("Pictures" + UUID.randomUUID().toString());
        UploadTask uploadTask = ref.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle unsuccessful uploads
            }

        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
// taskSnapshot. getMetadata () contains file metadata
            }
        });
        return ref.getPath();
    }


    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ivPhoto.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }



}


