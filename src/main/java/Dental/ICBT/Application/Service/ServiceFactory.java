package Dental.ICBT.Application.Service;

import Dental.ICBT.Application.Service.Custom.impl.*;
import Dental.ICBT.Application.Util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance = new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case MAINDASH:return (T) new MainDashBoard_impl();
            case REGISTER:return (T) new RegisterAppoitment_impl();
            case SEARCH:return (T) new SearchAppoitment_impl();
            case BILLING:return (T) new Billing_impl();
            case EXTRAPAY:return (T) new ExtraPayment_impl();
            case REPORT:return (T) new Report_impl();
            case RESETPW:return (T) new ResetPassword_impl();
        }
        return null;
    }
}
