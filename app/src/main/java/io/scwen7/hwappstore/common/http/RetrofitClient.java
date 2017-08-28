package io.scwen7.hwappstore.common.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import io.scwen7.hwappstore.common.Contacts;
import io.scwen7.hwappstore.common.cookie.CookieJarImpl;
import io.scwen7.hwappstore.common.cookie.store.PersistentCookieStore;
import io.scwen7.hwappstore.common.fastjsonconverter.FastJsonConvertFactory;
import io.scwen7.hwappstore.common.utils.UIUtils;
import io.scwen7.hwappstore.data.ApiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by 解晓辉  on 2017/5/24 20:49 *
 * QQ  ：811733738
 * 作用:   提供 Retrofit 网络连接 ApiService
 */

public class RetrofitClient {


    private static RetrofitClient instance;

    private OkHttpClient mOkHttpClient;


    private Context mContext;

    private Retrofit mRetrofit;

    private ApiService mApiService;
    private CookieJarImpl cookieJar;

    private RetrofitClient(Context context) {
        this.mContext = context;
        cookieJar = new CookieJarImpl(new PersistentCookieStore(mContext));
    }

    public static RetrofitClient init(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    public static RetrofitClient getInstance() {
        return instance;
    }

    private OkHttpClient provideOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new HttpParamInterceptor(mContext))
                    .cookieJar(cookieJar)
                    .build();
        }


        return mOkHttpClient;
    }

    /**
     * 获取全局的cookie实例
     */
    public CookieJarImpl getCookieJar() {
        return cookieJar;
    }


    private Retrofit provideRetrofit() {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Contacts.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(FastJsonConvertFactory.create())
                    .client(provideOkHttpClient()).build();
        }

        return mRetrofit;
    }

    public ApiService provideApiService() {
        if (mApiService == null) {

            mApiService = provideRetrofit().create(ApiService.class);
        }
        return mApiService;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                return intIP2StringIP(wifiInfo.getIpAddress());
            }
        } else {
            //当前无网络连接,请在设置中打开网络
            UIUtils.showToast("当前无网络连接");
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}
