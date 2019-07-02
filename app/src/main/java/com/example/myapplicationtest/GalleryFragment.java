package com.example.myapplicationtest;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnGalleryFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {

    private final String TAG = "GalleryFragment";

    private GridView gridView;
    private ArrayList<File> files = new ArrayList<File>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGalleryFragmentInteractionListener mListener;

    public GalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        files = imageReader(Environment.getExternalStorageDirectory());

        gridView = v.findViewById(R.id.grid_layout);
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return files.size();
            }

            @Override
            public File getItem(int position) {
                return files.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                Bitmap bitmap = BitmapFactory.decodeFile(files.get(position).getPath());

                if (convertView == null){
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_tile, parent, false);
                }
                File item = getItem(position);
                ImageView imageView = convertView.findViewById(R.id.grid_tile);
                imageView.setImageBitmap(bitmap);

                return convertView;
            }
        });



        return v;
    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {
        File[] fileArray = externalStorageDirectory.listFiles();
        for (int i = 0; i < fileArray.length; i++) {
            if (fileArray[i].isDirectory()) {
                files.addAll(imageReader(fileArray[i]));
            } else {
                if (fileArray[i].getName().endsWith(".jpg")||fileArray[i].getName().endsWith(".jpeg")) {
                    files.add(fileArray[i]);
                }
            }
        }
        return files;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

            mListener.onFragmentInteraction(uri);


        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGalleryFragmentInteractionListener) {
            mListener = (OnGalleryFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGalleryFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGalleryFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
