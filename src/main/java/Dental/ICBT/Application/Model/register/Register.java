package Dental.ICBT.Application.Model.register;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Register {
    private String id;
    private String appoitmenrt;
    private String pation;
    private String address;
    private String number;
    private String dentis;
    private String type;

    public Register(String appoitmenrt, String pation, String address, String number,String dentis, String type) {
        this.appoitmenrt = appoitmenrt;
        this.pation = pation;
        this.address = address;
        this.number = number;
        this.dentis = dentis;
        this.type = type;
    }
}
