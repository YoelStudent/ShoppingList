package com.example.testtest;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowData extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Button> arrayList;
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
        tableLayout.removeAllViews();
        ShowTableData();



        return view;
    }
    public void ShowTableData()
    {
        tableLayout.removeAllViews();
        Cursor cursor = db.readAllData();
        arrayList = new ArrayList<Button>();
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast())
        {
            String s = cursor.getString(1);

            TableRow tableRow = new TableRow(requireContext());
            TextView textView = new TextView(requireContext());
            TextView textView1 = new TextView(requireContext());
            Button btn = new Button(requireContext());
            btn.setId(i);
            btn.setOnClickListener(this);
            btn.setHint("DELETE");
            arrayList.add(btn);
            textView.setText(s);
            i++;
            tableRow.addView(textView);
            tableRow.addView(textView1);
            tableRow.addView(btn);
            tableLayout.addView(tableRow);
            cursor.moveToNext();
        }
        db.close();
    }

    @Override
    public void onClick(View v)
    {
        for (int i = 0; i < arrayList.size(); i++)
        {
            if (v== arrayList.get(i))
            {
                db.deleteOneRow(String.valueOf(i));
                ShowTableData();
            }
        }



    }
}