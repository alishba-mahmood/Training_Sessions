package org.example.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value="SELECT * FROM Transaction t WHERE t.account_id = :accountId", nativeQuery=true)
    Transaction findByAccountId(@Param("accountId")Long accountId);

    @Query(value="SELECT * FROM Transaction t WHERE t.balance_id = :balanceId", nativeQuery=true)
    Transaction findByBalanceId(@Param("balanceId")Long balanceId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Transaction t WHERE t.account_id = ?1", nativeQuery=true)
    void deleteByAccountId(@Param("accountId")Long accountId);
}
