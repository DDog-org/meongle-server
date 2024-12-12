package ddog.groomer.application;

import ddog.domain.estimate.port.GroomingEstimatePersist;
import ddog.domain.groomer.Groomer;
import ddog.domain.groomer.port.GroomerPersist;
import ddog.domain.payment.Reservation;
import ddog.domain.payment.enums.ServiceType;
import ddog.domain.payment.port.ReservationPersist;
import ddog.domain.pet.Pet;
import ddog.domain.pet.port.PetPersist;

import ddog.groomer.application.exception.account.GroomerException;
import ddog.groomer.application.exception.account.GroomerExceptionType;
import ddog.groomer.presentation.schedule.dto.ScheduleResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleInfoService {
    private final GroomerPersist groomerPersist;
    private final PetPersist petPersist;
    private final ReservationPersist reservationPersist;
    private final GroomingEstimatePersist groomingEstimatePersist;

    public ScheduleResp getScheduleByGroomerId(Long groomerId) {
        Groomer groomerInfo = groomerPersist.findByGroomerId(groomerId).orElseThrow(() -> new GroomerException(GroomerExceptionType.GROOMER_NOT_FOUND));

        int estimateTotalCount = groomingEstimatePersist.findGroomingEstimatesByGroomerId(groomerId).size();
        int designationCount = groomingEstimatePersist.findGroomingEstimatesByGroomerIdAndProposal(groomerId).size();
        int reservationCount = groomingEstimatePersist.findGroomingEstimatesByGroomerIdAndEstimateStatus(groomerId).size();

        List<Reservation> findReservations = reservationPersist.findTodayGroomingReservationByPartnerId(LocalDateTime.now(), ServiceType.GROOMING, groomerId);

        List<ScheduleResp.TodayReservation> todayReservation = new ArrayList<>();

        for (Reservation reservation : findReservations) {
            Long petId = reservation.getPetId();
            Long estimateId = reservation.getEstimateId();

            Pet pet = petPersist.findByPetId(petId).get();
            todayReservation.add(ScheduleResp.TodayReservation.builder()
                    .petId(reservation.getPetId())
                    .petName(pet.getName())
                    .petImage(pet.getImageUrl())
                    .reservationTime(reservation.getSchedule().toLocalTime())
                    .desiredStyle(groomingEstimatePersist.findByEstimateId(estimateId).get().getDesiredStyle())
                    .build());
        }

        return ScheduleResp.builder()
                .totalScheduleCount(String.valueOf(estimateTotalCount))
                .totalReservationCount(String.valueOf(reservationCount))
                .designationCount(String.valueOf(designationCount))
                .allReservations(todayReservation)
                .build();
    }
}