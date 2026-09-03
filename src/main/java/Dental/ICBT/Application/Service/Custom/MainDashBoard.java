package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.SuperService;

import java.sql.Statement;
import java.util.List;

public interface MainDashBoard extends SuperService {
    List<ViewData> getAll();
    int TotalAppoitment();
    int PendingPayment();
    int TodayAppoitmentCount();
}
