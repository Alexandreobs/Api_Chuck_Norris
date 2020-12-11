package com.example.appchucknorris.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appchucknorris.R;
import com.example.appchucknorris.factorys.ViewModelFactory;
import com.example.appchucknorris.view.adaper.ChuckAdaper;
import com.example.appchucknorris.viewmodel.ChuckViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private List<String> resultList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChuckViewModel viewModel;
    private ChuckAdaper adaper;
    private ViewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chamadaViewModel();
        initViews();
        loading();
        recyclerView();
    }

    private void recyclerView() {
        recyclerView.setAdapter(adaper);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    private void chamadaViewModel() {
        factory = new ViewModelFactory();
        viewModel = ViewModelProviders.of(this, factory).get(ChuckViewModel.class);
        viewModel.getAllCategorias();
        viewModel.getListaCategoria().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> resultaLista) {
                adaper.autalizaLista(resultaLista);
            }
        });
    }

    private void loading() {
        viewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if (loading) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_pricipal);
        progressBar = findViewById(R.id.progress_bar);
        adaper = new ChuckAdaper(resultList);
    }
}
