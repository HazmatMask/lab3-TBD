package Grupo6.VoluntariadoEmergencias.controllers;

import Grupo6.VoluntariadoEmergencias.entities.Forms.AbilitiesForm;
import Grupo6.VoluntariadoEmergencias.entities.Forms.JWTForm;
import Grupo6.VoluntariadoEmergencias.entities.Forms.LoginForm;
import Grupo6.VoluntariadoEmergencias.Responses.Login;
import Grupo6.VoluntariadoEmergencias.entities.HabilidadEntity;
import Grupo6.VoluntariadoEmergencias.entities.VoluntarioEntity;

import Grupo6.VoluntariadoEmergencias.services.HabilidadService;
import Grupo6.VoluntariadoEmergencias.services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    @Autowired
    private HabilidadService habilidadService;

    VoluntarioController(VoluntarioService voluntarioService){
        this.voluntarioService = voluntarioService;
    }
    // crear C
    @PostMapping("/voluntarios/save")
    @ResponseBody
    public VoluntarioEntity crearVoluntario(@RequestBody VoluntarioEntity voluntario){
        VoluntarioEntity voluntario1 = voluntarioService.crearVoluntario(voluntario);
        return voluntario1;
    }
    // get R
    @GetMapping("/voluntarios/getAll")
    @ResponseBody
    public List<VoluntarioEntity> getAllVoluntarios(){
        return voluntarioService.getAllVoluntarios();
    }
    //get by
    @GetMapping("/voluntarios/getById/{id}")
    @ResponseBody
    public List<VoluntarioEntity> getVoluntarioById(@PathVariable Integer id){
        return voluntarioService.getVoluntarioById(id);
    }

    // actualizar U
    @PutMapping("/voluntarios/update/{id}")
    @ResponseBody
    public String updateVoluntario(@RequestBody VoluntarioEntity voluntario, @PathVariable Integer id){
        String retorno = voluntarioService.updateVoluntario(voluntario,id);
        return retorno;
    }

    // borrar D
    @DeleteMapping("/voluntarios/delete/{id}")
    public void deleteVoluntario(@PathVariable Integer id){
        voluntarioService.deleteVoluntario(id);
    }

    @GetMapping("/voluntarios/habilidades")
    @ResponseBody
    public List<HabilidadEntity> getHabilidades() {
        return voluntarioService.getAllHabilidades();
    }

    @PostMapping("/voluntarios/login")
    public Login login(@RequestBody LoginForm form){
        return voluntarioService.login(form);
    }

    @PostMapping("/voluntarios/pruebajwt")
    public LoginForm testJWT(@RequestBody JWTForm form){
        return voluntarioService.testJWT(form.getToken());
    }

    @PostMapping("/voluntarios/habilidades")
    public List<HabilidadEntity> getHabilidades(@RequestBody JWTForm form){
        return voluntarioService.getAllHabilidades();
    }

    @PostMapping("/voluntarios/agregarHabilidades")
    public String agregarHabilidades(@RequestBody AbilitiesForm form){
        return voluntarioService.agregarHabilidades(form);
    }

    @PostMapping("/voluntarios/estadisticasHabilidades")
    public ArrayList<HashMap<String, Object>> estadisticasHabilidades(@RequestBody AbilitiesForm form){
        ArrayList<HashMap<String, Object>> output = voluntarioService.getSkillStats(form);

        return output;
    }

}
