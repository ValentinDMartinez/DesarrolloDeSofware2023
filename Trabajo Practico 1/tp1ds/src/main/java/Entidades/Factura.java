package Entidades;

import Enums.Formapago;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Factura extends BaseEntidad {

    private int numero;
    private Date fecha;
    private double descuento;
    private Formapago formapago;
    private int total;

    @OneToOne(mappedBy = "factura")
    private Pedido pedido;

}
