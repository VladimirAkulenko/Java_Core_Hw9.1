package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        String url = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

        HttpGet request = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(request);

        ObjectMapper mapper = new ObjectMapper();

        List<Cat> answerCat = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        answerCat.stream().filter(cat -> cat.getUpvotes() != null && cat.getUpvotes() > 0).forEach(System.out::println);
    }
}
