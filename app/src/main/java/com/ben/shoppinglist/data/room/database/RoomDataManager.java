package com.ben.shoppinglist.data.room.database;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.room.model.HistoryItem;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.util.DAOUtil;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                callback.itemIsAdded(item);
            }

            @Override
            public void onError(Throwable e) {
                callback.itemNotAdded();
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getShoppingItems(final ListPresenterCallback callback) {
        db.shoppingItemsDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ShoppingItem>>() {
                    @Override
                    public void accept(List<ShoppingItem> list) throws Exception {
                        callback.showList(list);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getHistoryItems(final ListPresenterCallback callback) {
        db.historyItemDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HistoryItem>>() {
                    @Override
                    public void accept(List<HistoryItem> list) throws Exception {
                        callback.showList(list);
                    }
                });
    }

    @Override
    public void markAsPurchased(final ListPresenterCallback callback, final ShoppingItem item) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.shoppingItemsDao().delete(item);
                db.historyItemDao().insert(DAOUtil.createHistoryItem(item));
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
                db.historyItemDao().insert(DAOUtil.createHistoryList(item));
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
