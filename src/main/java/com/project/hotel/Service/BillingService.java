package com.project.hotel.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.DTO.BillingDTO;
import com.project.hotel.Entity.Billing;
import com.project.hotel.Repository.BillingRepository;

@Service
public class BillingService
 {
    @Autowired
    private BillingRepository billingRepository;

    public String saveBill(BillingDTO dto) {

        Billing billing = new Billing();

        billing.setBillNo(dto.getBillNo());
        billing.setTableNo(dto.getTableNo());
        billing.setCustomerName(dto.getCustomerName());
        billing.setBillDate(dto.getBillDate());
        billing.setSubtotal(dto.getSubtotal());
        billing.setGst(dto.getGst());
        billing.setGrandTotal(dto.getGrandTotal());

        billingRepository.save(billing);

        return "Bill Saved Successfully";
    }
}
