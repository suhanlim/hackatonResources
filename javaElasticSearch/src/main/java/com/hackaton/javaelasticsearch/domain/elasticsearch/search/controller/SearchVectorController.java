package com.hackaton.javaelasticsearch.domain.elasticsearch.search.controller;

import com.hackaton.javaelasticsearch.domain.elasticsearch.search.model.SearchVectorRequest;
import com.hackaton.javaelasticsearch.domain.elasticsearch.search.model.SearchVectorResponse;
import com.hackaton.javaelasticsearch.domain.elasticsearch.search.service.SearchVectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchVectorController {

    private final SearchVectorService searchVectorService;

    @Autowired
    public SearchVectorController(SearchVectorService searchVectorService) {
        this.searchVectorService = searchVectorService;
    }

    @PostMapping("/search/similarity/vectors")
    public ResponseEntity<List<SearchVectorResponse.SearchResult>> searchBySimilarity(@RequestBody SearchVectorRequest request) {
        float[] vector = request.getVector(); // 요청에서 벡터 데이터를 추출
        float minSimilarity = 0.7f; // 최소 유사도 임계값 설정

        // 서비스 메서드를 호출하여 유사도 검색 수행
        var searchHits = searchVectorService.findBySimilarity(vector, minSimilarity);

        // 검색 결과를 SearchVectorResponse로 변환
        List<SearchVectorResponse.SearchResult> results = new ArrayList<>();
        for (var hit : searchHits.getSearchHits()) {
            results.add(new SearchVectorResponse.SearchResult(hit.getContent().getId(), hit.getScore()));
        }

        return ResponseEntity.ok(results);
    }
}
