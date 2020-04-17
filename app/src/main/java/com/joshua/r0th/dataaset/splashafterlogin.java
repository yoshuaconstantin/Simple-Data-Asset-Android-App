package com.joshua.r0th.dataaset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class splashafterlogin extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashcheckafterlogin);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = fAuth.getCurrentUser().getUid();
            //mengambil data dari firestore sesuai dengan userID yang telah di daftarkan / yang sedang berjalan
            DocumentReference documentReference = fStore.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String statuslvl = documentSnapshot.getString("Tipe");
                    Toast.makeText(getApplicationContext(),statuslvl,Toast.LENGTH_SHORT).show();
                if (statuslvl.contains("admin")){
                    Intent intent = new Intent(getApplicationContext(), MainActivityAdmin.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                }

            });

        }

    }
}
