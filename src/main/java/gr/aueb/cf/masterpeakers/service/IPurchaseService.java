package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.PurchaseDTO;

public interface IPurchaseService {
    void createPurchase(PurchaseDTO purchaseDTO, String username);
}
