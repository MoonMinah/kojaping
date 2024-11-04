package com.kosa.kojaping.controller;

import com.kosa.kojaping.entity.Accommodation;
import com.kosa.kojaping.entity.Member;
import com.kosa.kojaping.entity.Payment;
import com.kosa.kojaping.entity.Reservation;
import com.kosa.kojaping.service.AccommodationService;
import com.kosa.kojaping.service.MemberService;
import com.kosa.kojaping.service.PaymentService;
import com.kosa.kojaping.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/guest")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/mypage/myInfo")
    public String myInfo(Model model) {
        String sessionId = SecurityContextHolder.getContext().getAuthentication().getName();

        Member resultMember = memberService.selectMemberByUserId(sessionId);
        log.info("myInfo() : selectMemberByUserId() = {}", resultMember);

        model.addAttribute("loginMember", resultMember);

        return "myInfo";
    }

    @GetMapping("/mypage/myReservation")
    public String myReservation(Model model) {

        String sessionId = SecurityContextHolder.getContext().getAuthentication().getName();
        ;log.info("myReservation() : sessionId() = {}", sessionId);

        Member resultMember = memberService.selectMemberByUserId(sessionId);
        log.info("myReservation() : selectMemberByUserId() = {}", resultMember.toString());

        // 게스트의 예약 목록을 가져옴
        List<Reservation> reservations = reservationService.selectReservationsByMemberNo(resultMember.getMemberNo());

        log.info("myReservation() : selectReservationsByMemberNo() = {}", reservations);

        for (Reservation reservation : reservations) {
            // 예약된 숙소 정보 가져오기
            Accommodation accommodation = accommodationService.findAccommodationByNo(reservation.getAccommodationNo());
            reservation.setAccommodation(accommodation); // ReservationDTO에 숙소 정보 저장

            log.info("myReservation() : selectReservationsByAccommodationNo() = {}", reservation.toString());
            Payment payment = paymentService.selectPaymentsByReservationNo(reservation.getReservationNo());

            // 결제 정보가 null일 경우 처리
            if (payment != null) {
                // LocalDateTime을 포맷된 String으로 변환
                String formattedPaymentDate = payment.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                model.addAttribute("formattedPaymentDate", formattedPaymentDate);
                reservation.setPayment(payment);
            } else {
                log.info("결제 정보가 없습니다. Reservation No: " + reservation.getReservationNo());
            }

            log.info("결제 정보: " + payment); // 결제 정보가 제대로 들어오는지 확인
        }

        log.info("reservation : " + reservations.toString());
        model.addAttribute("resultMember", resultMember);
        model.addAttribute("reservations", reservations);

        return "myReservation"; // 예약 목록 페이지로 이동
    }



    @GetMapping("/mypage/myReviews")
    public String myReviews() {
        return "myReviews";
    }

    @PostMapping("/mypage/update")
    public String updateMember(Member member, Model model, RedirectAttributes redirectAttributes) {
        String sessionId = SecurityContextHolder.getContext().getAuthentication().getName();

        member.setUserId(sessionId);
        memberService.updateMember(member);

        Member resultMember = memberService.selectMemberByUserId(sessionId);
        log.info("updateMember() : selectMemberByUserId() = {}", resultMember);

        model.addAttribute("loginMember", resultMember);

        redirectAttributes.addFlashAttribute("message", "회원정보가 성공적으로 수정되었습니다.");

        return "redirect:/guest/mypage/myInfo";
    }

    @GetMapping("/mypage/delete")
    public String deleteMember(Member member, RedirectAttributes redirectAttributes) {
        String sessionId = SecurityContextHolder.getContext().getAuthentication().getName();

//        member.setUserId(sessionId);
        int resultMember = memberService.updateMemberStatusByUserId(sessionId);

        if(resultMember > 0) {
            redirectAttributes.addFlashAttribute("message", "회원 탈퇴 처리가 완료되었습니다.");

            return "redirect:/loginForm";
        }

        return "redirect:/guest/mypage/myInfo";
    }
}
