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
public class Rubro extends BaseEntidad {

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "IdRubro")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto prod){

        productos.add(prod);
    }

    public void mostrarProductos() {
        System.out.println("Los productos de este rubro son: ");
        for (Producto producto : productos) {
            System.out.println("Denominacion: " + producto.getDenominacion() + ", Receta: " + producto.getReceta()
                    + ", Unidad Medida: " + producto.getUnidadMedida() + ", Precio Compra: " + producto.getPrecioCompra()
                    + ", Precio Venta: " + producto.getPrecioVenta() + ", Stock Actual: " + producto.getStockActual()
                    + ", Stock Min: " + producto.getStockMinimo() + ", Tiempo Estimado Cocina: " + producto.getTiempoEstimadoCocina()
                    + ", Tipo: " + producto.getTipo());
        }
    }

}
