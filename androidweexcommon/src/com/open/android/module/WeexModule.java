/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8上午10:24:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.android.module;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8上午10:24:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

public class WeexModule extends WXModule {
	private static final String TAG = WeexModule.class.getSimpleName();
	private static final String WEEX_CATEGORY = "com.taobao.android.intent.category.WEEX";

	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
	public void openUrl(String url, String callback){
		Log.d(TAG, "openUrl ========" + url);
		boolean error = false;
		if (TextUtils.isEmpty(url)) {
			return;
		}
		String scheme = Uri.parse(url).getScheme();
		StringBuilder builder = new StringBuilder();
		if (TextUtils.equals("http", scheme) || TextUtils.equals("https", scheme) || TextUtils.equals("file", scheme)) {
			builder.append(url);
		} else {
			builder.append("http:");
			builder.append(url);
		}
		try {
			Uri uri = Uri.parse(builder.toString());
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addCategory(WEEX_CATEGORY);
			mWXSDKInstance.getContext().startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			 error = true;
		}
		
		Map<String, Object> result = new HashMap<String, Object>(1);
        result.put("error", error);
        WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, result);
	}
}