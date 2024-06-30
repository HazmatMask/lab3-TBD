package Grupo6.VoluntariadoEmergencias.repositories;

import Grupo6.VoluntariadoEmergencias.entities.VoluntarioEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface VoluntarioRepository {

    List<VoluntarioEntity> getAll();
    List<VoluntarioEntity> getById(Integer id);
    VoluntarioEntity save(VoluntarioEntity voluntario);
    VoluntarioEntity getByEmail(String email);
    String update(VoluntarioEntity voluntario,Integer id);
    void updateHabilidades(VoluntarioEntity voluntario, Integer id, List<Integer> skillList);
    void delete(Integer id);
    public ArrayList<HashMap<String, Object>> getHabilidadesStats(List<Integer> skillList);
}
