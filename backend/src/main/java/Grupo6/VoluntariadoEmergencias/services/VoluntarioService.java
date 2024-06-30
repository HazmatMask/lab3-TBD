package Grupo6.VoluntariadoEmergencias.services;

import Grupo6.VoluntariadoEmergencias.entities.Forms.AbilitiesForm;
import Grupo6.VoluntariadoEmergencias.entities.Forms.LoginForm;
import Grupo6.VoluntariadoEmergencias.Responses.Login;
import Grupo6.VoluntariadoEmergencias.Utils.Encrypter;
import Grupo6.VoluntariadoEmergencias.entities.HabilidadEntity;
import Grupo6.VoluntariadoEmergencias.entities.VoluntarioEntity;
import Grupo6.VoluntariadoEmergencias.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class VoluntarioService {

    @Autowired
    private final VoluntarioRepository voluntarioRepository;

    @Autowired
    private JWTMiddlewareRepositoryImp JWT;

    @Autowired
    private HabilidadRepository habilidadRepository;


    VoluntarioService(VoluntarioRepository voluntarioRepository){
        this.voluntarioRepository = voluntarioRepository;
    }

    public VoluntarioEntity crearVoluntario( VoluntarioEntity voluntario){
        VoluntarioEntity voluntario1 = voluntarioRepository.save(voluntario);
        return voluntario1;
    }
    // get R

    public List<VoluntarioEntity> getAllVoluntarios(){
        return voluntarioRepository.getAll();
    }
    //get by

    public List<VoluntarioEntity> getVoluntarioById(Integer id){
        return voluntarioRepository.getById(id);
    }

    // actualizar U

    public String updateVoluntario(VoluntarioEntity voluntario, Integer id){
        String retorno = voluntarioRepository.update(voluntario,id);
        return retorno;
    }

    // borrar D

    public void deleteVoluntario(Integer id){
        voluntarioRepository.delete(id);
    }


    public List<HabilidadEntity> getAllHabilidades() {
        return habilidadRepository.findAll();
    }

    public Login login(LoginForm form) {
        VoluntarioEntity vol = voluntarioRepository.getByEmail(form.getEmail());
        if (vol == null) {
            return new Login(false, null);
        }
        /*if (!form.getPassword().equals(Encrypter.decrypt(vol.getPassword(),vol.getEmail()))){
            return new Login(false, null);
        }*/
        if (!form.getPassword().equals(vol.getPassword())){
            return new Login(false, null);
        }

        String jwt = JWT.generateToken(form);
        return new Login(true, jwt);
    }

    public LoginForm testJWT(String token) {
        if (JWT.validateToken(token)) {
            return JWT.decodeJWT(token);
        }
        return null;
    }

    public String agregarHabilidades(AbilitiesForm form) {
        if (!JWT.validateToken(form.getToken()))
            return null;

        LoginForm user = JWT.decodeJWT(form.getToken());

        VoluntarioEntity vol = voluntarioRepository.getByEmail(user.getEmail());
        if (vol == null) {
            return "El usuario no existe";
        }

        voluntarioRepository.updateHabilidades(vol, vol.getId(), form.getAbility_ids());

        return "Las habilidades se agregaron exitosamente";
    }

    public ArrayList<HashMap<String, Object>> getSkillStats(AbilitiesForm form){
        if(!JWT.validateToken(form.getToken()))
            return null;

        LoginForm user = JWT.decodeJWT(form.getToken());

        VoluntarioEntity vol = voluntarioRepository.getByEmail(user.getEmail());
        if (vol == null) {
            return null;
        }
        return voluntarioRepository.getHabilidadesStats(form.getAbility_ids());
    }
}
