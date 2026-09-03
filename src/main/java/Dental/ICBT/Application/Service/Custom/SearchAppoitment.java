package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.SuperService;

import java.util.List;

public interface SearchAppoitment extends SuperService {
    List<ViewData> SearchAppoitmentNumber(String data);
    boolean delete(String id);
    boolean Update(String appoitment_id,String Pationt,String address,String number,String docter, String tread , String payment);
}
