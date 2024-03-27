package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.dto.read.BidForUserDto;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface UserBidRepository {
    Page<BidForUserDto> findAllByUserIdAndStatus(UUID id, PageRequest req, BidStatus status);
}
