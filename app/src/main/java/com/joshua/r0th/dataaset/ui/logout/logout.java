package com.joshua.r0th.dataaset.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.joshua.r0th.dataaset.LoginActivity;
import com.joshua.r0th.dataaset.R;

public class logout extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.logout, container, false);
        FirebaseAuth.getInstance().signOut();//logout
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

        return root;
    }
}
