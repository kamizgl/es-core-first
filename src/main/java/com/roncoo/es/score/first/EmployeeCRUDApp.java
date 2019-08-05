package com.roncoo.es.score.first;

import java.net.InetAddress;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.org.apache.xpath.internal.operations.String;
import javafx.scene.NodeBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 员工增删改查的应用程序
 * @author Administrator
 *
 */
public class EmployeeCRUDApp {
	private java.lang.String index = "company";
	private java.lang.String type = "employee";


	public static void main(String[] args) throws Exception {
		// 先构建client
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();

		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.31.35"), 9300));



//		createEmployee(client);
//		getEmployee(client);
		createjinweidu(client);
//		updateEmployee(client);
//		deleteEmployee(client);
//		getEmployeeBycondtion(client);
		client.close();
	}
	
	/**
	 * 创建员工信息（创建一个document）
	 * @param client
	 */
	private static void createEmployee(TransportClient client) throws Exception {
		IndexResponse response = client.prepareIndex("company", "employee", "1")
				.setSource(XContentFactory.jsonBuilder()
						.startObject()
							.field("name", "jack")
							.field("age", 27)
							.field("position", "technique")
							.field("country", "china")
							.field("join_date", "2017-01-01")
							.field("salary", 10000)
							.field("haha",123123)
						.endObject())
				.get();
		System.out.println(response.getResult()); 
	}
	
	/**
	 * 获取员工信息
	 * @param client
	 * @throws Exception
	 */
	private static void getEmployee(TransportClient client) throws Exception {
		GetResponse response = client.prepareGet("company", "employee", "1").get();
		System.out.println(response.getSourceAsString()); 
	}


	/**
	 * 获取员工信息根据条件
	 * @param client
	 * @throws Exception
	 */
	private static void getEmployeeBycondtion(TransportClient client) throws Exception {
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("position", "manager").operator(Operator.AND);


		SearchResponse searchResponse = client.prepareSearch("company").setTypes("employee").setQuery(matchQueryBuilder).get();


		SearchHits hits =
				searchResponse.getHits();
//		for (SearchHit hit : hits) {
//			long totalHits = hits.totalHits;
//			String id = hit.getId();
//			String sourceAsString = hit.getSourceAsString();
//			Map<String, DocumentField> fields = hit.getFields();
//			String index = hit.getIndex();
//			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//		}
		System.out.println(hits);
	}


	/**
	 * 获取员工信息
	 * @param client
	 * @throws Exception
	 */
	private static void getEmployee2(TransportClient client) throws Exception {
		GetResponse response = client.prepareGet("company", "employee", "1").get();
		System.out.println(response.getSourceAsString());

		client.prepareSearch("company").setTypes("employee")
				.setPostFilter(QueryBuilders.rangeQuery("age")
						.from(30).to(40))
				.setFrom(0)
				.setSize(1).get()
		;
	}
	
	/**
	 * 修改员工信息
	 * @param client
	 * @throws Exception
	 */
	private static void updateEmployee(TransportClient client) throws Exception {
		UpdateResponse response = client.prepareUpdate("company", "employee", "2")
				.setDoc(XContentFactory.jsonBuilder()
							.startObject()
								.field("position", "technique manager")
							.endObject()).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
				.get();
		System.out.println(response.getResult());
 	}
	
	/**
	 * 删除 员工信息
	 * @param client
	 * @throws Exception
	 */
	private static void deleteEmployee(TransportClient client) throws Exception {
		DeleteResponse response = client.prepareDelete("company", "employee", "1").get();
		System.out.println(response.getResult());  
	}

	/**
	 * 创建经纬度（创建一个document）
	 * @param client
	 */
	private static void createjinweidu(TransportClient client) throws Exception {
		IndexResponse response = client.prepareIndex("geo", "city", "1")
				.setSource(XContentFactory.jsonBuilder()
						.startObject()
						.field("name", "上海")
						.field("location", "121.495775,31.226789")
						.endObject())
				.get();
		System.out.println(response.getResult());
	}
}
