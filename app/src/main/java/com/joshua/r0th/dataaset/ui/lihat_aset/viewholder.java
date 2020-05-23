package com.joshua.r0th.dataaset.ui.lihat_aset;

import android.view.ContextMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.dataaset.R;

public class viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    public TextView rvHsatuan,rvnama,rvjumlah,rvTotal,rvPenempatan,rvstatus;
    public viewholder(@NonNull View itemView) {
        super(itemView);
        rvnama = itemView.findViewById(R.id.nama);
        rvjumlah = itemView.findViewById(R.id.jumlahbarang);
        rvHsatuan = itemView.findViewById(R.id.hargasatuan);
        rvTotal  = itemView.findViewById(R.id.totalharga);
        rvPenempatan = itemView.findViewById(R.id.tempat);
        rvstatus= itemView.findViewById(R.id.statusshow);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Pilih Menu");
        menu.add(0,0,getAdapterPosition(), "Update");
        menu.add(0,1,getAdapterPosition(), "Delete");
    }
}