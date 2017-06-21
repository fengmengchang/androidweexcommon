///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-6-2上午9:42:43
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.android.module;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONException;
//
//import android.util.Log;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.android.volley.AuthFailureError;
//import com.android.volley.NetworkResponse;
//import com.android.volley.ParseError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.Response.Listener;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.Gson;
//import com.open.andenginetask.CallEarliest;
//import com.open.andenginetask.Callable;
//import com.open.andenginetask.Callback;
//import com.open.yoka.bean.MainListBean;
//import com.open.yoka.bean.m.MListBoxBean;
//import com.open.yoka.json.MainListJson;
//import com.open.yoka.json.m.MGridFootJson;
//import com.open.yoka.json.m.MListBoxJson;
//import com.open.yoka.json.m.MSwiperJson;
//import com.open.yoka.json.m.MTabJson;
//import com.open.yoka.jsoup.m.MMainGridFootService;
//import com.open.yoka.jsoup.m.MMainListBoxService;
//import com.open.yoka.jsoup.m.MSwiperService;
//import com.open.yoka.jsoup.m.MTabService;
//import com.open.yoka.utils.UrlUtils;
//import com.taobao.weex.bridge.WXBridgeManager;
//import com.taobao.weex.common.WXModuleAnno;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//
///**
// ***************************************************************************************************************************************************************************** 
// * 
// * @author :fengguangjing
// * @createTime:2017-6-2上午9:42:43
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// ***************************************************************************************************************************************************************************** 
// */
//// public class WeexJsoupModule extends WXModule {
//public class WeexJsoupModule extends WeexBaseJsoupModule {
//	public String TAG = WeexBaseJsoupModule.class.getSimpleName();
//
//	/***
//	 * 头部pager导航
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void focuspager(final String params, final String callback) {
//		Log.d(TAG, "focuspager ========" + params);
//		try {
//			doAsync(new CallEarliest<MSwiperJson>() {
//				@Override
//				public void onCallEarliest() throws Exception {
//				}
//			}, new Callable<MSwiperJson>() {
//				@Override
//				public MSwiperJson call() throws Exception {
//					MSwiperJson mIndexFocusJson = new MSwiperJson();
//					mIndexFocusJson.setList(MSwiperService.parsePCFocus(params));
//					return mIndexFocusJson;
//				}
//			}, new Callback<MSwiperJson>() {
//				@Override
//				public void onCallback(MSwiperJson result) {
//					Gson gson = new Gson();
//					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//				}
//			});
//			// 发起请求
//			// new Thread(new Runnable() {
//			// @Override
//			// public void run() {
//			// MSwiperJson mIndexFocusJson = new MSwiperJson();
//			// mIndexFocusJson.setList(MSwiperService.parsePCFocus(params));
//			// Gson gson = new Gson();
//			// WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(),
//			// callback, gson.toJson(mIndexFocusJson));
//			// }
//			// }).start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 底部tag标签
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void foottag(final String params, final String callback) {
//		Log.d(TAG, "foottag ========" + params);
//		try {
//			doAsync(new CallEarliest<MGridFootJson>() {
//				@Override
//				public void onCallEarliest() throws Exception {
//				}
//			}, new Callable<MGridFootJson>() {
//				@Override
//				public MGridFootJson call() throws Exception {
//					MGridFootJson mMGridFootJson = new MGridFootJson();
//					mMGridFootJson.setList(MMainGridFootService.parsePCTag(params, 1));
//					return mMGridFootJson;
//				}
//			}, new Callback<MGridFootJson>() {
//				@Override
//				public void onCallback(MGridFootJson result) {
//					Gson gson = new Gson();
//					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 顶部tag关键字
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void tagtab(final String params, final String callback) {
//		Log.d(TAG, "tagtab ========" + params);
//		try {
//			doAsync(new CallEarliest<MTabJson>() {
//				@Override
//				public void onCallEarliest() throws Exception {
//				}
//			}, new Callable<MTabJson>() {
//				@Override
//				public MTabJson call() throws Exception {
//					MTabJson mMTabJson = new MTabJson();
//					mMTabJson.setList(MTabService.parsePCTab(params));
//					return mMTabJson;
//				}
//			}, new Callback<MTabJson>() {
//				@Override
//				public void onCallback(MTabJson result) {
//					Gson gson = new Gson();
//					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 主列表数据
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void boxlist(final String params, final String callback) {
//		Log.d(TAG, "params ========" + params);
//		JSONObject jsonObject = null;
//		try {
//			jsonObject = JSON.parseObject(params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		final String url = jsonObject.getString("url");
//		final int pageNo = jsonObject.getInteger("pageNo");
//		try {
//			if (pageNo == 1) {
//				doAsync(new CallEarliest<MListBoxJson>() {
//					@Override
//					public void onCallEarliest() throws Exception {
//					}
//				}, new Callable<MListBoxJson>() {
//					@Override
//					public MListBoxJson call() throws Exception {
//						MListBoxJson mMListBoxJson = new MListBoxJson();
//						// http://brandservice.yoka.com/v1/?_c=cmsbrandindex&_a=getCmsForZhuNew&_moduleId=29&channel=23&column=103&skip=45&limit=15&p=2&callback=jsonpCallbackFunctionNo14962850979074116
//						// jsonpCallbackFunctionNo14962852369915114({"count":"15","time":"2017-06-01 10:47:17","status":"true","restime":0,"context":[{"link":"http://www.yoka.com/fashion/edittj/2017/0313/50292801072402.shtml","title":"鞋柜需要升级了!春天到底是小白鞋时髦还是拖鞋好看?","picture":"http://p6.yokacdn.com/pic/YOKA/2017-03-13/U10018P1TS1489377007_38294.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0310/50285601072293.shtml","title":"垫肩外套2017年强势回归 入手一件来增加时髦气场吧","picture":"http://p1.yokacdn.com/pic/YOKA/2017-03-10/U10011P1TS1489133014_97007.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0310/50285401072288.shtml","title":"早春想时髦就得好色！快来认领让你一穿就美的流行色吧","picture":"http://p3.yokacdn.com/pic/YOKA/2017-03-10/U10011P1TS1489128648_31878.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0309/50275101072186.shtml","title":"看完刘雯用白衬衫搭印花半身裙，我也想马上来三条！","picture":"http://p7.yokacdn.com/pic/YOKA/2017-03-09/U10011P1TS1489046495_62915.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0309/50273901072169.shtml","title":"2017秋冬时装周趋势报告 为你严选8个时髦必get关键词","picture":"http://p5.yokacdn.com/pic/YOKA/2017-03-09/U10011P1TS1489030491_14959.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0306/pic50246901199799.shtml","title":"hold住鲜嫩又性感的桃色才是春天的打开方式！","picture":"http://p5.yokacdn.com/pic/YOKA/2017-03-06/U10011P1TS1488792516_80509.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0303/50226201071652.shtml","title":"都说春捂秋冻，要捂就学江疏影关晓彤用帅气皮夹克吧","picture":"http://p4.yokacdn.com/pic/YOKA/2017-03-03/U10011P1TS1488509448_53846.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0301/pic50204701196040.shtml","title":"杨幂唐嫣范冰冰女神标配，衬衫+半裙好搭又时髦","picture":"http://p5.yokacdn.com/pic/YOKA/2017-03-01/U10011P1TS1488337820_36373.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0301/pic50203601195720.shtml","title":"教你换季不吃土，最省钱的换季技巧全在这了！","picture":"http://p8.yokacdn.com/pic/YOKA/2017-03-01/U10011P1TS1488335575_32766.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0228/pic50194601195312.shtml","title":"2017开春最百搭单品！卫衣承包你的所有时髦搭配","picture":"http://p5.yokacdn.com/pic/YOKA/2017-02-28/U10011P1TS1488247697_14742.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0228/pic50193801195301.shtml","title":"长外套＋平底鞋，打造最简约高级的时髦混搭风！","picture":"http://p5.yokacdn.com/pic/YOKA/2017-02-28/U10011P1TS1488246309_26439.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0228/pic50193201195271.shtml","title":"春天来了，终于可以脱掉秋裤穿我爱的花裙子喽","picture":"http://p5.yokacdn.com/pic/YOKA/2017-02-28/U10011P1TS1488245810_99556.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0225/pic50176601192957.shtml","title":"2017秋冬米兰时装周Versace秀场","picture":"http://p5.yokacdn.com/pic/YOKA/2017-02-25/U10011P1TS1487993509_28197.jpg"},{"link":"http://www.yoka.com/fashion/edittj/2017/0222/pic50152301190456.shtml","title":"2017依然火炸天 让百变GIGI教你初春这样搭","picture":"http://p5.yokacdn.com/pic/YOKA/2017-02-22/U10011P1TS1487749329_58203.gif"},{"link":"http://www.yoka.com/fashion/edittj/2017/0222/pic50152101190445.shtml","title":"早春升温寒意未退，外套里搭件Slogan Tee最潮","picture":"http://p5.yokacdn.com/pic/YOKA/2017-02-22/U10011P1TS1487748887_64510.jpg"}],"isloading":1,"queryOffSet":"75"})
//						mMListBoxJson.setList(MMainListBoxService.parseList(url, pageNo));
//						return mMListBoxJson;
//					}
//				}, new Callback<MListBoxJson>() {
//					@Override
//					public void onCallback(MListBoxJson result) {
//						Gson gson = new Gson();
//						WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//					}
//				});
//			} else {
//				final Map<String, String> headers = new HashMap<String, String>();
//				// headers.put("Cookie",
//				// "yka_gid=45d53573-9ff3-c9a5-1ef9-2133b1f52365; UM_distinctid=15bd6a1663777-0924f97c4d1129-35414878-1aeaa0-15bd6a166381be; a=rYWRb0VDLuA6; analyse_author_id=808b3dabacb64e72be216db6393e80cf; not.use.page.proxy=http%3A%2F%2Fwww.yoka.com%2Fdna%2Fm%2Fa6; __SessionHandler=8897c5ca7f3fbae9661563e18fb96904; KM.PASSPORT.MEMBER=uid%3D8687946%26guid%3D18534ef9e1169780ecf03e9107fb1a88%26id%3D%E5%BE%A1%E5%AE%88uodaztspr%26nickName%3D%E5%BE%A1%E5%AE%88uodaztspr%26nick%3D%26third_source%3D1%26visitDate%3D1496211949%26pwd%3Dd41d8cd98f00b204e9800998ecf8427e%26sign2%3D7d2f1f5200957df4b3e9d23664ff9fc2%26sighbbs%3D87B2F99A1B36D348BCCD5332B4E5531C%26avatar_url%3Dhttp%3A%2F%2Fucenter.yoka.com%2Fdata%2Favatar%2F008%2F68%2F79%2F46_avatar_small.jpg%26expire_time%3D604800%26is_validate%3D0%26open_id%3D%26qq_nick%3D%26real_name%3D%26third%3D1%26sign%3D77f91d969b8b66b811d546a9b236207c; KM.PASSPORT.MEMBER.LastLogin=login_time%3D1496211949%26register_time%3D%26reg_source%3D%26login_source%3D; KM.PASSPORT.MEMBER.TRACK=uid%3D8687946%26nickName%3D%E5%BE%A1%E5%AE%88uodaztspr; KM.PASSPORT.MEMBERGUID=18534ef9e1169780ecf03e9107fb1a88; yokaATC=yoka,535,2743,2715,1496213180016,www.yoka.com%2Fdna%2Fm%2F; ADVS=35289fbbea9bd9; ASL=17318,ppzkk,74e218de74e218de74e218de74e218de74e218de; yka_ph=%7B%20%27value%27%3A%20%2700000000000000010000101100011%27%2C%27lastdate%27%3A%20%271496246400000%27%7D; yka_srchost=www.yoka.com/dna; __clickidc=149155608220710046; ADVC=34fd9c408bfbc0; yka_tid=19cc9c8b-e231-a8aa-723f-decf1001b468; Hm_lvt_a641a94f2a28291909af4213f237173a=1495611999,1495782766,1496198049,1496284030; Hm_lpvt_a641a94f2a28291909af4213f237173a=1496284423");
//				headers.put("User-Agent", UrlUtils.yokaAgent);
//				// Accept-Language:zh-CN,zh;q=0.8
//				// headers.put("charset", "gbk");
//				String href = UrlUtils.YOKA_FASHIION_JSON + pageNo;
//				RequestQueue requestQueue = Volley.newRequestQueue(mWXSDKInstance.getContext());
//				JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, href, null, new Listener<org.json.JSONObject>() {
//					@Override
//					public void onResponse(org.json.JSONObject response) {
//						// TODO Auto-generated method stub
//						Gson gson = new Gson();
//						MainListJson mMainListJson = gson.fromJson(response.toString(), MainListJson.class);
//						if (mMainListJson != null && mMainListJson.getContext() != null) {
//							MListBoxJson mMListBoxJson = new MListBoxJson();
//							List<MListBoxBean> list = new ArrayList<MListBoxBean>();
//							MListBoxBean mlbean;
//							for (MainListBean lbean : mMainListJson.getContext()) {
//								mlbean = new MListBoxBean();
//								mlbean.setHref(lbean.getLink());
//								mlbean.setSrc(lbean.getPicture());
//								mlbean.setAlt(lbean.getTitle());
//								list.add(mlbean);
//							}
//							mMListBoxJson.setList(list);
//							WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(mMListBoxJson));
//						}
//					}
//				}, null)
//
//				{
//
//					/*
//					 * (non-Javadoc)
//					 * 
//					 * @see
//					 * com.android.volley.toolbox.StringRequest#getHeaders()
//					 */
//					@Override
//					public Map<String, String> getHeaders() throws AuthFailureError {
//						// TODO Auto-generated method stub
//						return headers;
//					}
//
//					protected Response<org.json.JSONObject> parseNetworkResponse(NetworkResponse response) {
//						org.json.JSONObject jsonObject;
//						try {
//							jsonObject = new org.json.JSONObject(new String(response.data, "gbk"));
//							return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//							return Response.error(new ParseError(e));
//						} catch (JSONException e) {
//							e.printStackTrace();
//							return Response.error(new ParseError(e));
//						}
//					}
//				};
//				requestQueue.add(jsonObjectRequest);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 顶部主menu菜单
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void menuTab(final String params, final String callback) {
//		Log.d(TAG, "menuTab ========" + params);
//		try {
//			doAsync(new CallEarliest<MTabJson>() {
//				@Override
//				public void onCallEarliest() throws Exception {
//				}
//			}, new Callable<MTabJson>() {
//				@Override
//				public MTabJson call() throws Exception {
//					MTabJson mMTabJson = new MTabJson();
//					mMTabJson.setList(MTabService.parsePCMenuTab(params));
//					return mMTabJson;
//				}
//			}, new Callback<MTabJson>() {
//				@Override
//				public void onCallback(MTabJson result) {
//					Gson gson = new Gson();
//					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * body 部tag标签
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void bodytag(final String params, final String callback) {
//		Log.d(TAG, "bodytag ========" + params);
//		try {
//			doAsync(new CallEarliest<MGridFootJson>() {
//				@Override
//				public void onCallEarliest() throws Exception {
//				}
//			}, new Callable<MGridFootJson>() {
//				@Override
//				public MGridFootJson call() throws Exception {
//					MGridFootJson mMGridFootJson = new MGridFootJson();
//					mMGridFootJson.setList(MMainGridFootService.parseBodyTag(params, 1));
//					return mMGridFootJson;
//				}
//			}, new Callback<MGridFootJson>() {
//				@Override
//				public void onCallback(MGridFootJson result) {
//					Gson gson = new Gson();
//					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * footgrid 标签
//	 * @param params
//	 * @param callback
//	 */
//	@SuppressWarnings("unchecked")
//	@WXModuleAnno(moduleMethod = true, runOnUIThread = true)
//	public void footgrid(final String params, final String callback) {
//		Log.d(TAG, "footgrid ========" + params);
//		try {
//			doAsync(new CallEarliest<MGridFootJson>() {
//				@Override
//				public void onCallEarliest() throws Exception {
//				}
//			}, new Callable<MGridFootJson>() {
//				@Override
//				public MGridFootJson call() throws Exception {
//					MGridFootJson mMGridFootJson = new MGridFootJson();
//					mMGridFootJson.setList(MMainGridFootService.parseBox(params, 1));
//					return mMGridFootJson;
//				}
//			}, new Callback<MGridFootJson>() {
//				@Override
//				public void onCallback(MGridFootJson result) {
//					Gson gson = new Gson();
//					WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback, gson.toJson(result));
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
