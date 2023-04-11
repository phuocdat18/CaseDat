package product.services;

import product.model.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getPayment();
    void add(List <Payment> list);
    void recheck(int id, Payment updatePayment);

}
