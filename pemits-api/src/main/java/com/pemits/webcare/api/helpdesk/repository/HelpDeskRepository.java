package com.pemits.webcare.api.helpdesk.repository;

import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import com.pemits.webcare.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
@Repository
public interface HelpDeskRepository extends BaseRepository<HelpDesk, Long> {
}
