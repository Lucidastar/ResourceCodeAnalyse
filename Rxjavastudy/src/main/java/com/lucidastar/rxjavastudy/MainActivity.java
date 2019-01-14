package com.lucidastar.rxjavastudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        method1();
        method10();
    }

    //简单实用
    private void method1() {

        //被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();//会调用完成
            }

        }) ;

        //观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: ");

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG, "onNext: "+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: "+"完成！");
            }
        };

        observable.subscribe(observer);
    }

    //map操作符的使用
    private void method2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "this is a result"+integer;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
    }

    //flatMap操作符的使用
    private void method3(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        List<String> list = new ArrayList();
                        list.add("this is integer"+integer);
                        for (int i = 0; i < 3; i++) {
//                            list.add("this is integer"+integer);
                        }
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
    }

    //zip操作符的使用
    private void method4(){
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
                e.onNext(6);
                e.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("今天");
                e.onNext("明天");
                e.onNext("昨天");
                e.onNext("后天");
                e.onNext("大后天");
                e.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return new StringBuilder().append(integer).append(s).toString();
            }
        }).subscribeOn(Schedulers.newThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
    }

    private void method5(){
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                    Thread.sleep(2000);
                }

            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("今天");
                e.onNext("明天");
                e.onNext("昨天");
                e.onNext("后天");
                e.onNext("大后天");
                e.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return new StringBuilder().append(integer).append(s).toString();
            }
        }).subscribeOn(Schedulers.newThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.w(TAG, throwable);
            }
        });
    }

    private void method6(){
        Observable<String> observable = Observable.fromArray(new String[]{"你好","我好"});
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
    }
    private void method7(){
        Observable<String> observable = Observable.fromArray(new String[]{"你好","我好"});
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        }) ;

        Scheduler.Worker worker = Schedulers.io().createWorker();
        worker.schedule(new Runnable() {
            @Override
            public void run() {
                futureTask.run();
            }
        });

        Observable.fromFuture(futureTask).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
    }

    //Just
    private void method8(){
        Observable.just("hello","world").subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });

    }

    private void method9(){
        Observable.just("hello").timer(1, TimeUnit.SECONDS).timeInterval(TimeUnit.SECONDS, new Scheduler() {
            @Override
            public Worker createWorker() {
                return Schedulers.io().createWorker();
            }
        }).subscribe(new Consumer<Timed<Long>>() {
            @Override
            public void accept(@NonNull Timed<Long> longTimed) throws Exception {
                Log.i(TAG, "accept: "+longTimed.value());
            }
        });
    }
    private void method10(){
        Flowable<String> stringFlowable = Flowable.fromArray("你好","我好","他好");

        stringFlowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
    }
}
