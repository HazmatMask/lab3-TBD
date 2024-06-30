package Grupo6.VoluntariadoEmergencias.entities;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;

public class VoluntarioEntity {

    private Integer idVoluntario;
    private String nombre;
    private String email;
    private String password;
    private String rut;
    private ArrayList<Double> ubicacion_voluntario;
    private ArrayList<String> habilidades;
    private String emergencia;

    public VoluntarioEntity(Integer id, String nombre, String email, String password, String rut, ArrayList<Double> ubicacion, ArrayList<String> habilidades, String emergencia) {
        this.idVoluntario = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rut = rut;
        this.ubicacion_voluntario = ubicacion;
        this.habilidades = habilidades;
        this.emergencia = "Disponible";
    }

    public Integer getId() {
        return idVoluntario;
    }

    public void setId(Integer id) {
        this.idVoluntario = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public ArrayList<Double> getUbicacion_voluntario() {
        return ubicacion_voluntario;
    }

    public void setLongitud_voluntario(ArrayList<Double> ubicacion) {
        this.ubicacion_voluntario = ubicacion;
    }

    public ArrayList<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    public String getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(String emergencia) {
        this.emergencia = emergencia;
    }
}
