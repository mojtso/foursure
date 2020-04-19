package net.foursure.potter4sure.ui.main.houses;

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
import net.foursure.potter4sure.models.House;
import net.foursure.potter4sure.models.Spell;
import net.foursure.potter4sure.ui.main.spells.SpellsViewModel;
import net.foursure.potter4sure.util.Resource;
import net.foursure.potter4sure.util.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HousesFragment extends DaggerFragment {

    private static final String TAG = "HousesFragment";

    private RecyclerView recyclerView;

    private HousesViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    HousesRecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_houses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view_houses);
        viewModel = new ViewModelProvider(this, providerFactory).get(HousesViewModel.class);
        initRecyclerView();
        subscribeHouseObservers();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void subscribeHouseObservers() {
        viewModel.observeHouses().removeObservers(getViewLifecycleOwner());
        viewModel.observeHouses().observe(getViewLifecycleOwner(), new Observer<Resource<List<House>>>() {
            @Override
            public void onChanged(Resource<List<House>> listResource) {
                if(listResource.data != null) {
                    if(listResource.data.size() > 0) {
                        switch (listResource.status) {
                            case LOADING: {
                                Log.d(TAG, "onChanged: Loading...");
                                break;
                            }
                            case SUCCESS: {
                                Log.d(TAG, "onChanged: Successful");
                                adapter.setHouses(listResource.data);
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
