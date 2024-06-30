<template>
  <div>
    <Navbar />
    <div class="skills-main">
      <div class="skills-content">
          <h1 class="main-title" v-if="!outputHabilidades.length">
            Selecciona habilidades a verificar.
          </h1>
          <h1 class="main-title" v-if="outputHabilidades.length">
            Conteo de Habilidades
          </h1>
        <form @submit.prevent="registrarVoluntario" v-if="!outputHabilidades.length">
          <div class="selector-multiple">
            <label v-for="habilidad in habilidades" :key="habilidad.id" class="habilidad-label">
              <input type="checkbox" :value="habilidad.id" v-model="voluntario.habilidades">
              {{ habilidad.nombre }}
            </label>
          </div>
          <div class="btn-container">
            <button type="submit" class="btn-registrar">Registrar</button>
          </div>
        </form>
        <div class="statsHabilidades" v-if="outputHabilidades.length">
          <ul>
              <table class = "tablaHabilidades">
                <thead>
                  <tr>
                    <th>Habilidad</th>
                    <th>Cantidad<br>de Voluntarios</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="skill in outputHabilidades" :key="skill.name">
                    <td>{{ skill.name }}</td>
                    <td>{{ skill.count }}</td>
                  </tr>
                </tbody>
              </table>
              <tbody>
                <tr v-for="skill in resultadoHabilidades" :key="skill.name">
                  <td>{{ skill.name }}</td>
                  <td>{{ skill.value }}</td>
                </tr>
              </tbody>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import axios from 'axios';
import { obtenerHabilidades } from './apiService';
import Navbar from '@/components/Navbar.vue';
import { userJwt } from '@/store/store';

export default {
  components: {
    Navbar
  },
  data() {
    return {
      voluntario: {
        email: '',
        habilidades: []
      },
      habilidades: [],
      outputHabilidades: [],
      mensajeExito: ''
    };
  },
  created() {
    this.fetchHabilidades();
  },
  methods: {
    fetchHabilidades() {
      obtenerHabilidades()
        .then(response => {
          this.habilidades = response.data;
        })
        .catch(error => {
          console.error('Hubo un error al obtener las habilidades:', error);
        });
    },
    registrarVoluntario() {
      const body = {
        token: userJwt.value,
        ability_ids: this.voluntario.habilidades
      };

      axios.post("http://localhost:8080/voluntarios/estadisticasHabilidades", body)
        .then(response => {
          console.table(response.data);
          this.outputHabilidades = response.data;
        })
        .catch(error => {
          this.mensajeExito = "El correo electrónico es incorrecto";
          console.error('Hubo un error al registrar las habilidades:', error);
        });
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Lato:wght@300;400&display=swap');

.skills-main {
  background-color: #f0f9ff;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  min-height: 100vh;
}

h1 {
  font-weight: bold;
}

.skills-content {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 80%;
  max-width: 800px;
  justify-content: center;
  display: flex;
  flex-direction: column;
}

.main-title {
  color: #101935;
  font-family: 'Roboto', sans-serif;
  text-align: center;
  margin-bottom: 20px;
  font-size: 2rem;
}

.sub-title {
  color: #101935;
  font-family: 'Roboto', sans-serif;
  text-align: center;
  margin-bottom: 20px;
  font-size: 1.5rem;
}

.outputHabilidades {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.selector-multiple {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.habilidad-label {
  display: flex;
  align-items: center;
  margin: 0.5rem 0;
  font-size: 1.2rem;
}

.habilidad-label input[type="checkbox"] {
  width: 20px;
  height: 20px;
  margin-right: 10px;
  cursor: pointer;
}

.habilidad-label input[type="checkbox"]:checked {
  background-color: #9ad4d6;
  border-color: #9ad4d6;
}

input[type="email"] {
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  border-radius: 5px;
  border: 1px solid #cccccc;
}

input[type="email"]:focus {
  outline: none;
  border-color: #9ad4d6;
}

label {
  color: black;
}

.btn-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.btn-registrar {
  background-color: #9ad4d6;
  color: #101935;
  padding: 12px 24px;
  border-radius: 10px;
  font-size: 1.2rem;
  text-decoration: none;
  transition: background-color 0.3s, transform 0.2s;
  cursor: pointer;
}

.btn-registrar:hover {
  background-color: #564787;
  color: #f2fdff;
  transform: translateY(-2px);
}

.statsHabilidades {
  justify-content: center;
}

.tablaHabilidades {
  border-collapse: collapse;
  margin: 0 auto;
  width: 100%;
  text-align: center;
}

.statsHabilidades ul{
  padding: 0px;
}

.tablaHabilidades th,
.tablaHabilidades td {
  border: 1px solid #ddd;
  padding: 8px;
  font-size: 17px;
}

.tablaHabilidades th:nth-child(1),
.tablaHabilidades td:nth-child(1){
  width: 70%;
}

.tablaHabilidades th:nth-child(2),
.tablaHabilidades td:nth-child(2){
  width: 30%;
}

.tablaHabilidades tr:nth-last-child(1) td:nth-child(1){
  background-color: #f2f2f2;
}

.tablaHabilidades th {
  background-color: #f2f2f2;
}
</style>
