package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindPlaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindPlaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindPlaceFragment extends Fragment implements View.OnClickListener {

    private Button buttonSearch;
    private EditText editTextSearch;
    private ListView listViewSearchResults;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FindPlaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindPlaceFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static FindPlaceFragment newInstance(String param1, String param2) {
        FindPlaceFragment fragment = new FindPlaceFragment();
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
        View v = inflater.inflate(R.layout.fragment_find_place, container, false);

        buttonSearch = v.findViewById(R.id.fragment_find_place_button_search);
        buttonSearch.setOnClickListener(this);
        editTextSearch = v.findViewById(R.id.fragment_find_place_editText_search);
        listViewSearchResults = v.findViewById(R.id.fragment_find_place_listView_searchResults);
        listViewSearchResults.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return "price";
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.grid_tile, parent, false);
                }

                TextView tdesction = convertView.findViewById(R.id.grid_tile_textView_desciption);
                TextView tsquare = convertView.findViewById(R.id.grid_tile_textView_squareM);

                tdesction.setText("super hyggeligt sted. \nting rådner som regel ikke her");
                tsquare.setText("størrelse : "+position+" m³");

                return convertView;
            }
        });


        return v;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(this.buttonSearch)){
            int pos;
            try {
                pos = Integer.parseInt(editTextSearch.getText().toString());
                listViewSearchResults.smoothScrollToPosition(pos);
                hideKeyboardFrom(getContext(), this.getView());
            } catch (Exception e){
                Toast.makeText(getContext(), "skriv et tal", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
