package com.example.testtest;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static int id = 0;
    Button btnRead;
    MyDatabaseHelper db;
    public EditText etSearch;
    Button btnUpdate;
    Button btnCreate;
    Button btnDelete;
    public Search() {
        // Required empty public constructor
    }
    public void delete_from_id()
    {
        id--;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnRead = view.findViewById(R.id.btnRead);
        etSearch = view.findViewById(R.id.etSearch);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        db = new MyDatabaseHelper(getContext());



        return view;
    }

    @Override
    public void onClick(View view) {

        if (view == btnRead)
        {
            FragmentManager fagman = getActivity().getSupportFragmentManager();
            ShowData ShowDataFag = (ShowData) fagman.findFragmentById(R.id.fag2);
            ShowDataFag.ShowTableData();
        }
        if(view == btnUpdate)
        {
            String s = etSearch.getText().toString();
            db.addItem(s);
            id++;
        }
        if (view == btnDelete)
        {
            db.deleteAllData();
        }
    }
}