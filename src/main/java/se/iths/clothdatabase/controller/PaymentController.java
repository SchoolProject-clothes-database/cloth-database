package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.PaymentEntity;
import se.iths.clothdatabase.service.PaymentService;

import java.util.Optional;

@RestController
@RequestMapping("payment")
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<PaymentEntity> createPayment(@RequestBody PaymentEntity payment) {
        PaymentEntity createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<PaymentEntity>> findPaymentById(@PathVariable Long id) {
        Optional<PaymentEntity> foundPayment = paymentService.findPaymentById(id);
        return new ResponseEntity<>(foundPayment, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<PaymentEntity>> findAllPayment() {
        Iterable<PaymentEntity> allPayment = paymentService.findAllPayments();
        return new ResponseEntity<>(allPayment, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<PaymentEntity> updatePayment(@PathVariable Long id, @RequestBody PaymentEntity paymentEntity) {
        return new ResponseEntity<>(paymentService.updatePayment(id, paymentEntity), HttpStatus.OK);
    }

}
