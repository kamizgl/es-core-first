package com.roncoo.es.score.first;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.store.Store;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.List;


public class EmployeeSearchApp {

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.31.35"), 9300));
//        prepareData(client);
//        executeSearch(client);
        createjinweidu(client);
        getStores(client);
    }

     private static void executeSearch(TransportClient client) {
         SearchResponse searchResponse = client.prepareSearch("company")
                 .setTypes("employee")
                 .setQuery(QueryBuilders.matchQuery("position", "finance"))
                 .setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
                 .get();
         System.out.println(searchResponse);
     }
     private static void aggrApp(TransportClient client) {
/*
         client.prepareSearch("company").addAggregation(AggregationBuilders.dateHistogram())
*/
     }

     private static void prepareData(TransportClient client) throws Exception {
        client.prepareIndex("company", "employee", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jack")
                        .field("age", 27)
                        .field("position", "technique software")
                        .field("country", "china")
                        .field("join_date", "2017-01-01")
                        .field("salary", 10000)
                        .endObject())
                .get();

        client.prepareIndex("company", "employee", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "marry")
                        .field("age", 35)
                        .field("position", "technique manager")
                        .field("country", "china")
                        .field("join_date", "2017-01-01")
                        .field("salary", 12000)
                        .endObject())
                .get();

        client.prepareIndex("company", "employee", "3")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "tom")
                        .field("age", 32)
                        .field("position", "senior technique software")
                        .field("country", "china")
                        .field("join_date", "2016-01-01")
                        .field("salary", 11000)
                        .endObject())
                .get();

        client.prepareIndex("company", "employee", "4")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jen")
                        .field("age", 25)
                        .field("position", "junior finance")
                        .field("country", "usa")
                        .field("join_date", "2016-01-01")
                        .field("salary", 7000)
                        .endObject())
                .get();

        client.prepareIndex("company", "employee", "5")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "mike")
                        .field("age", 37)
                        .field("position", "finance manager")
                        .field("country", "usa")
                        .field("join_date", "2015-01-01")
                        .field("salary", 15000)
                        .endObject())
                .get();
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
                         .field("location", "31.226789,121.495775")
                         .endObject())
                 .get();
         System.out.println(response.getResult());


     }


     /**
      * 查询地理位置
      * @param client
      * @throws Exception
      */
     private static List<Store> getStores(TransportClient client) {
         //拼接条件
         BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
         QueryBuilder isdeleteBuilder = QueryBuilders.matchAllQuery();
         // 以某点为中心，搜索指定范围
         GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
         distanceQueryBuilder.point(31.240869, 121.458118121);
         //查询单位：km
         distanceQueryBuilder.distance(10, DistanceUnit.KILOMETERS);
         boolQueryBuilder.filter(distanceQueryBuilder);
         boolQueryBuilder.must(isdeleteBuilder);

         client.prepareSearch("geo").setTypes("city")
                 .setQuery(boolQueryBuilder)
                 .setFrom(0)
                 .setSize(10).get()
         ;

         return null ;
     }

}
