package com.glm.search;

import com.alibaba.fastjson.JSON;
import com.glm.search.config.GulimallElasticSearchConfig;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class GulimallSearchApplicationTests {
	@Autowired
	private RestHighLevelClient client;

	@Data
	@ToString
	static class Account {
		private int account_number;
		private int balance;
		private String firstname;
		private String lastname;
		private int age;
		private String gender;
		private String address;
		private String employer;
		private String email;
		private String city;
		private String state;
	}
	@Test
	public void testSearch(){
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("bank");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));

		TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
		AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");

		sourceBuilder.aggregation(ageAgg);
		sourceBuilder.aggregation(balanceAvg);

		System.out.println(sourceBuilder.toString());
		searchRequest.source(sourceBuilder);
		try {
			SearchResponse searchResponse = client.search(searchRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for(SearchHit hit : hits){
				String source = hit.getSourceAsString();
				Account account = JSON.parseObject(source, Account.class);
				System.out.println("account"+account);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testIndex(){
		IndexRequest indexRequest = new IndexRequest("users");
		indexRequest.id("1");
		User user = new User();
		user.setUserName("John");
		user.setUserName("18");
		user.setGender("male");
		String jsonString = JSON.toJSONString(user);
		indexRequest.source(jsonString, XContentType.JSON);
		//indexRequest.source("userName", "John", "age", 18, "gender", "male");
		try {
			IndexResponse indexResponse = client.index(indexRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);
			System.out.println(indexResponse);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Data
	class User{
		private String userName;
		private String gender;
		private Integer age;
	}

	@Test
	public void contextLoads() {
		System.out.println(client);
	}

}
