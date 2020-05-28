package com.joshua.r0th.dataaset.ui.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.joshua.r0th.dataaset.R;
import com.joshua.r0th.dataaset.ui.lihat_aset.data_item_histoy;
import com.joshua.r0th.dataaset.ui.lihat_aset.viewholder;
import com.joshua.r0th.dataaset.ui.tambah_aset.data_item;

import java.util.ArrayList;
import java.util.List;

public class lihatrequesUser extends Fragment implements data_adapter.OnItemClickListener{
    private RecyclerView recyclerView;
    SharedPreferences sharedpreferences;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    FirebaseRecyclerOptions<data_item_requser> options;
    FirebaseRecyclerAdapter<data_item_requser, viewholder_requser> adapter2;
    private data_adapter madapter;
    ValueEventListener mdblistener;
    FirebaseAuth fAuth;
    String userId;
    private DatabaseReference ref,ref2;
    FirebaseStorage mstorage;
    private DatabaseReference mDatabaseRef;
    private List<data_item_requser> mUpload;
    //
    int jumlahkeint;
    int jumlahhitung;
    String namaAset;
    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lihatrequestdariuser, container, false);
        sharedpreferences = this.getActivity().getSharedPreferences("myref", Context.MODE_PRIVATE);
        /*jumlahkeint = sharedpreferences.getInt("jumlahkeint",0);
        jumlahhitung = sharedpreferences.getInt("jumlahkeint",0);
        namaAset = sharedpreferences.getString("namaAset","Meja Dosen");*/
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("data_verifikasi");
        ref2 = database.getReference("Data_aset");
        mstorage = FirebaseStorage.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        recyclerView = root.findViewById(R.id.reclihatuser);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mUpload = new ArrayList<>();
        madapter = new data_adapter(getContext(),mUpload);
        recyclerView.setAdapter(madapter);
        madapter.setOnItemClickListener(lihatrequesUser.this);
        database = FirebaseDatabase.getInstance();
        setHasOptionsMenu(true);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("request_data");
        Query query = mDatabaseRef.orderByChild("estatusawal").equalTo("Pending");
        mdblistener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUpload.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    data_item_requser Uploads = postsnapshot.getValue(data_item_requser.class);
                    Uploads.setMkey(postsnapshot.getKey());
                    mUpload.add(Uploads);
                }
                madapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mdblistener );
    }

    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void updateclick(int position) {
        //
        //
        //
        data_item_requser selectedItem = mUpload.get(position);
        final String selectedKey = selectedItem.getMkey();
        hitungan(selectedKey);
        final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("request_data").child(selectedKey).child("estatusawal");

        reference = database.getReference("Data_aset");

        mDatabase2.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mDatabase2.setValue("diterima");
                hitungan(selectedKey);


                //Toast.makeText(getContext()," BERHASIL ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hitungan(selectedKey);
            }
        });


        hitungan(selectedKey);
        //Toast.makeText(getContext()," 1 " + namaAset + Jumlah + jumlahhitung ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteclick(int position) {

    }
public void hitungan(String selectedKey){
    final DatabaseReference mdatabasenama = FirebaseDatabase.getInstance().getReference("request_data").child(selectedKey).child("bnamaAset");
    final Query mdatabasetstatus = FirebaseDatabase.getInstance().getReference("request_data").child(selectedKey).child("cjumlah");
    reference = database.getReference("Data_aset");
    mdatabasenama.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String mNamaAset = dataSnapshot.getValue(String.class);
            namaAset = mNamaAset;


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    mdatabasetstatus.addValueEventListener(new ValueEventListener() {
        String jumlah;
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            jumlah  = dataSnapshot.getValue(String.class);
            jumlahkeint = Integer.parseInt(jumlah);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    Query query = reference.orderByChild(namaAset+"Baik");
    query.addListenerForSingleValueEvent(new ValueEventListener() {
        int jumlahawal;
        int jumlahhitung = 0;
        //int jumlahkeint;
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                jumlahawal = dataSnapshot.child(namaAset+"Baik").child("cjumlah").getValue(Integer.class);
                jumlahhitung = jumlahawal - jumlahkeint;

                final Task<Void> hasil = FirebaseDatabase.getInstance().getReference("Data_aset").child(namaAset + "Baik").child("cjumlah").setValue(jumlahhitung);
                hasil.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


}

}
