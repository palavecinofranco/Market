package com.palavecinofranco.market.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "compras")
public class Compra {
    @Id @Column(name = "id_compra") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;
    @Column(name = "id_cliente")
    private Long idCliente;
    private LocalDateTime fecha;
    @Column(name = "medio_pago")
    private Character medioDePago;
    private String comentario;
    private Character estado;
    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL}) @NotFound(action = NotFoundAction.IGNORE)
    private List<CompraProducto> productos;

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Character getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(Character medioDePago) {
        this.medioDePago = medioDePago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<CompraProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CompraProducto> productos) {
        this.productos = productos;
    }
}
