package Dental.ICBT.Application.Model.MainTable;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ViewData {
    private int id;
    private String Appoitment;
    private String pation;
    private String address;
    private String number;
    private String docter;
    private String treet;
    private String pay;
    private Date date;


    public ViewData(
            int id,
            String appoitment,
            String pation,
            String address,
            String number,
            Date date
    ) {
        this.id = id;
        this.Appoitment = appoitment;
        this.pation = pation;
        this.address = address;
        this.number = number;
        this.date = date;
    }

    public ViewData(int id, String appoitment, String pation, String address, String number, String docter, String treet,  String pay) {
        this.id = id;
        this.Appoitment = appoitment;
        this.pation = pation;
        this.address = address;
        this.number = number;
        this.docter = docter;
        this.treet = treet;
        this.pay = pay;
    }

    public ViewData(int id, String appoitment, String pation, String address, String number, String docter, String treet, Date date, String pay) {
        this.id = id;
        this.Appoitment = appoitment;
        this.pation = pation;
        this.address = address;
        this.number = number;
        this.docter = docter;
        this.treet = treet;
        this.date = date;
        this.pay = pay;
    }

    public ViewData(int id, String appoitment, String pation, String address, String number, String docter, String treet, Date date) {
        this.id = id;
        this.Appoitment = appoitment;
        this.pation = pation;
        this.address = address;
        this.number = number;
        this.docter = docter;
        this.treet = treet;
        this.date = date;
    }

}
