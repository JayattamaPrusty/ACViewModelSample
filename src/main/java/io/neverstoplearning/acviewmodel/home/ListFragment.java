package io.neverstoplearning.acviewmodel.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.neverstoplearning.acviewmodel.R;
import io.neverstoplearning.acviewmodel.base.BaseApplication;
import io.neverstoplearning.acviewmodel.details.ListDetailsFragment;
import io.neverstoplearning.acviewmodel.details.RepoViewModel;
import io.neverstoplearning.acviewmodel.model.Repo;
import io.neverstoplearning.acviewmodel.viewmodels.ViewModelFactory;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */

public class ListFragment extends Fragment implements RepoSelectedListener {

    @Inject ViewModelFactory viewModelFactory;

    @BindView(R.id.recycler_view) RecyclerView listView;
    @BindView(R.id.tv_error) TextView errorTextView;
    @BindView(R.id.loading_view) View loadingView;

    private Unbinder unbinder;
    private ListViewModel viewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BaseApplication.getApplicationComponent(context).inject(this);
    }

    @Override
    public void onRepoSelected(Repo repo) {
        RepoViewModel repoViewModel = ViewModelProviders.of(getActivity(),viewModelFactory)
                .get(RepoViewModel.class);
        repoViewModel.setSelectedRepo(repo);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_container,new ListDetailsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_list,
                container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getContext()
                , DividerItemDecoration.VERTICAL));
        listView.setAdapter(new RepoListAdapter(viewModel, this,this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if (repos != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });
        viewModel.getError().observe(this, isError -> {
            //noinspection ConstantConditions
            if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(R.string.api_error_repos);
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });
        viewModel.getLoading().observe(this, isLoading -> {
            //noinspection ConstantConditions
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                errorTextView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }


}
