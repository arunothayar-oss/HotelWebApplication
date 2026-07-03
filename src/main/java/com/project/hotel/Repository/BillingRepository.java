package com.project.hotel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.hotel.Entity.Billing;
public interface BillingRepository extends JpaRepository<Billing, Long> 
 {
    @Query("SELECT COALESCE(SUM(b.grandTotal), 0) FROM Billing b")
    Double getTotalSales();

}
