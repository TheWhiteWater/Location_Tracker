package nz.co.redice.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import nz.co.redice.myapplication.databinding.FragmentListBinding;
import nz.co.redice.myapplication.di.MyApplication;
import nz.co.redice.myapplication.repository.models.LocationModel;
import nz.co.redice.myapplication.viewmodel.LocationViewModel;


public class ListFragment extends Fragment implements Adapter.SelectedItemListener {

    private FragmentListBinding mBinding;
    private LocationViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentListBinding.inflate(inflater, container, false);
        View view = mBinding.getRoot();

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.myRecyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(this);

        // specify an adapter (see also next example)
        mBinding.myRecyclerView.setAdapter(adapter);

        mViewModel = new LocationViewModel(((MyApplication) getActivity().getApplication()).getRepository());

        mViewModel.getLastKnowLocation().observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel model) {
                adapter.addNewLocation(model);
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onClick(LocationModel model) {
        Toast.makeText(getContext(), model.toString(), Toast.LENGTH_SHORT).show();
    }
}