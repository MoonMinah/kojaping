package com.kosa.kojaping.service;

import com.kosa.kojaping.entity.Accommodation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductService {

    // MySQL에서 Elasticsearch로 데이터 동기화
    void syncMySQLToElasticsearch();

    void updateProductInElasticsearch(Accommodation accommodation);

    Accommodation findProductInElasticsearchByAccommodationNo(String accommodationNo);

    void insertProductToElasticsearch(Accommodation accommodation);

    List<Accommodation> getAllProductsFromMySQL();

    void deleteProduct(String AccommodationNo);

    // Elasticsearch 에서 유사한 주소명으로 검색
    ArrayList<Accommodation> searchProductsByAddress(String address);
}
