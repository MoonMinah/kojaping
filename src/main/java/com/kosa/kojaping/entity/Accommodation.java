package com.kosa.kojaping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "accommodations")
public class Accommodation {

    @Id
    private String accommodationNo;

    private String memberNo;
    private String title;
    private String description;
    private double pricePerNight;
    private double latitude;
    private double longitude;
    private int postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private int maxGuest;
    private int bedroomCnt;
    private int bedCnt;
    private int bathCnt;
    private Long locationNo;
    private Date createdAt;
    private Date updateAt;
    private String status;

    private List<String> imageUrls;
}
