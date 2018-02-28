package io.neverstoplearning.acviewmodel.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;


import javax.inject.Inject;

import io.neverstoplearning.acviewmodel.model.Repo;
import io.neverstoplearning.acviewmodel.networking.RepoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */

public class RepoViewModel extends ViewModel {

    private MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();
    private Call<Repo> repoCall;
    private RepoService repoService;

    @Inject
    RepoViewModel(RepoService repoService) {
        this.repoService = repoService;
    }

    public LiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    public void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if (selectedRepo.getValue() == null) {
            // If We dont have Repo
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repoDetails) {
        repoCall = repoService.getRepoDetails(repoDetails[0], repoDetails[1]);
        repoCall.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                selectedRepo.setValue(response.body());
                repoCall = null;
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                repoCall = null;
            }
        });
    }

    public void saveToBundle(Bundle outState) {
        if (selectedRepo.getValue() != null) {
            outState.putStringArray("repo_details"
                    , new String[]{selectedRepo.getValue().owner.login
                            , selectedRepo.getValue().name});
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (repoCall != null) {
            repoCall.cancel();
            repoCall = null;
        }
    }
}
