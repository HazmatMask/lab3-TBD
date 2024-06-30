package Grupo6.VoluntariadoEmergencias.entities.Forms;

import java.util.List;

public class AbilitiesForm {
    private String token;
    private List<Integer> ability_ids;

    public AbilitiesForm(String token, List<Integer> ability_ids) {
        this.token = token;
        this.ability_ids = ability_ids;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getAbility_ids() {
        return ability_ids;
    }

    public void setAbility_ids(List<Integer> ability_ids) {
        this.ability_ids = ability_ids;
    }
}
