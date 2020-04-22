package com.joshua.r0th.dataaset.ui.tambah_aset;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joshua.r0th.dataaset.R;

public class tambah_asetAdmin extends Fragment {
EditText namabarang,jenisbarang,jumlahbarang;
    Button addData;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tambah_aset, container, false);
        namabarang = root.findViewById(R.id.Anamabarang);
        jenisbarang = root.findViewById(R.id.Bjenisbarang);
        jumlahbarang = root.findViewById(R.id.Cjumlahbarang);
        addData = root.findViewById(R.id.tambahdata);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        insertData();
    return root;
    }

    public void insertData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Data_aset");

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namabarang.getText().toString();
                String jenis = jenisbarang.getText().toString();
                String jumlah= jumlahbarang.getText().toString();

                if(TextUtils.isEmpty(nama)){
                    namabarang.setError("Nama Barang Tidak Boleh Kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(jenis)){
                    jenisbarang.setError("Jenis barang tidak boleh kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(jumlah)){
                    jumlahbarang.setError("Jumlah barang tidak boleh kosong !.");
                    return;
                }

                long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);

                data_item data_item1 = new data_item(nama, jenis, jumlah);
                myRef.child(mOrderTime).setValue(data_item1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Berhasil menambah Data Aset !", Toast.LENGTH_SHORT).show();
                        namabarang.setText("");
                        jenisbarang.setText("");
                        jumlahbarang.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Gagal menambah Data Aset", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
