package Dental.ICBT.Application.Service.Custom;

import Dental.ICBT.Application.Service.SuperService;

public interface ResetPassword extends SuperService {
    boolean ChangePassword(String name,String number,String newPassword);
}
