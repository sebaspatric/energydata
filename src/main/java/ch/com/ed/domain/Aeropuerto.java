package ch.com.ed.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "aeropuerto")
public class Aeropuerto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "codigo")
    private String codigo;
    
    @NotEmpty
    @Column(name = "AEROPUERTO")
    private String AEROPUERTO;
    
    @NotEmpty
    @Column(name = "PAX_TOTAL")
    private double PAX_TOTAL;
    
    @NotEmpty
    @Column(name = "CARGA_TOTA")
    private double CARGA_TOTA;
    
    @NotEmpty
    @Column(name = "longitud")
    private double longitud;
    
    @NotEmpty
    @Column(name = "latitud")
    private double latitud;
    
 
}
