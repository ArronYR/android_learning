package com.helloarron.arron.imooc_imageloader;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.helloarron.arron.imooc_imageloader.bean.FolderBean;
import com.helloarron.arron.imooc_imageloader.util.ImageLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private GridView mGridVIew;
    private List<String> mImgs;
    private ImageAdapter mImgAdapter;

    private RelativeLayout mBottomLy;
    private TextView mDirName;
    private TextView mDirCount;

    private File mCurrentDir;
    private int mMaxCount;

    private ProgressDialog mProgressDialog;

    private static final int DATA_LOADED = 0x110;

    private List<FolderBean> mFolderBeans = new ArrayList<FolderBean>();

    private ListImageDirPopupWindow mDirPopupWindow;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == DATA_LOADED){
                mProgressDialog.dismiss();
                // 绑定数据到view中
                data2View();
                // 初始化popupWindow
                initPopupWindow();
            }
        }
    };

    private void initPopupWindow() {
        mDirPopupWindow = new ListImageDirPopupWindow(this, mFolderBeans);

        mDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });

        mDirPopupWindow.setOnDirSelectedListener(new ListImageDirPopupWindow.OnDirSelectedListener() {
            @Override
            public void onSelected(FolderBean folderBean) {
                mCurrentDir = new File(folderBean.getDir());
                mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")){
                            return true;
                        }
                        return false;
                    }
                }));
                mImgAdapter = new ImageAdapter(MainActivity.this, mImgs, mCurrentDir.getAbsolutePath());
                mGridVIew.setAdapter(mImgAdapter);

                mDirCount.setText(mMaxCount+"");
                mDirName.setText(mCurrentDir.getName());
                mDirPopupWindow.dismiss();
            }
        });
    }

    /**
     * 内容区域变亮
     */
    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    /**
     * 内容区域变黑
     */
    private void lightOff(){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);
    }

    /**
     * 绑定数据到View
     */
    private void data2View() {
        if (mCurrentDir == null){
            Toast.makeText(this, "未扫描到任何图片", Toast.LENGTH_LONG).show();
            return;
        }
        mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")){
                    return true;
                }
                return false;
            }
        }));
        mImgAdapter = new ImageAdapter(this, mImgs, mCurrentDir.getAbsolutePath());
        mGridVIew.setAdapter(mImgAdapter);

        mDirCount.setText(mMaxCount+"");
        mDirName.setText(mCurrentDir.getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        initEvent();
    }

    private void initEvent(){
        mBottomLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDirPopupWindow.setAnimationStyle(R.style.dir_popupwindow_anim);
                mDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);
                lightOff();
            }
        });
    }

    /**
     * 利用ContentProvider扫描手机中的所有图片
     */
    private void initDatas(){
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "当前存储卡不可用", Toast.LENGTH_LONG).show();
            return;
        }
        mProgressDialog = ProgressDialog.show(this, null, "正在扫描中...");
        new Thread(){
            @Override
            public void run() {
                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = MainActivity.this.getContentResolver();
                Cursor cursor = cr.query(mImgUri, null, MediaStore.Images.Media.MIME_TYPE + " = ? or " + MediaStore.Images.Media.MIME_TYPE + " = ?", new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                // 零时存储文件的父目录路径
                Set<String> mDirPaths = new HashSet<String>();
                while (cursor.moveToNext()){
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null){
                        continue;
                    }
                    String dirPath = parentFile.getAbsolutePath();
                    FolderBean folderBean = null;
                    // 防止重复扫描
                    if (mDirPaths.contains(dirPath)){
                        continue;
                    }else{
                        mDirPaths.add(dirPath);
                        folderBean = new FolderBean();
                        folderBean.setDir(dirPath);
                        folderBean.setFirstImgPath(path);
                    }
                    if (parentFile.list() == null){
                        continue;
                    }
                    int picCount = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")){
                                return true;
                            }
                            return false;
                        }
                    }).length;
                    folderBean.setCount(picCount);
                    mFolderBeans.add(folderBean);

                    if (picCount > mMaxCount){
                        mMaxCount = picCount;
                        mCurrentDir = parentFile;
                    }
                }
                cursor.close();
                // 通知handler扫描图片完成
                mHandler.sendEmptyMessage(DATA_LOADED);
            }
        }.start();
    }

    private void initView() {
        mGridVIew = (GridView) findViewById(R.id.id_gridView);
        mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        mDirName = (TextView) findViewById(R.id.id_dir_name);
        mDirCount = (TextView) findViewById(R.id.id_dir_count);
    }

    @Override
    protected void onDestroy() {
        try{
            mProgressDialog.dismiss();
        }catch (Exception e) {
            System.out.println("myDialog取消，失败！");
        }
        super.onDestroy();
    }
}
