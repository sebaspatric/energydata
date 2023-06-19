package ch.com.ed.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "vuelo")
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Long idVuelo;

    @NotEmpty
    @Column(name = "ruta_vuelo")
    private String rutaVuelo;

    @ManyToOne
    @JoinColumn(name = "origen_codigo")
    private Aeropuerto origen;

    @ManyToOne
    @JoinColumn(name = "destino_codigo")
    private Aeropuerto destino;

    @NotEmpty
    @Column(name = "carga_pasajeros")
    private double cargaPasajeros;

    @NotEmpty
    @Column(name = "carga")
    private double carga;

    @NotEmpty
    @Column(name = "consumo_ultimos_100")
    private double consumoUltimos100;

    @Column(name = "datos_meteorologicos")
    private String datosMeteorologicos;
}
