/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-1下午3:24:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.android.weex;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-1下午3:24:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class WeexNavigatorActivity  extends  FragmentActivity implements IWXRenderListener {

	public WXSDKInstance mWXSDKInstance;
	private Map<String, Object> options = new HashMap<String, Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        /**
         * Intent { act=android.intent.action.VIEW 
         * cat=[com.taobao.android.intent.category.WEEX] 
         * dat=http://192.168.1.15:8080/... (has extras) }
         */
        String action = getIntent().getAction();
        if(Intent.ACTION_VIEW.equals(action)){
        	String data = getIntent().getDataString();
        	String weoptions = null;
        	if (getIntent().getExtras() != null) {
				weoptions = getIntent().getExtras().getString("options");
				try {
					if (weoptions != null && weoptions.length() > 0) {
						Log.d("weoptions", weoptions);
						JSONObject jsonObject;
						jsonObject = new JSONObject(weoptions);
						if (jsonObject != null) {
							Iterator<String> iterator = jsonObject.keys();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								String value = jsonObject.getString(key);
								options.put(key, value);
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
            mWXSDKInstance.renderByUrl("MyApplication",data,options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
        }
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
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
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
}