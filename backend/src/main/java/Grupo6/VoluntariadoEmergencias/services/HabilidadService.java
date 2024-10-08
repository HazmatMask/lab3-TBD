package Grupo6.VoluntariadoEmergencias.services;

import Grupo6.VoluntariadoEmergencias.entities.Forms.JWTForm;
import Grupo6.VoluntariadoEmergencias.entities.Forms.LoginForm;
import Grupo6.VoluntariadoEmergencias.entities.HabilidadEntity;
import Grupo6.VoluntariadoEmergencias.repositories.HabilidadRepository;
import Grupo6.VoluntariadoEmergencias.repositories.JWTMiddlewareRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;

    @Autowired
    private JWTMiddlewareRepositoryImp JWT;

    HabilidadService(HabilidadRepository habilidadRepository){
        this.habilidadRepository = habilidadRepository;
    }
    // crear C

    public HabilidadEntity crearHabilidad( HabilidadEntity habilidad){
        HabilidadEntity habilidad1 = habilidadRepository.save(habilidad);
        return habilidad1;
    }
    // get R

    public List<HabilidadEntity> getAllHabilidades(){
        return habilidadRepository.getAll();
    }
    //get by

    public List<HabilidadEntity> getHabilidadById(Integer id){
        return habilidadRepository.getById(id);
    }

    public String updateNombreHabilidad( HabilidadEntity habilidad, Integer id){
        String retorno = habilidadRepository.updateNombreHabilidad(habilidad,id);
        return retorno;
    }


    public void deleteHabilidad(Integer id){
        habilidadRepository.delete(id);
    }

    public List<HabilidadEntity> getHabilidadByVoluntario(JWTForm form) {
        if (!JWT.validateToken(form.getToken())) {
            return null;
        }
        LoginForm user = JWT.decodeJWT(form.getToken());

        return habilidadRepository.getByEmailVoluntario(user.getEmail());
    }

}
