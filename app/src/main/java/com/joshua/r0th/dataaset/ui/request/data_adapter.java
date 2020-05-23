package com.joshua.r0th.dataaset.ui.request;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.dataaset.R;

import java.util.List;

public class data_adapter extends RecyclerView.Adapter<data_adapter.DataViewHolder> {
    private Context mContext;
    private List<data_item_requser> mUpload;
    private OnItemClickListener mlistener;

    public data_adapter(Context context, List<data_item_requser> upload) {
        mContext = context;
        mUpload = upload;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_lihatrequestuser, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        data_item_requser uploadCurrent = mUpload.get(position);
        holder.txtnama.setText(uploadCurrent.getAnamainput());
        holder.namaaset.setText(uploadCurrent.getBnamaAset());
        holder.jumlah.setText(uploadCurrent.getCjumlah());
        holder.penempatan.setText(uploadCurrent.getDpenempatan());
        holder.status.setText(uploadCurrent.getEstatusawal());
    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView txtnama,status,jumlah,penempatan,namaaset;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnama = itemView.findViewById(R.id.namainputuser);
            namaaset = itemView.findViewById(R.id.namarequestdariuser);
            jumlah = itemView.findViewById(R.id.jumlahrequestdariuser);
            penempatan= itemView.findViewById(R.id.tempatrequestdariuser);
            status = itemView.findViewById(R.id.statusrequest);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mlistener != null){
                int position  = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            mlistener.updateclick(position);
                        case 2:
                            mlistener.deleteclick(position);
                            return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (mlistener != null){
                int position  = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mlistener.OnItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Pilih Menu");
            MenuItem Update = menu.add(Menu.NONE,1,1 , "Terima Permintaan");


            Update.setOnMenuItemClickListener(this);
        }
    }

    public interface OnItemClickListener{

        void OnItemClick(int position);
        void updateclick(int position);
        void deleteclick(int position);


    }
public void setOnItemClickListener(OnItemClickListener listener){
mlistener = listener;
}
}
