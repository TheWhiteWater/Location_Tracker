package nz.co.redice.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nz.co.redice.myapplication.databinding.AddressItemBinding;
import nz.co.redice.myapplication.repository.models.LocationModel;

public class Adapter extends RecyclerView.Adapter<Adapter.AddressHolder> {

    private List<LocationModel> locationList = new ArrayList<>();
    private SelectedItemListener mListener;

    public Adapter(SelectedItemListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AddressItemBinding itemBinding = AddressItemBinding.inflate(inflater, parent, false);
        return new AddressHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressHolder holder, int position) {
        holder.bind(locationList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void addNewLocation(LocationModel locationModel) {
        locationList.add(locationModel);
        notifyItemChanged(locationList.size() - 1);
    }


    /**
     *
     */

    class AddressHolder extends RecyclerView.ViewHolder {

        private AddressItemBinding mItemBinding;

        public AddressHolder(AddressItemBinding binding) {
            super(binding.getRoot());
            mItemBinding = binding;
        }


        void bind(LocationModel model, SelectedItemListener listener) {
            mItemBinding.setLocationModel(model);
            mItemBinding.itemLayout.setOnClickListener(v -> {
                if (model != null)
                    listener.onClick(model);
            });
        }

    }


    /**
     * Custom Listener
     */
    interface SelectedItemListener {
        void onClick(LocationModel model);
    }

}
