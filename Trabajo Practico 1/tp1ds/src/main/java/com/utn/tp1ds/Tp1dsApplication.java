package com.utn.tp1ds;

import Entidades.*;
import Enums.Estado;
import Enums.Formapago;
import Enums.Tipo;
import Enums.Tipoenvio;
import Repositorio.ClienteRepository;
import Repositorio.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;



@SpringBootApplication
public class Tp1dsApplication {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;


	public static void main(String[] args) {
		SpringApplication.run(Tp1dsApplication.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			System.out.println("----------------ESTOY----FUNCIONANDO---------------------");
			//crear instancia rubro
			Rubro rubro1 = Rubro.builder()
					.denominacion("Pizza")
					.build();
			//crear instancias de productos

			Producto producto1 = Producto.builder()
					.tiempoEstimadoCocina(30)
					.denominacion("Pizza muzzarella")
					.precioVenta(2500)
					.precioCompra(1000)
					.stockActual(100)
					.stockMinimo(10)
					.unidadMedida("unidad1")
					.receta("receta1")
					.Tipo(Tipo.INSUMO)
					.build();
			Producto producto2 = Producto.builder()
					.tiempoEstimadoCocina(35)
					.denominacion("Pizza especial")
					.precioVenta(3000)
					.precioCompra(1500)
					.stockActual(80)
					.stockMinimo(8)
					.unidadMedida("unidad2")
					.receta("receta2")
					.Tipo(Tipo.INSUMO)
					.build();
			//agregar productos al rubro
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);


			//Crear instancia Cliente
			Cliente cliente1 = Cliente.builder()
					.nombre("Valentin")
					.apellido("Messi")
					.email("@email")
					.telefono("telefono1")
					.build();
			//Crear instancia domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Lavalle")
					.numero(6538)
					.localidad("Maipu")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("San Martin")
					.numero(465)
					.localidad("Ciudad")
					.build();
			//agregar domicilios a cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			//Crear Instancia Detalle Pedido
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(6000)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2500)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(9000)
					.build();

			//configuracion fecha
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString = "2023-11-27";

			Date fecha = formatoFecha.parse(fechaString);
			//Creamos Instancia Pedido
			Pedido pedido1 = Pedido.builder()
					.fecha((java.sql.Date) fecha)
					.total(6000)
					.Estado(Estado.ENTREGADO)
					.Tipoenvio(Tipoenvio.DELIVERY)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha((java.sql.Date) fecha)
					.total(2500)
					.Estado(Estado.ENTREGADO)
					.Tipoenvio(Tipoenvio.DELIVERY)
					.build();
			//Crear instancias de factura
			Factura.FacturaBuilder builder = Factura.builder();
			builder.fecha((java.sql.Date) fecha);
			builder.total(5400);
			builder.numero(1);
			builder.descuento(600);
			builder.formapago(Formapago.EFECTIVO);
			Factura factura1 = builder
					.build();
			Factura factura2 = Factura.builder()
					.fecha((java.sql.Date) fecha)
					.total(2100)
					.numero(2)
					.descuento(400)
					.formapago(Formapago.EFECTIVO)
					.build();
			//Agregar detalles al pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
			//agregar pedidos al cliente
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);
			//vincular el detalle pedido con el producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			//vincular factura con pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			//guardar cliente y rubro
			clienteRepository.save(cliente1);
			rubroRepository.save(rubro1);

			//recuperar objeto rubro desde la base de datos
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null) {
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}


			//recuperar Cliente desde la base de Datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}
		};
	}
}
