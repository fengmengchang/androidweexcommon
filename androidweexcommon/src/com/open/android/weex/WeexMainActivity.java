/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-1下午3:08:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.android.weex;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-1下午3:08:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

public class WeexMainActivity extends  FragmentActivity implements IWXRenderListener {
	/**weex**/
	/**网络请求方式http or https*/
	public static final String HTTP = "https";//https http
	/**ip*/
	public static final String IP = "raw.githubusercontent.com/fengmnegchang/yokaweex/master";//192.168.1.9:8080 192.168.1.15:8080 
	//raw.githubusercontent.com/fengmnegchang/yokaweex/master
	/**桥接主入口*/
	public static final String MAIN_JS = "/yoka/build/src/mainlist.js";//"dist/app.weex.js";
	public static final String MAIN_WEB = "/index.html?page=./yoka/build/src/mainlist.js"; 
    WXSDKInstance mWXSDKInstance;
	Map<String, Object> options = new HashMap<String, Object>();
	int themetype=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         * width 为-1 默认全屏，可以自己定制。
         * height =-1 默认全屏，可以自己定制。
         */
        options.put("skinType", themetype);
        mWXSDKInstance.renderByUrl("MyApplication",HTTP+"://"+IP+MAIN_JS,options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
      
//        mWXSDKInstance.renderByUrl("MyApplication","http://192.168.1.15:8080/dist/weexbar/tabbar.js",null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
//        mWXSDKInstance.render("MyApplication", WXFileUtils.loadAsset("index.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    
    
        /**
		 * 
		 * WXSample 可以替换成自定义的字符串，针对埋点有效。 template 是.we transform 后的 js文件。 option
		 * 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。 jsonInitData 可以为空。 width
		 * 为-1 默认全屏，可以自己定制。 height =-1 默认全屏，可以自己定制。 main.we / main.vue
		 * 文件，也就是上面代码中的main.js文件中的this.$getConfig()来获取传进来的参数 module.exports = {
		 * data: { aa: '', bb: '', bundleUrl: '' }, methods: { // 获取 native的传参
		 * getOptions: function() { this.aa = this.$getConfig().aa; this.bb =
		 * this.$getConfig().bb; this.bundleUrl = this.$getConfig().bundleUrl; }
		 * } }
		 */
		// Map<String, Object> options = new HashMap<>();
		// options.put(WXSDKInstance.BUNDLE_URL, url); // 传递bundleUrl
		// options.put("aa", "aaa"); // 传递自定义参数 aa
		// options.put("bb", "ccc"); // 传递自定义参数 bb
		// mWXSDKInstance.render("MyApplication",
		// WXFileUtils.loadAsset("main.js", this), options, null, -1, -1,
		// WXRenderStrategy.APPEND_ASYNC);

		// mWXSDKInstance.renderByUrl("MyApplication","http://192.168.1.15:8080/dist/weexbar/tabbar.js",null,
		// null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
		// mWXSDKInstance.render("MyApplication",
		// WXFileUtils.loadAsset("index.js", this), null, null, -1, -1,
		// WXRenderStrategy.APPEND_ASYNC);
    }

    @SuppressLint({ "ResourceAsColor", "NewApi" }) @Override
	public void onViewCreated(WXSDKInstance instance, View view) {
		try {
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				Window window = getWindow();
				// 取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
				window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				// 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				// 设置状态栏颜色
				window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
				ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
				View mChildView = mContentView.getChildAt(0);
				if (mChildView != null) {
					// 注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView
					// 的第一个子 View . 预留出系统 View 的空间.
					ViewCompat.setFitsSystemWindows(mChildView, true);
				}
			}

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
				View statusBarView = new View(this);
				ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this));
				statusBarView.setBackgroundColor(getResources().getColor(R.color.status_bar_color));
				contentView.addView(statusBarView, lp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		LayoutParams lp = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			int statusBarHeight = getStatusBarHeight(this);
			lp.topMargin = statusBarHeight;
		}
		addContentView(view, lp);
	}
    /**
	 * 得到状态栏高度
	 * 
	 * @return
	 */
	public static int getStatusBarHeight(Activity act) {
		/*
		 * 方法一，荣耀3c无效 Rect frame = new Rect(); act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame); int statusBarHeight = frame.top; return statusBarHeight;
		 */

		/*
		 * 方法二，荣耀3c无效 Rect rectgle= new Rect(); Window window= act.getWindow(); window.getDecorView().getWindowVisibleDisplayFrame(rectgle); int StatusBarHeight= rectgle.top; int contentViewTop=
		 * window.findViewById(Window.ID_ANDROID_CONTENT).getTop(); int statusBar = contentViewTop - StatusBarHeight; return statusBar;
		 */
		// 方法三，荣耀3c有效
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = act.getResources().getDimensionPixelSize(x);
			return sbar;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;
	}
    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityResume();
			Log.d("MainActivity", "onResume");
			options.put("themetype", themetype);
			//callJS >>>> instanceId:1function:callJS tasks:[{"data":"1","type":2},{"data":"[{\"args\":[\"1\",{\"bundleUrl\":\"http://192.168.1.15:12580/dist/mainlist.js\",\"themetype\":1},true],\"method\":\"callback\"}]","type":3}]
			mWXSDKInstance.fireGlobalEventCallback("mainlist_text_day_night", options);
			
			//callJS >>>> instanceId:1function:callJS tasks:[{"data":"1","type":2},{"data":"[{\"args\":[\"mainlist_text_day_night_ref\",\"mainlist_text_day_night\",{\"bundleUrl\":\"http://192.168.1.15:12580/dist/mainlist.js\",\"themetype\":1},null],\"method\":\"fireEvent\"}]","type":3}]
//			c(mWXSDKInstance.getInstanceId(), "_root", "mainlist_text_day_night",options);
		}
    }

    @Override
    protected void onPause() {
        super.onPause();
        themetype = 1;
		super.onPause();
		if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityPause();
			Log.d("MainActivity", "onPause");
		}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}