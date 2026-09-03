package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Model.register.Register;
import Dental.ICBT.Application.Service.SuperService;

public interface RegisterAppoitment extends SuperService {
    boolean saveAppoitment(Register register);

}
