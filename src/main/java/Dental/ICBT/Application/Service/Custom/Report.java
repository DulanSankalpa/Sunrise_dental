package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.SuperService;

import java.time.LocalDate;
import java.util.List;

public interface Report extends SuperService {
    List<ViewData> getAllData(LocalDate from,LocalDate to);
}
