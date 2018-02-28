package io.neverstoplearning.acviewmodel.home;

import android.support.v7.util.DiffUtil;

import java.util.List;

import io.neverstoplearning.acviewmodel.model.Repo;

/**
 * Created by mulasa.arunkumar on 28-02-2018.
 */

public class RepoDiffCallback extends DiffUtil.Callback {

    private List<Repo> oldList;
    private List<Repo> newList;

    RepoDiffCallback(List<Repo> oldList, List<Repo> newList){
        this.oldList = oldList;
        this.newList = newList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
