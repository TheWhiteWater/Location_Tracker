package nz.co.redice.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import nz.co.redice.myapplication.repository.Repository;

public class ViewModelFactory implements Factory {

    private final Repository mRepository;

    public ViewModelFactory(Repository repository) {
        mRepository = repository;
    }

    @Override
    public LocationViewModel create() {
        return new LocationViewModel(mRepository);
    }
}

interface Factory {
    LocationViewModel create();

    class T extends ViewModel {
    }
}
