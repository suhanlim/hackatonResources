package com.hackaton.javaelasticsearch.domain.elasticsearch.search.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

@NoArgsConstructor
@Data
public class SearchVectorResponse {

    private List<SearchResult> results; // 검색 결과를 저장할 리스트

    public SearchVectorResponse(SearchHits<SearchVectorRequest> searchResults) {
    }

    // 검색 결과를 나타내는 내부 클래스
    public static class SearchResult {
        private String id; // 문서 ID
        private float score; // 유사도 점수

        public SearchResult(String id, float score) {
            this.id = id;
            this.score = score;
        }

        // id 필드에 대한 게터
        public String getId() {
            return id;
        }

        // score 필드에 대한 게터
        public float getScore() {
            return score;
        }
    }

    public SearchVectorResponse(List<SearchResult> results) {
        this.results = results;
    }

    // results 필드에 대한 게터
    public List<SearchResult> getResults() {
        return results;
    }
}
