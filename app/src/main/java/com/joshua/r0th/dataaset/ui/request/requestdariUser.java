package com.joshua.r0th.dataaset.ui.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.joshua.r0th.dataaset.R;
import com.joshua.r0th.dataaset.ui.tambah_aset.data_item;

import org.w3c.dom.Text;

public class requestdariUser extends Fragment {
    EditText jumlahbarang;
    Button addData;
    TextView statusawal;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Spinner spinnertempat,namaaset;
    StorageReference mStorageref;
    String userId;
    TextView namainput;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.requestdariuser, container, false);
        namaaset = root.findViewById(R.id.Anamasetreq);
        jumlahbarang = root.findViewById(R.id.Bjumlahreq);
        spinnertempat = root.findViewById(R.id.CtempatReq);
        statusawal = root.findViewById(R.id.statusrequestuserawal);
        namainput = root.findViewById(R.id.namainputrequestuser);

        addData = root.findViewById(R.id.tambahdata);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ///
        String[] arraySpinner = new String[] {
                "Ruang Dosen", "Ruang TU", "Ruang Dekan", "Ruang Wakil Dekan", "Ruang Sekertaris", "Ruang BPSI"
        };

        String[] arraynamaset = new String[] {
                "Meja Dosen", "Meja Rapat", "Meja Komputer", "Kursi", "Sapu", "Printer", "Stop Kontak","Dispenser"
        };


        ///
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        ArrayAdapter<String> adapternamaaset = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, arraynamaset);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        namaaset.setAdapter(adapternamaaset);
        spinnertempat.setAdapter(adapter);

        //
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                namainput.setText(documentSnapshot.getString("Username"));
            }
        });
        //

        insertData();


        return root;
    }

    public void insertData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("request_data");

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Namainput = namainput.getText().toString();
                String nama = namaaset.getSelectedItem().toString();
                String jumlah= jumlahbarang.getText().toString();
                String penempatan = spinnertempat.getSelectedItem().toString();
                String statusAwal = statusawal.getText().toString();


              /*  if(TextUtils.isEmpty(nama)){
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
                }*/

                long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);

                data_item_requser data_item1 = new data_item_requser(Namainput,nama,jumlah,penempatan,statusAwal);
                myRef.child(mOrderTime).setValue(data_item1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Berhasil Meminta Aset !", Toast.LENGTH_SHORT).show();
                        jumlahbarang.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Gagal Meminta Aset", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
