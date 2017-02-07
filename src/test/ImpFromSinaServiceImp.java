package com.htsc.datacenter.service.imp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htsc.datacenter.dao.nosql.BaseDao;
import com.htsc.datacenter.service.ImpFromSinaService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ImpFromSinaServiceImp implements ImpFromSinaService {

	@Autowired
	private BaseDao baseDaoImp;

	/*
	 * 存储至nosql数据库
	 */
	public void saveBatch(List<DBObject> objects) {
		baseDaoImp.insertBatch(objects, "sina");
	}

	public void getData(String group, String classification, String url) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);

		httpGet.addHeader("Accept", "application/json");

		CloseableHttpResponse rep = httpClient.execute(httpGet);

		HttpEntity repEntity = rep.getEntity();

		InputStream resStream = repEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(resStream, "GB2312"));
		StringBuffer resBuffer = new StringBuffer();
		String resTemp = "";
		while ((resTemp = br.readLine()) != null) {
			resBuffer.append(resTemp);
		}

		rep.close();
		httpClient.close();

		int i = resBuffer.indexOf("{");
		int j = resBuffer.lastIndexOf("}");

		String json = resBuffer.substring(i, j + 1);

		JSONObject jsonObject = JSONObject.fromObject(json);

		JSONArray config = JSONArray.fromObject(jsonObject.get("config"));

		JSONArray all = JSONArray.fromObject(config.getJSONObject(0).get("all"));

		// jsonObject.get("count");

		JSONArray data = JSONArray.fromObject(jsonObject.get("data"));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String version = df2.format(new Date());
		JSONArray time = JSONArray.fromObject(all.get(0));
		String timeName = time.getString(1);
		ArrayList<DBObject> list = new ArrayList<DBObject>();
		for (int m = 1; m < all.size(); m++) {

			String signName = "";
			String signUnit = "";
			JSONArray a = JSONArray.fromObject(all.get(m));

			signName = a.getString(1);
			signUnit = a.size() < 3 ? "" : a.getString(2);
			DBObject dbo = new BasicDBObject();
			dbo.put("数据来源", "新浪财经");
			dbo.put("版本", version);
			dbo.put("指标分组", group);
			dbo.put("指标分类", classification);
			dbo.put("指标名称", signName);
			dbo.put("指标单位", signUnit);
			BasicDBObject[] dboData = new BasicDBObject[data.size()];
			for (int n = 0; n < data.size(); n++) {
				JSONArray dataArray = JSONArray.fromObject(data.get(n));
				DBObject dboObject = new BasicDBObject();
				String date = dataArray.get(0).toString();
				if (date.contains(".")) {
					if (date.substring(date.indexOf(".")).length() - 1 < 2) {
						date = date.substring(0, date.indexOf(".")) + "0" + date.substring(date.indexOf(".") + 1);
					} else {
						date = date.substring(0, date.indexOf(".")) + date.substring(date.indexOf(".") + 1);
					}
				}
				dboObject.put(timeName, date);
				dboObject.put("数值", dataArray.get(m).toString());
				dboData[n] = (BasicDBObject) dboObject;

			}
			dbo.put("sign_infos", dboData);
			dbo.put("create_time", df.format(new Date()));// 添加导入时间
			list.add(dbo);
		}
		saveBatch(list);
		// System.out.println(dbos.toString());
	}

	public void getSinaData() throws Exception {
		String[][] arrays = new String[9][];
		arrays[0] = new String[] { "国民经济", "nation", "国内生产总值(年度)", "国内生产总值(季度)", "支出法GDP结构", "支出法国内生产总值", "三大需求对GDP贡献", "三大产业对GDP拉动", "三大产业贡献率", "地区生产总值", "居民消费水平", "工业增加值(月度)", "工业增加值(按行业分)",
				"地区工业增加值(月度)", "分行业增加值及构成", "社会消费品零售总额", "主要工业产品产量" };
		arrays[1] = new String[] { "价格指数", "price", "居民消费价格指数", "按项目分居民消费价格", "地区居民消费价格指数", "工业品出厂价格指数", "工业品出厂价格(年度)", "工业品出厂价格(月度)", "按行业分工业品出厂价", "分地区工业品价格指数", "企业商品价格(月度)", "各地区固投价格指数",
				"地区农业生产资料价格", "燃料动力购进价格指数", "商品零售价格指数", "农产品生产价格指数" };
		arrays[2] = new String[] { "居民收入", "resident", "居民收入与物价信心", "农村居民家庭人均收入", "城镇居民家庭收支", "城镇从业人员报酬" };
		arrays[3] = new String[] { "固定资产投资", "fixed", "全社会固定资产投资", "固定资产投资完成情况", "按行业50万以上项目", "城镇固投和建设规模", "按经济类型分资产投资", "按项目规模分资产投资", "施工、竣工房屋信息", "城镇50万元以上项目", "各地区行业资产投资", "各地区固投完成情况", "行业固投完成情况",
				"全国固投完成情况" };
		arrays[4] = new String[] { "景气指数", "boom", "宏观经济景气指数", "企业景气及企业家信心", "银行景气及银行家信心", "宏观经济预警信号表", "消费者信心指数", "制造业采购经理指数", "非制造业采购经理指数" };
		arrays[5] = new String[] { "对外经济贸易", "foreign", "货物进出口总额", "按贸易分进出口总额", "利用外资概况", "利用外资情况(月度)", "利用外资(投资方式)", "分国别利用外资情况", "分行业利用外资情况" };
		arrays[6] = new String[] { "金融信息", "fininfo", "货币供应量(年底余额)", "货币供应量", "存款利率", "贷款利率", "存款准备金率", "央行黄金和外汇储备", "货币供应量同比增长率", "机构人民币信贷收支", "央行货币当局资产负债", "央行汇率", "机构本外币信贷收支", "机构外汇信贷收支全国股票交易统计",
				"银行间市场债券回购", "机构按部门信贷收支", "其他存款性公司负债", "其它商业银行信贷收支", "存款性公司概览", "宏观类银行资产负债", "保险业经营情况", "信贷资金平衡(来源)", "信贷资金平衡(运用)", "金融机构现金支出", "金融机构现金收入", "金融机构现金投放回笼" };
		arrays[7] = new String[] { "国家财政", "finance", "财政收支总额及增长", "财政收入及比重", "财政支出及比重", "国家财政主要支出项目", "各项税收", "外债风险指标", "外债余额", "政策性补贴支出", "国家财政分项目收入", "各地区财政支出", "各地区财政收入", "预算外资金分项目支出",
				"预算外资金分项目收入", "财政按功能性质支出", "财政用于农业的支出", "财政债务还本付息支出", "财政债务发行情况", "财政主要支出项目", "财政主要收入项目", "财政用于科研的支出", "财政用于福利的支出" };
		arrays[8] = new String[] { "行业信息", "industry", "房屋销售价指数(季度)", "房屋销售价指数(环比)", "房屋销售价指数(同比)", "大中城市房屋价格指数", "国房景气指数", "全国分地区用电量", "全社会用电分类情况表", "全社会分行业用电情况", "农林牧渔业总产值", "地区农林牧渔业总产值",
				"全社会客货运输量", "邮电业务基本情况", "地方接待情况", "地方国际旅游外汇收入", "旅游事业发展情况", "国际旅游外汇收入构成", "主要城市接待情况", "入境旅游人数(按目的)", "入境旅游人数(按性别)", "来华旅游入境人数", "民航客座率及载运率", "航贸运价指数" };
		for (int i = 0; i < 9; i++) {
			String title = arrays[i][0];
			String cate = arrays[i][1];
			// System.out.println(arrays[i].length);
			for (int j = 2; j < arrays[i].length; j++) {
				String url = "http://money.finance.sina.com.cn/mac/api/jsonp.php/SINAREMOTECALLCALLBACK/MacPage_Service.get_pagedata?cate=" + cate + "&event=0&from=0&num=50000&condition";
				String maxTil = arrays[i][j];
				getData(title, maxTil, url);
				// String shishi = "123";
			}
		}
	}
}
