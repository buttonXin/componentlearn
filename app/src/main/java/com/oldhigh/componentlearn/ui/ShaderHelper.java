package com.oldhigh.componentlearn.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import com.oldhigh.libres.util.L;

import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by oldhigh on 2018/3/28.
 */

public class ShaderHelper {


    private BitmapListener mListener;

    public interface BitmapListener{
        void onBitmap(Bitmap bitmap);

        void onError(Exception e);
    }
    private Handler mHandler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg) {

            if (msg.arg1 == 0){
                Exception  exception = (Exception) msg.obj;
                mListener.onError(exception);
            }else {

                mListener.onBitmap((Bitmap) msg.obj);
            }

        }
    };

    public void startHander(String url , BitmapListener listener){
        mListener = listener;
        final Message message = mHandler.obtainMessage();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e1) {
                e1.printStackTrace();
                message.obj = e1;
                message.arg1 = 0 ;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                L.d("回调"  , Thread.currentThread().getName());
                InputStream inputStream = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                message.obj = bitmap ;
                message.arg1 = 1 ;
                mHandler.sendMessage(message);

            }
        });
    }

    public void start(final ImageView shaderView) {


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("https://ws1.sinaimg.cn/large/610dc034ly1fp9qm6nv50j20u00miacg.jpg");
                e.onComplete();
            }
        })
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        L.d("这是子线程");
                        return downloadBitmap(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {

                        L.d("这就是主线程了 ", bitmap);
                        shaderView.setImageBitmap(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
    //这里面用的是同步的方法 ， 记得上面是子线程
    private Bitmap downloadBitmap(String s) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(s).build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = response.body().byteStream();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }


    public Observable<Bitmap> start(final String url){
       return Observable.just(url)
               .flatMap(new Function<String, ObservableSource<Bitmap>>() {
                   @Override
                   public ObservableSource<Bitmap> apply(String s) throws Exception {
                       return download(s);
                   }
               })
               .observeOn(AndroidSchedulers.mainThread());

    }

    private ObservableSource<Bitmap> download(final String s) {
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(final ObservableEmitter<Bitmap> e) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(s).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e1) {
                        e.onError(e1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        e.onNext(bitmap);
                        e.onComplete();
                        L.d(" 222222" );
                    }
                });
            }
        });
    }


    public Observable<Bitmap> wwww(final String url){
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(final ObservableEmitter<Bitmap> e) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e1) {
                        e.onError(e1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        e.onNext(bitmap);
                        e.onComplete();
                        L.d(" 222222" );
                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
