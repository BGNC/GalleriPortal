package com.bgnc.galleriportal.repository;

import com.bgnc.galleriportal.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends GenericRepository<Account, Long> {
}
