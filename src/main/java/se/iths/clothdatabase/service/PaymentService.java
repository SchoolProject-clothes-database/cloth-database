package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.PaymentEntity;
import se.iths.clothdatabase.repository.PaymentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentEntity createPayment(PaymentEntity paymentEntity) {
        return paymentRepository.save(paymentEntity);
    }

    public void deletePayment(Long id) {
        PaymentEntity foundPayment = paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        paymentRepository.deleteById(foundPayment.getId());
    }

    public Optional<PaymentEntity> findPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Iterable<PaymentEntity> findAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentEntity updatePayment(Long id, PaymentEntity paymentEntity) {
        PaymentEntity foundPayment = paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        foundPayment.setAmount(paymentEntity.getAmount());

        return paymentRepository.save(paymentEntity);
    }

}
