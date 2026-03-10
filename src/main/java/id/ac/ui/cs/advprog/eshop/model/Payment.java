package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.status = "PENDING";

        if (paymentData.isEmpty()){
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this(id, method, paymentData);
        if(order == null) {
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }
    }
    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
