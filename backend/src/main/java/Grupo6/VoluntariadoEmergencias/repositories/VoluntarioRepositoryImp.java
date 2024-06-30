package Grupo6.VoluntariadoEmergencias.repositories;

import Grupo6.VoluntariadoEmergencias.entities.VoluntarioEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository{

    @Autowired
    private MongoClient mongoClient;

    @Override
    public VoluntarioEntity save(VoluntarioEntity voluntario) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");
        MongoCollection<Document> counterCollection = database.getCollection("Counter");

        Document filter = new Document("name", "voluntarios");
        Document counterDoc = counterCollection.find(filter).first();
        Integer counter = counterDoc.getInteger("counter");

        Document documento = new Document();
        documento.put("voluntarioId", counter);
        documento.put("nombre", voluntario.getNombre());
        documento.put("email", voluntario.getEmail());
        documento.put("password", voluntario.getPassword());
        documento.put("rut", voluntario.getRut());
        documento.put("ubicacion_voluntario", new ArrayList<Double>(List.of(-33.431254879234,-70.648930154879)));
        documento.put("habilidad", new ArrayList<String>());
        documento.put("emergencia", voluntario.getEmergencia());

        collection.insertOne(documento);

        counterCollection.updateOne(new Document("name","voluntarios"), Updates.inc("counter",1));

        ArrayList<Double> arrayLocation = new ArrayList<>(documento.getList("ubicacion_voluntario", Double.class));
        ArrayList<String> arraySkills = new ArrayList<>(documento.getList("habilidad", String.class));

        return new VoluntarioEntity(documento.getInteger("voluntarioId"), documento.getString("nombre"),
                documento.getString("email"),documento.getString("password"),documento.getString("rut"),
                arrayLocation,arraySkills,documento.getString("emergencia"));
    }

    private VoluntarioEntity convertDocumentToVoluntario(Document doc) {
        Integer idVoluntario = doc.getInteger("voluntarioId");
        String nombre = doc.getString("nombre");
        String email = doc.getString("email");
        String password = doc.getString("password");
        String rut = doc.getString("rut");
        ArrayList<Double> arrayLocation = new ArrayList<>(doc.getList("ubicacion_voluntario", Double.class));
        ArrayList<String> arraySkills = new ArrayList<>(doc.getList("habilidad", String.class));
        String emergencia = doc.getString("emergencia");
        return new VoluntarioEntity(idVoluntario,nombre,email,password,rut,arrayLocation,arraySkills,emergencia);
    }

    @Override
    public List<VoluntarioEntity> getAll() {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");

        List<VoluntarioEntity> voluntarios = new ArrayList<>();
        FindIterable<Document> output = collection.find();
        for (Document documento : output) {
            voluntarios.add(convertDocumentToVoluntario(documento));
        }
        return voluntarios;
    }

    public List<VoluntarioEntity> getById(Integer id) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");

        List<VoluntarioEntity> voluntarios = new ArrayList<>();
        Document filter = new Document("voluntarioId", Filters.eq(id));

        FindIterable<Document> output = collection.find(filter);
        for (Document documento : output) {
            voluntarios.add(convertDocumentToVoluntario(documento));
        }
        return voluntarios;
    }

    @Override
    public String update(VoluntarioEntity voluntario, Integer id) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");

        Document filter = new Document("voluntarioId", id);
        Document update = new Document("$set", new Document("nombre", voluntario.getNombre()));

        collection.updateOne(filter, update);

        return "Se actualizó el nombre del voluntario";
    }

    public void updateHabilidades(VoluntarioEntity voluntario, Integer id, List<Integer> skillList){
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");
        MongoCollection<Document> SkillCollection = database.getCollection("Habilidades");

        ArrayList<String> newSkills = new ArrayList<>();

        for (Integer skillId : skillList) {
            Document filter = new Document("SkillId",skillId);
            newSkills.add(SkillCollection.find(filter).first().getString("nombre"));
        }

        Document idFilter = new Document("voluntarioId", id);
        Document update = new Document("$set", new Document("habilidad", newSkills));

        collection.updateOne(idFilter,update);
    }

    @Override
    public void delete(Integer id) {

        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");

        Document filter = new Document("voluntarioId", Filters.eq(id));
        DeleteResult deleteResult = collection.deleteOne(filter);

        if (deleteResult.getDeletedCount() == 1) {
            System.out.println("Voluntario eliminado exitosamente.");
        } else {
            System.out.println("No se pudo encontrar el voluntario solicitado.");
        }
    }

    @Override
    public VoluntarioEntity getByEmail(String email) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");

        Document filter = new Document("email", email);

        FindIterable<Document> output = collection.find(filter);

        return convertDocumentToVoluntario(output.first());
    }

    public ArrayList<HashMap<String, Object>> getHabilidadesStats(List<Integer> skillList){
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Voluntarios");
        MongoCollection<Document> SkillCollection = database.getCollection("Habilidades");

        List<VoluntarioEntity> voluntarios = getAll();
        String skillName;
        ArrayList<HashMap<String,Object>> skillStats = new ArrayList<>();
        Integer TotalSkills = 0;

        for (Integer skillId : skillList) {
            Integer currentSkillSum = 0;
            Document filter = new Document("SkillId",skillId);
            skillName = SkillCollection.find(filter).first().getString("nombre");
            for(VoluntarioEntity voluntarioActual : voluntarios){
                if(voluntarioActual.getHabilidades().contains(skillName)){
                    currentSkillSum++;
                }
            }
            HashMap<String,Object> skillObject = new HashMap<>();
            skillObject.put("name",skillName);
            skillObject.put("count",currentSkillSum);
            TotalSkills += currentSkillSum;

            skillStats.add(skillObject);
        }

        HashMap<String,Object> skillObject = new HashMap<>();
        skillObject.put("name","Total de Habilidades Revisadas");
        skillObject.put("count",TotalSkills);
        skillStats.add(skillObject);

        return skillStats;
    }
}
