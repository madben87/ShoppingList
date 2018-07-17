package com.ben.shoppinglist.data.room.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.data.ListPresenterCallback;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RoomDataManager implements DataManager {

    private Context context;
    private RoomDB db;

    public RoomDataManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, RoomDB.class, RoomDB.getDbName()).build();
    }

    @Override
    public void newShoppingItem(final ListPresenterCallback callback, final ShoppingItem item) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.shoppingItemsDao().insert(item);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                callback.itemIsAdded();
            }

            @Override
            public void onError(Throwable e) {
                callback.itemNotAdded();
            }
        });
    }

    @Override
    public void getShoppingItems(final ListPresenterCallback callback) {
        db.shoppingItemsDao().getAll().observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ShoppingItem>>() {
                    @Override
                    public void accept(List<ShoppingItem> shoppingItems) throws Exception {
                        callback.showList(shoppingItems);
                    }
                });
    }

    @Override
    public void getHistoryItems(final ListPresenterCallback callback) {

    }

    @Override
    public void markAsPurchased(final ListPresenterCallback callback, final ShoppingItem item) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.shoppingItemsDao().delete(item);
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        callback.itemMarkAsPurchased();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.itemNotMarkAsPurchased();
                    }
                });
    }

    @Override
    public void markAsPurchased(final ListPresenterCallback callback, final List<ShoppingItem> item) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.shoppingItemsDao().delete(item);
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        callback.itemMarkAsPurchased();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.itemNotMarkAsPurchased();
                    }
                });
    }
}
