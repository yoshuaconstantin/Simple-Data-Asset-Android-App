package com.joshua.r0th.dataaset.ui.lihat_aset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.dataaset.R;
import com.joshua.r0th.dataaset.ui.tambah_aset.data_item;

import java.util.List;

public class data_item_histoy extends RecyclerView.Adapter<data_item_histoy.ViewHolder> {
    private Context context;
    private List<data_item> items;
    public
    ImageView btndelete;
    String mRef;

    public data_item_histoy(Context context, List<data_item> items) {

        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_lihat_aset,parent,false);

        return new data_item_histoy.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final data_item item = items.get(position);
        holder.rvnama.setText(item.getBnama());
        holder.rvjumlah.setText(item.getCjumlah());
        holder.rvHsatuan.setText(item.getDhargasatuan());
        holder.rvTotal.setText(item.getEtotal());
        holder.rvPenempatan.setText(item.getFtempat());
        holder.rvstatus.setText(item.getGstatus());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rvnama,rvjumlah,rvTotal,rvPenempatan,rvHsatuan,rvstatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rvnama = itemView.findViewById(R.id.nama);
            rvjumlah = itemView.findViewById(R.id.jumlahbarang);
            rvHsatuan= itemView.findViewById(R.id.hargasatuan);
            rvTotal= itemView.findViewById(R.id.totalharga);
            rvPenempatan= itemView.findViewById(R.id.tempat);
            rvstatus = itemView.findViewById(R.id.statusshow);



        }
    }

}
