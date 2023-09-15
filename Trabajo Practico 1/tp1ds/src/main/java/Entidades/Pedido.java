package Entidades;

import Enums.Estado;
import Enums.Tipoenvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends BaseEntidad {

    private Estado Estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Tipoenvio Tipoenvio;
    private double total;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "DetallePedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();



    public void agregarDetallePedido(DetallePedido detallePedidoed){

        detallePedidos.add(detallePedidoed);
    }
}
