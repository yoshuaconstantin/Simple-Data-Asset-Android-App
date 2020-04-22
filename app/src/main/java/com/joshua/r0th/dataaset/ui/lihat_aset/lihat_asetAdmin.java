package com.joshua.r0th.dataaset.ui.lihat_aset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joshua.r0th.dataaset.R;
import com.joshua.r0th.dataaset.ui.tambah_aset.data_item;

import java.util.List;

public class lihat_asetAdmin extends Fragment {

    private RecyclerView recyclerView;

    private data_item_histoy adapter;
    private List<data_item> items;
    private DatabaseReference reference;
    private DatabaseReference reference2;
    private FirebaseDatabase database;
    FirebaseRecyclerOptions<data_item> options;
    FirebaseRecyclerAdapter<data_item, viewholder> adapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lihat_aset, container, false);
        recyclerView = root.findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Data_aset");

        setHasOptionsMenu(true);
        showtask();
        return root;


    }
    public void showtask(){
        options = new FirebaseRecyclerOptions.Builder<data_item>()
                .setQuery(reference, data_item.class)
                .build();

        adapter2 = new FirebaseRecyclerAdapter<data_item, viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewholder viewholder, int i, @NonNull data_item data_item) {

                viewholder.rvnama.setText(data_item.getAnama());
                viewholder.rvjenis.setText(data_item.getBjenis());
                viewholder.rvjumlah.setText(data_item.getCjumlah());

            }

            @NonNull
            @Override
            public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemview = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_lihat_aset, parent, false);
                return new viewholder(itemview);
            }
        };
        adapter2.startListening();
        recyclerView.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

    }
}
