package com.example.laba2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

    public class PageFragment extends Fragment {
    private int pageNumber;
    private Technologies.Technology technology;

    public static PageFragment newInstance(int page, Technologies.Technology technology) {
        PageFragment fragment = new PageFragment(technology);
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragment(Technologies.Technology technology) {
        this.technology = technology;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_page, container, false);

        // Найдем все View
        TextView pageHeader = (TextView)result.findViewById(R.id.nameInPage);
        ImageView imageTech = (ImageView)result.findViewById(R.id.imageView4);
        TextView helpText = (TextView)result.findViewById(R.id.helpText);

        //Присвоим все значения
        pageHeader.setText(technology.getName());
        imageTech.setImageBitmap(technology.getImage());
        helpText.setText(technology.getHelptext());
        return result;
    }
}
