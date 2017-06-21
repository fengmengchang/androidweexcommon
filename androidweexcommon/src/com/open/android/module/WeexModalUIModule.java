/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午3:33:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.android.module;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;
import com.taobao.weex.utils.WXLogUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午3:33:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class WeexModalUIModule extends WXModule {
	public static final String OK = "OK";
	public static final String CANCEL = "Cancel";
	public static final String RESULT = "result";
	public static final String DATA = "data";
	public static final String MESSAGE = "message";
	public static final String DURATION = "duration";
	public static final String OK_TITLE = "okTitle";
	public static final String CANCEL_TITLE = "cancelTitle";
	public static final String DEFAULT = "default";

	private Toast toast;

	/**
	 * 使用： this.$call('modal', 'toast', { 'message': content, 'duration': 2.0
	 * }); Arguments： message、duration； example： var modal =
	 * require('@weex-module/modal'); modal.toast({'message': 'I am toast!',
	 * 'duration': 1});
	 */
	@WXModuleAnno
	public void toast(String param) {
		String message = "";
		int duration = Toast.LENGTH_SHORT;
		if (!TextUtils.isEmpty(param)) {
			try {
				param = URLDecoder.decode(param, "utf-8");
				JSONObject jsObj = new JSONObject(param);
				message = jsObj.optString(MESSAGE);
				duration = jsObj.optInt(DURATION);
			} catch (Exception e) {
				WXLogUtils.e("[WXModalUIModule] alert param parse error " + WXLogUtils.getStackTrace(e));
			}
		}
		if (TextUtils.isEmpty(message)) {
			WXLogUtils.e("[WXModalUIModule] toast param parse is null ");
			return;
		}

		if (duration > 3) {
			duration = Toast.LENGTH_LONG;
		} else {
			duration = Toast.LENGTH_SHORT;
		}
		if (toast == null) {
			toast = Toast.makeText(mWXSDKInstance.getContext(), message, duration);
		} else {
			toast.setDuration(duration);
			toast.setText(message);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * Arguments : message(string): 显示内容. okTitle(string): 确认按钮.
	 * callback(function): 弹出回调.
	 * 
	 * Example :
	 * 
	 * var arg1 = 'I am alert!'; var arg2 = 'I am ok'; var modal =
	 * require('@weex-module/modal'); modal.alert({ message: arg1, okTitle: arg2
	 * }, function(e) { // TODO after the alert is complete. })
	 */
	@WXModuleAnno
	public void alert(String param, final String callbackId) {
		if (mWXSDKInstance.getContext() instanceof Activity) {
			String message = "";
			String okTitle = OK;
			if (!TextUtils.isEmpty(param)) {
				try {
					param = URLDecoder.decode(param, "utf-8");
					JSONObject jsObj = new JSONObject(param);
					message = jsObj.optString(MESSAGE);
					okTitle = jsObj.optString(OK_TITLE);
				} catch (Exception e) {
					WXLogUtils.e("[WXModalUIModule] alert param parse error " + WXLogUtils.getStackTrace(e));
				}
			}
			if (TextUtils.isEmpty(message)) {
				WXLogUtils.e("[WXModalUIModule] alert param parse is null ");
				return;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(mWXSDKInstance.getContext());
			builder.setMessage(message);

			final String okTitle_f = TextUtils.isEmpty(okTitle) ? OK : okTitle;
			builder.setPositiveButton(okTitle_f, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, okTitle_f);
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
		} else {
			WXLogUtils.e("[WXModalUIModule] when call alert mWXSDKInstance.getContext() must instanceof Activity");
		}
	}

	/**
	 * confirm Arguments
	 * 
	 * message(string): the message that the confirm shows. okTitle(string): the
	 * title of confirm button. cancelTitle(string): the title of cancel button.
	 * callback(function): callback when complete. Example
	 * 
	 * var arg1 = 'I am alert!' var arg2 = 'I am ok' var arg3 = 'I am cancel'
	 * var modal = require('@weex-module/modal'); modal.confirm({ message: arg1,
	 * okTitle: arg2, cancelTitle: arg3 }, function(e) { nativeLog(e.status) //
	 * TODO after the confirm is complete. });
	 */
	@WXModuleAnno
	public void confirm(String param, final String callbackId) {
		if (mWXSDKInstance.getContext() instanceof Activity) {
			String message = "";
			String okTitle = OK;
			String cancelTitle = CANCEL;
			if (!TextUtils.isEmpty(param)) {
				try {
					param = URLDecoder.decode(param, "utf-8");
					JSONObject jsObj = new JSONObject(param);
					message = jsObj.optString(MESSAGE);
					okTitle = jsObj.optString(OK_TITLE);
					cancelTitle = jsObj.optString(CANCEL_TITLE);
				} catch (Exception e) {
					WXLogUtils.e("[WXModalUIModule] confirm param parse error " + WXLogUtils.getStackTrace(e));
				}
			}
			if (TextUtils.isEmpty(message)) {
				WXLogUtils.e("[WXModalUIModule] confirm param parse is null ");
				return;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(mWXSDKInstance.getContext());
			builder.setMessage(message);

			final String okTitle_f = TextUtils.isEmpty(okTitle) ? OK : okTitle;
			final String cancelTitle_f = TextUtils.isEmpty(cancelTitle) ? CANCEL : cancelTitle;

			builder.setPositiveButton(okTitle_f, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, okTitle_f);
				}
			});
			builder.setNegativeButton(cancelTitle_f, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, cancelTitle_f);
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
		} else {
			WXLogUtils.e("[WXModalUIModule] when call confirm mWXSDKInstance.getContext() must instanceof Activity");
		}
	}

	/**
	 * Arguments
	 * 
	 * message(string): the message that the prompt shows. okTitle(string): the
	 * title of confirm button. cancelTitle(string): the title of cancel button.
	 * callback(function): callback when complete.
	 * 
	 * //返回参数 状态（字符串）：单击用户单击的按钮的标题。 // 数据（字符串）：由用户输入的文本的值。
	 * 
	 * Example
	 * 
	 * var arg1 = 'I am prompt!' var arg2 = 'I am ok' var arg3 = 'I am cancel'
	 * var modal = require('@weex-module/modal'); modal.prompt({ message: arg1,
	 * okTitle: arg2, cancelTitle: arg3 }, function(e) { nativeLog(e.status + ',
	 * ' + e.data); // TODO after the prompt is complete. });
	 */
	@WXModuleAnno
	public void prompt(String param, final String callbackId) {
		if (mWXSDKInstance.getContext() instanceof Activity) {
			String message = "";
			String defaultValue = "";
			String okTitle = OK;
			String cancelTitle = CANCEL;

			if (!TextUtils.isEmpty(param)) {
				try {
					param = URLDecoder.decode(param, "utf-8");
					JSONObject jsObj = new JSONObject(param);
					message = jsObj.optString("message");
					okTitle = jsObj.optString("okTitle");
					cancelTitle = jsObj.optString("cancelTitle");
					defaultValue = jsObj.optString("default");
				} catch (Exception e) {
					WXLogUtils.e("[WXModalUIModule] confirm param parse error " + WXLogUtils.getStackTrace(e));
				}
			}

			if (TextUtils.isEmpty(message)) {
				WXLogUtils.e("[WXModalUIModule] confirm param parse is null ");
				return;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(mWXSDKInstance.getContext());
			builder.setMessage(message);

			final EditText editText = new EditText(mWXSDKInstance.getContext());
			editText.setText(defaultValue);
			builder.setView(editText);
			final String okTitle_f = TextUtils.isEmpty(okTitle) ? OK : okTitle;
			final String cancelTitle_f = TextUtils.isEmpty(cancelTitle) ? CANCEL : cancelTitle;
			builder.setPositiveButton(okTitle_f, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Map<String, Object> result = new HashMap<String, Object>();
					result.put(RESULT, okTitle_f);
					result.put(DATA, editText.getText().toString());
					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, result);

				}
			});
			builder.setNegativeButton(cancelTitle_f, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, cancelTitle_f);
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
		} else {
			WXLogUtils.e("when call prompt mWXSDKInstance.getContext() must instanceof Activity");
		}
	}
}
