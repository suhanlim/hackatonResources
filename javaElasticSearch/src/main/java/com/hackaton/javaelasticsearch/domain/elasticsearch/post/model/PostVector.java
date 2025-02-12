package com.hackaton.javaelasticsearch.domain.elasticsearch.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "pdf_index")
public class PostVector {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Dense_Vector, dims = 100) // dims는 실제 차원 수에 따라 조정해야 합니다.
    private float[] vector; // Elasticsearch 8.7.0은 float 배열을 사용합니다.

}
