package io.neverstoplearning.acviewmodel.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.neverstoplearning.acviewmodel.R;


/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */

public class ListDetailsFragment extends Fragment {

    @BindView(R.id.tv_reponame)
    TextView repoName;
    @BindView(R.id.tv_repo_description)
    TextView repoDesc;
    @BindView(R.id.tv_stars)
    TextView starCount;
    @BindView(R.id.tv_forks)
    TextView forks;

     private Unbinder unbinder;
    private RepoViewModel repoViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_frag, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repoViewModel = ViewModelProviders.of(getActivity()).get(RepoViewModel.class);
        // In case of process got destroyed
        repoViewModel.restoreFromBundle(savedInstanceState);
        displaySelectedRepo();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        repoViewModel.saveToBundle(outState);
    }

    private void displaySelectedRepo() {
        repoViewModel.getSelectedRepo().observe(this, repo -> {
            if(repo != null){
                repoName.setText(repo.name);
                repoDesc.setText(repo.description);
                starCount.setText(String.valueOf(repo.stars));
                forks.setText(String.valueOf(repo.forks));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }

    }
}
