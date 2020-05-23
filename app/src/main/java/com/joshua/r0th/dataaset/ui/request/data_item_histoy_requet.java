package com.joshua.r0th.dataaset.ui.request;

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

public class data_item_histoy_requet extends RecyclerView.Adapter<data_item_histoy_requet.ViewHolder> {
    private Context context;
    private List<data_item_requser> items;
    public
    ImageView btndelete;
    String mRef;

    public data_item_histoy_requet(Context context, List<data_item_requser> items) {

        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_lihatrequestuser,parent,false);

        return new data_item_histoy_requet.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final data_item_requser item = items.get(position);
        holder.rvnama.setText(item.getBnamaAset());
        holder.rvjumlah.setText(item.getCjumlah());
        holder.rvPenempatan.setText(item.getDpenempatan());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rvnama,rvjumlah,rvPenempatan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rvnama = itemView.findViewById(R.id.namarequestdariuser);
            rvjumlah = itemView.findViewById(R.id.jumlahrequestdariuser);
            rvPenempatan= itemView.findViewById(R.id.tempatrequestdariuser);



        }
    }

}
