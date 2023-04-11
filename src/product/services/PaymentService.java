package product.services;

import product.model.Payment;
import product.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentService extends ProductService implements IPaymentService {
    List<Payment> paymentList = new ArrayList<>();
    public static String path = "src/data/payment.csv";

    @Override
    public List<Payment> getPayment() {
        List<Payment> newPaymentList = new ArrayList<>();
        List<String> reads = CSVUtils.read(path);
        for (String read : reads) {
            newPaymentList.add(new Payment(read));
        }
        return paymentList = newPaymentList;
    }

    @Override
    public void  add(List <Payment> list) {
        getPayment();
        for (Payment p:list) {
            paymentList.add(p);
        }
        CSVUtils.write(path, paymentList);
    }

    @Override
    public void recheck(int id, Payment updatePayment) {
        getPayment();
        for (int i = 0; i < paymentList.size(); i++) {
            if (paymentList.get(i).getId() == id) {
                paymentList.set(i, updatePayment);
                CSVUtils.write(path, paymentList);
            }
        }
    }



    public long showTotal (){
        getPayment();
        long a = 0;
        for (Payment p:paymentList) {
            long x = p.getRevenue();
            a+=x;
        }
        return a;
    }

}
