/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Kevin
 */
import enums.Rol;
import java.time.LocalDateTime;

public class Usuario extends Base {
    private String nombre;
    private String email;
    private Rol rol;

    public Usuario() {
        super();
    }
private String apellido;
public String getApellido() {
    return apellido;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

    public Usuario(Long id, boolean eliminado, LocalDateTime createdAt, String nombre, String email, Rol rol) {
        super(id, eliminado, createdAt);
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}