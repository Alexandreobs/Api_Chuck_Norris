package com.example.appchucknorris.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchucknorris.model.pojos.Result;
import com.example.appchucknorris.model.repository.ChuckRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChuckViewModelPiadas extends ViewModel {

    private MutableLiveData<String> listPiadas = new MutableLiveData();
    private CompositeDisposable disposable = new CompositeDisposable();
    private ChuckRepository repository;
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public ChuckViewModelPiadas(@NonNull ChuckRepository chuckRepository) {
        this.repository = chuckRepository;
    }

    public LiveData<String> getPiadaLiveData() {
        return this.listPiadas;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void getAllPiada(String categorias) {
        disposable.add(
                repository.getListPiadas(categorias)
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
                        .subscribe(new Consumer<Result>() {
                                       @Override
                                       public void accept(Result result) throws Exception {
                                           listPiadas.setValue(result.getValue());
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
