package com.sparkleclean.sparkleclean.cleaningservice.service;

import com.sparkleclean.sparkleclean.booking.repository.BookingRepository;
import com.sparkleclean.sparkleclean.cleaningservice.model.CleaningService;
import com.sparkleclean.sparkleclean.cleaningservice.repository.CleaningServiceRepository;
import com.sparkleclean.sparkleclean.exception.ResourceNotFoundException;
import com.sparkleclean.sparkleclean.exception.ServiceAlreadyExistsException;
import com.sparkleclean.sparkleclean.exception.ServiceInUseException;
import com.sparkleclean.sparkleclean.review.repository.ReviewRepository;
import com.sparkleclean.sparkleclean.web.dto.CleaningServiceDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CleaningServiceService {

    private final CleaningServiceRepository repository;
    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;


    public CleaningServiceService(CleaningServiceRepository repository, BookingRepository bookingRepository, ReviewRepository reviewRepository) {
        this.repository = repository;
        this.bookingRepository = bookingRepository;
        this.reviewRepository = reviewRepository;
    }
    @Transactional
    public void create (CleaningServiceDto dto){
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new ServiceAlreadyExistsException("Cleaning service already exists.");
        }
        CleaningService cleaningService = new CleaningService();
        applyChanges(cleaningService,dto);

        repository.save(cleaningService);
    }
    public List<CleaningService> getAll(){
        return repository.findAll();
    }

    public CleaningService getById (UUID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));

    }


    public CleaningServiceDto getDtoById(UUID id){
        CleaningService cleaningService = getById(id);
        CleaningServiceDto dto = new CleaningServiceDto();
        dto.setName(cleaningService.getName());
        dto.setDescription(cleaningService.getDescription());
        dto.setPrice(cleaningService.getPrice());
        dto.setDurationMinutes(cleaningService.getDurationMinutes());

        return dto;
    }
    @Transactional
    public void update(UUID id, CleaningServiceDto dto){
        CleaningService cleaningService=getById(id);

        Optional<CleaningService> name = repository.findByName(dto.getName());
        if(name.isPresent() && !name.get().getId().equals(id)){
            throw  new RuntimeException("Cleaning service already exists.");
        }

        applyChanges(cleaningService,dto);
        repository.save(cleaningService);
    }
    @Transactional
    public void delete(UUID id){
        CleaningService cleaningService = getById(id);

        if(bookingRepository.existsByServiceId(id) || reviewRepository.existsByServiceId(id)){
            throw new ServiceInUseException("This service has bookings or reviews and cannot be deleted.");
        }

        repository.delete(cleaningService);

    }

    private void applyChanges(CleaningService cleaningService,CleaningServiceDto dto){
        cleaningService.setName(dto.getName());
        cleaningService.setDescription(dto.getDescription());
        cleaningService.setPrice(dto.getPrice());
        cleaningService.setDurationMinutes(dto.getDurationMinutes());

    }



}
