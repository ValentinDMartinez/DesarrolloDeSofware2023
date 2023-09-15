package Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio extends BaseEntidad {
    private String calle;
    private int numero;
    private String localidad;

    @ManyToOne()
    @JoinColumn(name = "Cliente_id")
    private Cliente cliente;
}
