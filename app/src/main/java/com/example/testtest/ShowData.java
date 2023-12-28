package com.example.testtest;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowData extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TableLayout tableLayout;
    public ShowData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowData.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowData newInstance(String param1, String param2) {
        ShowData fragment = new ShowData();
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
    MyDatabaseHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_show_data, container, false);
        db = new MyDatabaseHelper(requireContext());
        tableLayout = view.findViewById(R.id.tbData);


        return view;
    }
    public void ShowTableData(Cursor cursor)
    {
        cursor = db.readAllData();
        cursor.moveToFirst();
        int i = 0;
        while (cursor.moveToNext())
        {
            String s = cursor.getString(i);
            TableRow tableRow = new TableRow(requireContext());
            TextView textView = new TextView(requireContext());
            TextView textView1 = new TextView(requireContext());
            textView.setText(s);
            textView1.setText(i);
            i++;
            tableRow.addView(textView);
            tableRow.addView(textView1);
            tableLayout.addView(tableRow);
        }
    }
}