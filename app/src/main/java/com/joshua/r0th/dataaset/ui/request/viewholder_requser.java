package com.joshua.r0th.dataaset.ui.request;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.dataaset.R;

public class viewholder_requser extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    public TextView rvnama,rvjumlah,rvPenempatan,rvstatusawal;
    public viewholder_requser(@NonNull View itemView) {
        super(itemView);
        rvnama = itemView.findViewById(R.id.namarequestdariuser);
        rvjumlah = itemView.findViewById(R.id.jumlahrequestdariuser);
        rvPenempatan = itemView.findViewById(R.id.tempatrequestdariuser);
        rvstatusawal = itemView.findViewById(R.id.statusrequestuserawal);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Pilih Menu");
        menu.add(0,0,getAdapterPosition(), "Update");
        menu.add(0,1,getAdapterPosition(), "Delete");
    }
}