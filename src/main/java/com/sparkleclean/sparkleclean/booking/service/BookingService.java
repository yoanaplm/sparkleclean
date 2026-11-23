package com.sparkleclean.sparkleclean.booking.service;

import com.sparkleclean.sparkleclean.booking.model.Booking;
import com.sparkleclean.sparkleclean.booking.model.BookingStatus;
import com.sparkleclean.sparkleclean.booking.repository.BookingRepository;
import com.sparkleclean.sparkleclean.cleaningservice.service.CleaningServiceService;
import com.sparkleclean.sparkleclean.exception.InvalidBookingTimeException;
import com.sparkleclean.sparkleclean.user.service.UserService;
import com.sparkleclean.sparkleclean.web.dto.BookingDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final CleaningServiceService cleaningServiceService;

    public BookingService(BookingRepository bookingRepository, UserService userService, CleaningServiceService cleaningServiceService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.cleaningServiceService = cleaningServiceService;
    }

    @Transactional
    public void create(UUID userId, UUID serviceId, BookingDto dto){
        LocalDateTime scheduledAt = dto.getScheduledAt();

        if(!scheduledAt.isAfter(LocalDateTime.now())){
            throw  new InvalidBookingTimeException("The booking must be in the future");

        }
        if (scheduledAt.getHour()<9 || scheduledAt.getHour()>=17){
            throw  new InvalidBookingTimeException("Bookings are available between 09:00 and 17:00");

        }
        Booking booking = new Booking();
        booking.setUser(userService.getById(userId));
        booking.setCleaningService(cleaningServiceService.getById(serviceId));
        booking.setScheduledAt(scheduledAt);
        booking.setAddress(dto.getAddress());
        booking.setNotes(dto.getNotes());
        booking.setStatus(BookingStatus.PENDING);
        bookingRepository.save(booking);
    }
    public List<Booking> getByUser (UUID userId){
        return bookingRepository.findUserIdOrderByScheduledAtAsc(userId);
    }
}
