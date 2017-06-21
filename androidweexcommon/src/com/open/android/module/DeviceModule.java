/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-16下午4:04:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.android.module;

import android.support.annotation.Nullable;

import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-16下午4:04:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DeviceModule extends WXModule {
	  @WXModuleAnno
	  public void getOSVersion(@Nullable JSCallback callback) {
	    String osversion = android.os.Build.VERSION.RELEASE;
	    if(callback != null) {
	      callback.invoke(osversion);
	    }
	  }

	  @WXModuleAnno
	  public void getSDKVersion(@Nullable JSCallback callback) {
	    @SuppressWarnings("deprecation")
	    String sdkversion = android.os.Build.VERSION.SDK;
	    if(callback != null) {
	      callback.invoke(sdkversion);
	    }
	  }
	}

	//WXSDKEngine.registerModule("deviceApi", DeviceModule.class);
