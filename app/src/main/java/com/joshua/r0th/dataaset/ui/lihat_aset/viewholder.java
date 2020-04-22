package com.joshua.r0th.dataaset.ui.lihat_aset;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.dataaset.R;

public class viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    public TextView rvjenis,rvnama,rvjumlah;
    public viewholder(@NonNull View itemView) {
        super(itemView);
        rvnama = itemView.findViewById(R.id.nama);
        rvjenis = itemView.findViewById(R.id.jenisbarang);
        rvjumlah = itemView.findViewById(R.id.jumlahbarang);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Pilih Menu");
        menu.add(0,0,getAdapterPosition(), "Update");
        menu.add(0,1,getAdapterPosition(), "Delete");
    }
}