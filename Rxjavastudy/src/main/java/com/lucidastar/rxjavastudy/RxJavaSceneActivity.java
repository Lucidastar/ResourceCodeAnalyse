package com.lucidastar.rxjavastudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lucidastar.rxjavastudy.model.ClassModel;
import com.lucidastar.rxjavastudy.model.Student;
import com.mine.lucidastarutils.log.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hyli
 * @date 2020/5/14.
 * GitHub：https://github.com/Lucidastar
 * email： 1061150853@qq.com
 * description：
 */
public class RxJavaSceneActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_scene);
        findViewById(R.id.btn_one).setOnClickListener(this);
    }

    private void sceneOne(){

        /**
         * 1、获取班级的详情
         * 2、获取其中一个学生的id
         * 3、获取学生的详情
         */
        Observable.create(new ObservableOnSubscribe<ClassModel>() {
            @Override
            public void subscribe(ObservableEmitter<ClassModel> emitter) throws Exception {
                ClassModel classMode2 = new ClassModel("二年级","secondStudentId",30);
                emitter.onNext(classMode2);
                emitter.onComplete();
            }
        }).filter(new Predicate<ClassModel>() {
            @Override
            public boolean test(ClassModel classModel) throws Exception {
                return "secondStudentId".equals(classModel.getmStudentId());
            }
        }).flatMap(new Function<ClassModel, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final ClassModel classModel) throws Exception {

                return Observable.timer(1000, TimeUnit.MILLISECONDS).map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return classModel.getmClassName()+"class";
                    }
                });
            }
        }).flatMap(new Function<String, ObservableSource<Student>>() {
            @Override
            public ObservableSource<Student> apply(String s) throws Exception {

                return Observable.just(new Student("学生姓名和班级"+s));
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Student>() {
            @Override
            public void accept(Student student) throws Exception {
                KLog.d("mine",student.getName());
            }
        })

        ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                sceneOne();
                break;
        }
    }
}
