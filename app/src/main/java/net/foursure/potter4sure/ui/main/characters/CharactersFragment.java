package net.foursure.potter4sure.ui.main.characters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.foursure.potter4sure.R;
import net.foursure.potter4sure.models.Characteri;
import net.foursure.potter4sure.models.House;
import net.foursure.potter4sure.ui.main.houses.HousesViewModel;
import net.foursure.potter4sure.util.Resource;
import net.foursure.potter4sure.util.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CharactersFragment extends DaggerFragment {

    private static final String TAG = "CharactersFragment";

    private RecyclerView recyclerView;

    private CharactersViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    CharactersRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_characters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_characters);
        viewModel = new ViewModelProvider(this, providerFactory).get(CharactersViewModel.class);
        initRecyclerView();
        subscribeCharactersObservers();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void subscribeCharactersObservers() {
        viewModel.observeCharacters().removeObservers(getViewLifecycleOwner());
        viewModel.observeCharacters().observe(getViewLifecycleOwner(), new Observer<Resource<List<Characteri>>>() {
            @Override
            public void onChanged(Resource<List<Characteri>> listResource) {
                if(listResource.data != null) {
                    if(listResource.data.size() > 0) {
                        switch (listResource.status) {
                            case LOADING: {
                                Log.d(TAG, "onChanged: Loading...");
                                break;
                            }
                            case SUCCESS: {
                                Log.d(TAG, "onChanged: Successful" + listResource.data.size());
                                adapter.setCharacters(listResource.data);
                                break;
                            }
                            case ERROR: {
                                Log.d(TAG, "onChanged: Error...");
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
}
