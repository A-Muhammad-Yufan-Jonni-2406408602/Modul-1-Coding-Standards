package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(String method, Payment payment) {
        if(paymentRepository.findById(payment.getId()) == null){
            if (method.equals("voucher")){
                determineStatusVoucher(payment, payment.getPaymentData());
                paymentRepository.save(payment);
                return payment;
            }
            if (method.equals("bank")){
                return payment;
            }
        }
        return null;
    }

    @Override
    public Payment updateStatus(String paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            Payment newPayment = new Payment(payment.getId()
                    , payment.getMethod(), payment.getPaymentData(),status);
            paymentRepository.save(newPayment);
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment findById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.getAll();
    }

    private void determineStatusVoucher(Payment payment, Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (isValidVoucher(voucherCode)){
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    private boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null){
            return false;
        }
        if (voucherCode.length() != 16){
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        int numericalChar = 0;
        for (char character : voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numericalChar++;
            }
        }
        return numericalChar == 8;
    }

    private void determineStatusBank(Payment payment, Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bankName == null) {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        } else if (referenceCode == null) {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
