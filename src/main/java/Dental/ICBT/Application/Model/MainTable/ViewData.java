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
    private Date date;

    public ViewData(int id, String appoitment, String pation, String address, String number, Date date) {
        this.id = id;
        Appoitment = appoitment;
        this.pation = pation;
        this.address = address;
        this.number = number;
        this.date = date;
    }

}
