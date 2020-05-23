package com.joshua.r0th.dataaset.ui.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.joshua.r0th.dataaset.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryRequestAdmin extends Fragment {
    private data_adapter madapter;
    ValueEventListener mdblistener;
    FirebaseAuth fAuth;
    String userId;
    private DatabaseReference ref;
    FirebaseStorage mstorage;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabaseRef;
    private List<data_item_requser> mUpload;
    private FirebaseDatabase database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.history_request_admin, container, false);
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("request_data");
        mstorage = FirebaseStorage.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        recyclerView = root.findViewById(R.id.rec_historyreqadmin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mUpload = new ArrayList<>();
        madapter = new data_adapter(getContext(),mUpload);
        recyclerView.setAdapter(madapter);
        database = FirebaseDatabase.getInstance();
        setHasOptionsMenu(true);

        //
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("request_data");
        Query query = mDatabaseRef.orderByChild("estatusawal").equalTo("diterima");
        mdblistener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUpload.clear();
                for (DataSnapshot postsnapshot: dataSnapshot.getChildren()){
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
}
