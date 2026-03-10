package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    private List<Payment> payments;
    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);
        this.order= new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudarajat");
        this.payments = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79o"
                , "voucher", paymentData1, order);
        payments.add(payment1);
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", "TF-000");
        Payment payment2 = new Payment("13652556-012a-4c07-b546-54eb1396d78c"
                , "voucher", paymentData1, order);
        payments.add(payment2);
    }

    @Test
    void testCreatePayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.createPayment(payment.getMethod(), payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testPaymentIfAlreadyExist() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.createPayment(payment.getMethod(), payment));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testUpdateStatus() {
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(),
                payment.getMethod(), payment.getPaymentData(), payment.getOrder());
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.updateStatus(payment.getId(), PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.updateStatus(payment.getId(), "ANTEK_ASING"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidId() {
        doReturn(null).when(paymentRepository).findById("Monokotil");

        assertThrows(NoSuchElementException.class,
                () -> paymentService.updateStatus("Monokotil", OrderStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testFindByIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        doReturn(null).when(paymentRepository).findById("Monokotil");
        assertNull(paymentService.findById("Monokotil"));
    }

    @Test
    void testGetAllPayment() {
        Payment payment = payments.get(1);
        doReturn(payments).when(paymentRepository).getAll();
        List<Payment> results = paymentService.getAll();
        assertEquals(2, results.size());
    }
}
