package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.SuperService;

import java.util.List;

public interface PaymentExtra extends SuperService {
    boolean ExtraPayment(String appointment,String service,double charge);
    List<ViewData> getAllList();
}
