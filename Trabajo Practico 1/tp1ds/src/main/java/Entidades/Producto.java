package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto extends BaseEntidad {

    private Enums.Tipo Tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<DetallePedido> detallePedidos= new ArrayList<>();


}
