package com.lucidastar.rxjavastudy;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Emitter;
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
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.operators.completable.CompletableDelay;
import io.reactivex.internal.operators.completable.CompletableTimer;
import io.reactivex.internal.operators.observable.ObservableZip;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        method1();
//        method10();
        method14();
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

        });

        //观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: ");

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: " + "完成！");
            }
        };

        observable.subscribe(observer);
    }

    //map操作符的使用
    private void method2() {
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
                        return "this is a result" + integer;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        });
    }

    //flatMap操作符的使用
    private void method3() {
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
                        list.add("this is integer" + integer);
                        for (int i = 0; i < 3; i++) {
//                            list.add("this is integer"+integer);
                        }
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        });
    }

    //zip操作符的使用
    private void method4() {
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
                Log.i(TAG, "accept: " + s);
            }
        });
    }

    private void method5() {
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

    private void method6() {
        Observable<String> observable = Observable.fromArray(new String[]{"你好", "我好"});
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        });
    }

    private void method7() {
        Observable<String> observable = Observable.fromArray(new String[]{"你好", "我好"});
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

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
                Log.i(TAG, "accept: " + s);
            }
        });
    }

    //Just
    private void method8() {
        Observable.just("hello", "world").subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        });

    }

    private void method9() {
        Observable.just("hello").timer(1, TimeUnit.SECONDS).timeInterval(TimeUnit.SECONDS, new Scheduler() {
            @Override
            public Worker createWorker() {
                return Schedulers.io().createWorker();
            }
        }).subscribe(new Consumer<Timed<Long>>() {
            @Override
            public void accept(@NonNull Timed<Long> longTimed) throws Exception {
                Log.i(TAG, "accept: " + longTimed.value());
            }
        });
    }

    private void method10() {
        Flowable<String> stringFlowable = Flowable.fromArray("你好", "我好", "他好");

        stringFlowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: " + s);
            }
        });
    }

    private void method11() {
        Observable.fromArray("你好", "jhon", "other")
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return "first" + s;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "disposable0: 当前线程" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.i(TAG, "apply: 当前线程" + Thread.currentThread().getName());
                        return "是的" + s;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "disposable1: 当前线程" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "disposable2: 当前线程" + Thread.currentThread().getName());
                    }
                })
                .mergeWith(new CompletableTimer(4000, TimeUnit.MILLISECONDS, Schedulers.io()))
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        Log.i(TAG, "ObservableSource: 当前线程" + Thread.currentThread().getName());
                        return Observable.just("你好" + s);
                    }
                }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return true;
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.i(TAG, "disposable3: 当前线程" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext: result" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 当前线程" + Thread.currentThread().getName());
                    }
                });
    }

    private void method12() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        }).subscribeOn(getNamedScheduler("创建后的scheduler"))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        threadInfo("doOnSubscribe-1");
                    }
                }).subscribeOn(getNamedScheduler("第二个测试测试"))
                .subscribeOn(getNamedScheduler("测试测试测试"))
                .subscribeOn(getNamedScheduler("doOnSubscribe-1后的subscribeOn"))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        threadInfo("doOnSubscribe-2");
                    }
                })
                .subscribeOn(getNamedScheduler("doOnSubscribe-2后的subscribeOn"))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        threadInfo("doOnSubscribe-3");
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                threadInfo("doOnSubscribe-4");
            }

            @Override
            public void onNext(String s) {
                threadInfo("onNext");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        //创建自己的Schedulers，是为了验证subscribeOn与observerOn的区别

        /*doOnSubscribe-3 => main
        doOnSubscribe-4 => main
        doOnSubscribe-2 => doOnSubscribe-2后的subscribeOn
        doOnSubscribe-1 => 第二个测试测试
        onNext => 创建后的scheduler
        onNext => 创建后的scheduler
        onNext => 创建后的scheduler
        */
        //doOnSubscribe会得到下方第一个subscribeOn的值
        //subscribeOn(事件的产生线程的切换)   observerOn（事件的消费线程的切换）
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    public static Scheduler getNamedScheduler(final String name) {
        return Schedulers.from(Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(@android.support.annotation.NonNull Runnable r) {
                return new Thread(r, name);
            }
        }));
    }

    public static void threadInfo(String caller) {
        Log.i(TAG, caller + " => " + Thread.currentThread().getName());
    }

    private void method13() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        Log.d("wyz", "create:" + Thread.currentThread().getName());
                        e.onNext(1);
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Log.d("wyz", "map1:" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("wyz", "doOnSubscribe1:" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("wyz", "doOnSubscribe2:" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Log.d("wyz", "doOnSubscribe2:" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        Log.d("wyz", "flatMap:" + Thread.currentThread().getName());
                        return Observable.fromArray(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d("wyz", "执行完毕:" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void method14() {
        //https://blog.csdn.net/wenyingzhi/article/details/80453464  如何使用subscribeOn ObserveOn及他们之间了解
        Disposable disposable = Observable.just("hello", "world")
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
//                      Log.i(TAG, "accept: "+s);
                    }
                });

//        disposable.dispose();


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello");
                emitter.onNext("world");
                emitter.onNext("nihao");
                emitter.onNext("douhao");
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "doOnSubscribe1=" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "doOnSubscribe2=" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "doOnSubscribe3=" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "doOnNext=" + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
//                        Log.i(TAG, "accept: " + s);
//                        Log.i(TAG, "onNext: " + (Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
