package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    Map<String, String> paymentData;
    @BeforeEach
    void setUp() {
        String x = "as";
        this.paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

    }

    @Test
    void testCreatePaymentNoPaymentData(){
        this.paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b"
                    , "voucher", paymentData);
        });
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b"
                , "voucher", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("voucher", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymetData().get("voucherCode"));
        assertEquals("PENDING", payment.getStatus);
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b"
                , "voucher", paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus);
    }

    @Test
    void testCreatePaymentWithInvalidStatus(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b"
                    , "voucher", paymentData, "ASING");
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b"
                , "voucher", paymentData, "ASING");
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus);
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b"
                , "voucher", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("ASING"));
    }
}
