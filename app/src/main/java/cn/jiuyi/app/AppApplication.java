package cn.jiuyi.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.nostra13.universalimageloader.BuildConfig;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

public class AppApplication extends Application {
    public static final String UPDATE_DATA = "update_data";//更新数据
    //	public LocationService locationService;
    public static Context context;
    /*umeng*/
    private Handler handler;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = getApplicationContext();
        initImageLoader(context);
    }

    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
        //整体摧毁的时候调用这个方法
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.diskCache(new UnlimitedDiskCache(new File(Environment.getExternalStorageDirectory() + "/lawClient")));

        if (BuildConfig.DEBUG) {
            config.writeDebugLogs(); // 发布release包时，移除log信息
        }
        //  用configuration初始化ImageLoader
        ImageLoader.getInstance().init(config.build());
    }


}
