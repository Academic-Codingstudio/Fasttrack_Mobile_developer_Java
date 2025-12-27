package com.codingstudio.bookcatalog.viewFregment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.R;
import com.codingstudio.bookcatalog.asset.GridSpacingItemDecoration;
import com.codingstudio.bookcatalog.asset.StoryAdapter;

import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView rvSearch;
    EditText etSearch;

    public SearchFragment() {
        super(R.layout.fragment_search);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        etSearch = view.findViewById(R.id.etSearch);
        rvSearch = view.findViewById(R.id.rvSearch);
        int spanCount = 2;
        int spacing = 8;
        boolean includeEdge = true;

        rvSearch.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        rvSearch.addItemDecoration(
                new GridSpacingItemDecoration(spanCount, spacing, includeEdge)
        );

        // dummy data
        List<Integer> dummyImages = Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        );

        StoryAdapter adapter = new StoryAdapter(getContext(), dummyImages);
        rvSearch.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nanti di sini bisa filter data
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}

