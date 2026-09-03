package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Model.Billing.billing;
import Dental.ICBT.Application.Service.SuperService;

import java.util.List;
import java.util.Map;

public interface Billing extends SuperService {

    List<billing> searchBill(String appointment);
    boolean completePayment(String appointment, String method, String status);
    Map<String, String> getBillDetails(String appointment);

}