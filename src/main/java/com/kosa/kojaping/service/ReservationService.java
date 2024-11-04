package com.kosa.kojaping.service;

import com.kosa.kojaping.entity.Reservation;

import java.util.List;

public interface ReservationService {
    public void insertReservation(Reservation reservationDTO); //예약정보 등록

    //내가 예약한 숙소 목록
    List<Reservation> selectReservationsByMemberNo(String memberNo);

    public String createReservation(Reservation reservation);

    List<Reservation> findByCheckInOut(String accommodationNo, String checkIn, String checkOut);
}
