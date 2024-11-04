package com.kosa.kojaping.repo;

import com.kosa.kojaping.entity.Accommodation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepo extends ElasticsearchRepository<Accommodation, String> {
    //입력한 문자로 시작하는 주소의 숙소를 실시간으로 검색
    List<Accommodation> findByAddressStartingWith(String address);
}