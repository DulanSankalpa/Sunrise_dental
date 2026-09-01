package Dental.ICBT.Application.Model.Billing;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class billing {

    private int id;

    private String service;

    private Double amount;



    public billing(int id, String service, double amount) {

        this.id = id;
        this.service = service;
        this.amount = amount;

    }


}
