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
public class DetallePedido extends BaseEntidad {
    private int cantidad;
    private double subtotal;


    @ManyToOne()
    private Producto producto;

    @ManyToOne()
    private Pedido pedido;
}
