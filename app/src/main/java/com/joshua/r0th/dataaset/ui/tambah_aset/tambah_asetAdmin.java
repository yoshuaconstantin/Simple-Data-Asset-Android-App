package com.joshua.r0th.dataaset.ui.tambah_aset;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joshua.r0th.dataaset.R;

public class tambah_asetAdmin extends Fragment {
    EditText jumlahbarang,idbarang,Hargasatuan,total;
    Button addData;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth fAuth;
    private DatabaseReference reference;
    FirebaseFirestore fStore;
    Spinner spinnertempat,status,namaaset;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tambah_aset, container, false);
        namaaset = root.findViewById(R.id.BnamaAset);
        jumlahbarang = root.findViewById(R.id.Cjumlah);
        spinnertempat = root.findViewById(R.id.Fspinertempat);
        idbarang = root.findViewById(R.id.Aidaset);
        Hargasatuan = root.findViewById(R.id.Dhargasatuan);
        addData = root.findViewById(R.id.tambahdata);
        status = root.findViewById(R.id.Gstatus);
        total = root.findViewById(R.id.ETotal);
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ///
        String[] arraySpinner = new String[] {
                "Ruang Dosen", "Ruang TU", "Ruang Dekan", "Ruang Wakil Dekan", "Ruang Sekertaris", "Ruang BPSI"
        };
        String[] arraystatus = new String[] {
                "Baik", "Normal", "Rusak", "Rusak Parah"
        };

        String[] arraynamaset = new String[] {
                "Meja Dosen", "Meja Rapat", "Meja Komputer", "Kursi", "Sapu", "Printer", "Stop Kontak","Dispenser"
        };


        ///
        Spinner s = root.findViewById(R.id.Fspinertempat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        ArrayAdapter<String> adapterstatus = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, arraystatus);
        ArrayAdapter<String> adapternamaaset = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, arraynamaset);
        adapterstatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        namaaset.setAdapter(adapternamaaset);
        s.setAdapter(adapter);
        status.setAdapter(adapterstatus);

        insertData();


    return root;
    }

    public void insertData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Data_aset");

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = idbarang.getText().toString();
                final String nama = namaaset.getSelectedItem().toString();
                final String jumlaH = jumlahbarang.getText().toString();
                final String hargasatuan = Hargasatuan.getText().toString();
                final String Total = total.getText().toString();
                final String penempatan = spinnertempat.getSelectedItem().toString();
                final String Status = status.getSelectedItem().toString();

                //
                   if(TextUtils.isEmpty(id)){
                       idbarang.setError("Nama Barang Tidak Boleh Kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(jumlaH)){
                    jumlahbarang.setError("Jumlah barang tidak boleh kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(hargasatuan)){
                    Hargasatuan.setError("Hargasatuan barang tidak boleh kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(Total)){
                    total.setError("total barang tidak boleh kosong !.");
                    return;
                }

                //
                final int jumlah= Integer.parseInt(jumlahbarang.getText().toString());
                reference = database.getReference("Data_aset");
                Query query = reference.child(nama+Status);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
//                            String cek = dataSnapshot.child(Status).child("gstatus").getValue(String.class);

                          /*  if (cek.equals(Status)){*/
                            int JUmlah = dataSnapshot.child("cjumlah").getValue(Integer.class);
                            int jumtambah = JUmlah + jumlah;
                            final Task<Void> hasil = FirebaseDatabase.getInstance().getReference("Data_aset").child(nama+Status).child("cjumlah").setValue(jumtambah);
                            hasil.addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    idbarang.setText("");
                                    Hargasatuan.setText("");
                                    jumlahbarang.setText("");
                                    total.setText("");
                                }
                            });
                                Toast.makeText(getContext(), "Data sudah di tambah untuk aset dan status yang sama !", Toast.LENGTH_LONG).show();
                                return;
//                        }

                        }else {
                            data_item data_item1 = new data_item(id,nama,jumlah,hargasatuan,penempatan,Total,Status);
                            myRef.child(nama+Status).setValue(data_item1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Berhasil menambah Data Aset !", Toast.LENGTH_SHORT).show();
                                    idbarang.setText("");
                                    Hargasatuan.setText("");
                                    jumlahbarang.setText("");
                                    total.setText("");

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Gagal menambah Data Aset", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


              /*  long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);*/



            }
        });
    }
}
