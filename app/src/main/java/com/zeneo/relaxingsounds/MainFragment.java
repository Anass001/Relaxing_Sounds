package com.zeneo.relaxingsounds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeneo.relaxingsounds.adapter.MainAdapter;
import com.zeneo.relaxingsounds.model.MainItem;

import java.util.ArrayList;

public class MainFragment extends Fragment implements MainAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    public static ArrayList<MainItem> items = new ArrayList<>();
    private MainAdapter mainAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
        mainAdapter = new MainAdapter(getContext(), items);
        mainAdapter.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.main_rv);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void initializeData() {
        items.add(new MainItem("Ocean", R.drawable.ocean, R.raw.bird));
        items.add(new MainItem("Forest", R.drawable.forest, R.raw.bird));
        items.add(new MainItem("Night", R.drawable.night, R.raw.bird));
        items.add(new MainItem("Rain", R.drawable.rain, R.raw.bird));
        items.add(new MainItem("Lake", R.drawable.lake, R.raw.bird));
    }

    @Override
    public void onItemClick(int position) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        MainFragmentDirections.ActionMainFragmentToPlayerFragment action =  MainFragmentDirections.actionMainFragmentToPlayerFragment().setPosition(position);
        navController.navigate(action);
    }
}