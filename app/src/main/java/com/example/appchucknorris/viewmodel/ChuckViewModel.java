package com.example.appchucknorris.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchucknorris.model.repository.ChuckRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChuckViewModel extends ViewModel {

    private MutableLiveData<List<String>> listMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private ChuckRepository repository;
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public ChuckViewModel(ChuckRepository chuckRepository) {
        this.repository = chuckRepository;
    }

    public LiveData<List<String>> getListaCategoria() {
        return this.listMutableLiveData;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void getAllCategorias() {
        disposable.add(
                repository.getList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                loading.setValue(true);
                            }
                        })
                        .doAfterTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                loading.setValue(false);
                            }
                        })
                        .subscribe(new Consumer<List<String>>() {
                                       @Override
                                       public void accept(List<String> result) throws Exception {
                                           listMutableLiveData.setValue(result);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Log.i("LOG", "Erro" + throwable.getMessage());
                                    }
                                }
                        )
        );
    }
}