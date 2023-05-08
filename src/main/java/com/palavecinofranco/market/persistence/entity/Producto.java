package com.palavecinofranco.market.persistence.entity;

import jakarta.persistence.*;

@Entity @Table(name = "productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    private String nombre;
    @Column(name = "id_categoria")
    private Long idCategoria;
    @Column(name = "codigo_barras")
    private String codigoDeBarras;
    @Column(name = "precio_venta")
    private Double precioDeVenta;
    @Column(name = "cantidad_stock")
    private Integer cantidadDeStock;
    private Boolean estado;
    @ManyToOne @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public Double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(Double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public Integer getCantidadDeStock() {
        return cantidadDeStock;
    }

    public void setCantidadDeStock(Integer cantidadDeStock) {
        this.cantidadDeStock = cantidadDeStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
