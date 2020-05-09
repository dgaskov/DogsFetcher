package ru.nsu.dogsFetcher.presentation.repos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.nsu.dogsFetcher.R;
import ru.nsu.dogsFetcher.data.model.Repo;
import ru.nsu.dogsFetcher.data.model.User;
import ru.nsu.dogsFetcher.presentation.repos.list.ReposAdapter;

public class ReposActivity extends AppCompatActivity {
    public static String USER_KEY = "user_key";

    private RecyclerView rvRepos;
    private ImageView ivAvatar;
    private TextView tvLogin;
    private ReposAdapter adapter;

    private Context context;

    private ReposViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        context = this;

        rvRepos = findViewById(R.id.rvRepos);
        ivAvatar = findViewById(R.id.ivAvatar);
        tvLogin = findViewById(R.id.tvLogin);
        adapter = new ReposAdapter();

        initList();

        String url = (String)getIntent().getSerializableExtra(USER_KEY);

        viewModel = ViewModelProviders.of(this, new ReposViewModelFactory(url)).get(ReposViewModel.class);

        viewModel.observeUserLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String user) {
                Glide.with(context)
                        .load(Uri.parse(user))
                        .into(ivAvatar);
            }
        });

        viewModel.observeShibesLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> repos) {
                adapter.setItems(repos);
            }
        });
    }

    private void initList() {
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        rvRepos.setAdapter(adapter);
    }
}
