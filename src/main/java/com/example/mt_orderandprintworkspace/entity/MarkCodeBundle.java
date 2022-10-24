package com.example.mt_orderandprintworkspace.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "code_bundle")
public class MarkCodeBundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO связать со стороны заказа что бы получать список кодов из заказа
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "code_bundle", columnDefinition="TEXT")
    private String codeBundleJson;

    public MarkCodeBundle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCodeBundleJson() {
        return codeBundleJson;
    }

    public void setCodeBundleJson(String codeBundleJson) {
        this.codeBundleJson = codeBundleJson;
    }

    public List<String> getCodeBundle() {
        List<String> codeBundle;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            codeBundle = objectMapper.readValue(getCodeBundleJson(), List.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        return codeBundle;
    }

    public void setCodeBundle(List<String> codeBundle) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            setCodeBundleJson(objectMapper.writeValueAsString(codeBundle));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
