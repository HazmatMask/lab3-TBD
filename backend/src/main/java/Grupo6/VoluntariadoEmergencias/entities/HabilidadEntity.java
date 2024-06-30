package Grupo6.VoluntariadoEmergencias.entities;

public class HabilidadEntity {

    private Integer habilidadId;
    private String nombre;
    private String descripcion;

    public HabilidadEntity(Integer id, String nombre, String descripcion) {
        this.habilidadId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return habilidadId;
    }

    public void setId(Integer habilidadId) {
        this.habilidadId = habilidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
