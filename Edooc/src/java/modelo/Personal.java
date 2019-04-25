package modelo;

import java.sql.Date;
import lombok.Data;

@Data
public class Personal {
    private int idPer;
    private String nomPer;
    private String apePer;
    private String carPer;
    private String dniPer;
    private Date fdnPer;
    private String sexPer;    
    private String corPer;
    private String celPer;
}
