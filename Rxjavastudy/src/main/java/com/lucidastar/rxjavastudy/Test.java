package com.lucidastar.rxjavastudy;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class Test {
    public static void main(String[] args) {
        methodCombineLatest();
    }

    //combineLatest 操作符的使用
    @SuppressLint("CheckResult")
    private static void methodCombineLatest() {
        Observable.combineLatest(Observable.just("hello"), Observable.just("world","two_1","two_2","two_3","two_4","two_5","two_6"), new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
