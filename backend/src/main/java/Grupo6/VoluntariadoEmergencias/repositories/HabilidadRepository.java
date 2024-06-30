package Grupo6.VoluntariadoEmergencias.repositories;

import Grupo6.VoluntariadoEmergencias.entities.HabilidadEntity;

import java.util.List;

public interface HabilidadRepository {
    List<HabilidadEntity> getAll();
    List<HabilidadEntity> getById(Integer id);
    HabilidadEntity save(HabilidadEntity habilidad);
    String updateNombreHabilidad(HabilidadEntity habilidad,Integer id);
    List<HabilidadEntity> getByEmailVoluntario(String email);
    void delete(Integer id);

    List<HabilidadEntity> findAll();
}
