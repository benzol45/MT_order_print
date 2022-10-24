package com.example.mt_orderandprintworkspace.DTO;

import com.example.mt_orderandprintworkspace.entity.MarkCodeBundle;
import com.example.mt_orderandprintworkspace.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class MarkCodeBundleDTO {
    private long id;
    private String dateOfOrder;
    private String productName;
    private int quantity;

    public MarkCodeBundleDTO() {
    }

    public MarkCodeBundleDTO(long id, String dateOfOrder, String productName, int quantity) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.productName = productName;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static MarkCodeBundleDTO convertFromBundle(MarkCodeBundle markCodeBundle) {
        Order orderOfBundle = markCodeBundle.getOrder();
        return new MarkCodeBundleDTO(
                markCodeBundle.getId(),
                orderOfBundle.getCreateDateForForShow(),
                orderOfBundle.getProduct().getName() + " /" + orderOfBundle.getProduct().getGTIN() + "/",
                orderOfBundle.getQuantity()
        );
    }

    public static List<MarkCodeBundleDTO> convertFromBundleList(List<MarkCodeBundle> markCodeBundles) {
        List<MarkCodeBundleDTO> codeBundleDTOs = new ArrayList<>();
        for (MarkCodeBundle markCodeBundle: markCodeBundles) {
            codeBundleDTOs.add(convertFromBundle(markCodeBundle));
        }

        return codeBundleDTOs;
    }
}
