package com.helloarron.arron.imooc_imageloader.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 图片加载类
 * Created by arron on 16/4/8.
 */
public class ImageLoader {
    private static ImageLoader mInstance;

    /**
     * 图片缓存的核心对象
     */
    private LruCache<String, Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;
    private static final int DEFAUL_THREAD_COUNT = 1;

    /**
     * 队列调度方式
     */
    private Type mType = Type.LIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskQueue;

    /**
     * 后台轮询线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandle;

    /**
     * 信号量
     */
    private Semaphore mSemaphorePoolThreadHandle = new Semaphore(0);
    private Semaphore mSemaphoreThreadPool;

    /**
     * UI线程中的handle
     */
    private Handler mUIHandle;

    public enum Type{
        FIFO, LIFO
    }

    /**
     * 构造方法
     */
    private ImageLoader(int threadCount, Type type){
        init(threadCount, type);
    }

    /**
     * 初始化操作
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {
        // 后台轮询线程
        mPoolThread = new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandle = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        // 去线程池取出一个任务执行
                        mThreadPool.execute(getTask());
                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 释放一个信号量
                mSemaphorePoolThreadHandle.release();
                Looper.loop();
            }
        };
        mPoolThread.start();

        // 获取应用可用最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMamory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMamory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        // 创建线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<Runnable>();
        mType = type;

        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    /**
     * 从任务队列取出一个方法
     * @return
     */
    private Runnable getTask() {
        if (mType == Type.FIFO){
            return mTaskQueue.removeFirst();
        }else if (mType == Type.LIFO){
            return mTaskQueue.removeLast();
        }
        return null;
    }

    public static ImageLoader getInstance(int threadCount, Type type){
        if (mInstance == null){
            synchronized (ImageLoader.class){
                if (mInstance == null){
                    mInstance = new ImageLoader(threadCount, type);
                }
            }
        }
        return mInstance;
    }

    /**
     * 根据path为imageView设置图片
     * @param path
     * @param imageView
     */
    public void loadImage(final String path, final ImageView imageView){
        imageView.setTag(path);
        if (mUIHandle == null){
            mUIHandle = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    // 获取得到的图片，为iimageView回调设置图片
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageView = holder.imageView;
                    String path = holder.path;
                    // 将path与Tag进行比较
                    if (imageView.getTag().toString().equals(path)){
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        // 根据path在缓存中获取bitmap
        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null){
            refreshBitmap(bm, imageView, path);
        }else{
            addTask(new Runnable(){
                @Override
                public void run() {
                    // 获取图片,获取图片大小
                    ImageSize imageSize = getImageViewSize(imageView);
                    // 压缩图片
                    Bitmap bm = decodeSimpledBitmapFromPath(path, imageSize.width, imageSize.height);
                    // 把图片加入到缓存
                    addBitmapToLruCache(path, bm);
                    refreshBitmap(bm, imageView, path);
                    mSemaphoreThreadPool.release();
                }
            });
        }
    }

    /**
     * 更新UI
     * @param bm
     * @param imageView
     * @param path
     */
    private void refreshBitmap(Bitmap bm, ImageView imageView, String path) {
        Message message = Message.obtain();
        ImgBeanHolder holder = new ImgBeanHolder();
        holder.bitmap = bm;
        holder.imageView = imageView;
        holder.path = path;
        message.obj = holder;
        mUIHandle.sendMessage(message);
    }

    /**
     * 将图片加入到LruCache
     * @param path
     * @param bm
     */
    private void addBitmapToLruCache(String path, Bitmap bm) {
        if (getBitmapFromLruCache(path) == null){
            if (bm != null){
                mLruCache.put(path, bm);
            }
        }
    }

    /**
     * 根据图片需要显示的宽和高对图片进行压缩
     * @param path
     * @param width
     * @param height
     * @return
     */
    private Bitmap decodeSimpledBitmapFromPath(String path, int width, int height) {
        // 获取图片的宽和高，并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        // 使用获取到的imSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight){
            int widthRadio = Math.round(width*1.0f / reqWidth);
            int heightRadio = Math.round(height*1.0f / reqHeight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }

        return inSampleSize;
    }

    /**
     * 根据ImageView获取压缩适当的宽和高
     * @param imageView
     * @return
     */
    private ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        // 获取imageView的实际宽度
        int width = imageView.getWidth();
        if (width <= 0){
            width = lp.width;   // 获取imageView在layout中声明的宽度
        }
        if (width <= 0){
            // width = imageView.getMaxWidth();    // 检查最大值
            width = getImageViewFieldValue(imageView, "mMaxWidth");
        }
        if (width <= 0){
            width = displayMetrics.widthPixels;
        }

        // 获取imageView的实际高度
        int height = imageView.getHeight();
        if (height <= 0){
            height = lp.height; // 获取imageView在layout中声明的高度
        }
        if (height <= 0){
            // height = imageView.getMaxHeight();  // 检查最大值
            height = getImageViewFieldValue(imageView, "mMaxHeight");
        }
        if (height <= 0){
            height = displayMetrics.heightPixels;
        }

        imageSize.width = width;
        imageSize.height = height;
        return imageSize;
    }

    /**
     * 通过反射获取imageView的某个属性值
     * @param onject
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object onject, String fieldName){
        int value = 0;

        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            int fieldValue = field.getInt(onject);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE){
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 添加一个方法到任务队列
     * @param runnable
     */
    private synchronized void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);
        try {
            if (mPoolThreadHandle == null){
                mSemaphorePoolThreadHandle.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPoolThreadHandle.sendEmptyMessage(0x110);
    }

    /**
     * 根据path在缓存中获取bitmap
     * @param path
     * @return
     */
    private Bitmap getBitmapFromLruCache(String path) {
        return mLruCache.get(path);
    }

    private class ImageSize{
        int width;
        int height;
    }

    private class ImgBeanHolder{
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }
}
