package Grupo6.VoluntariadoEmergencias.repositories;

import Grupo6.VoluntariadoEmergencias.entities.HabilidadEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HabilidadRepositoryImp implements HabilidadRepository {

    @Autowired
    private MongoClient mongoClient;

    @Override
    public HabilidadEntity save(HabilidadEntity habilidad) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Habilidades");

        MongoCollection<Document> counterCollection = database.getCollection("Counter");
        Document filter = new Document("name", Filters.eq("habilidades"));

        FindIterable<Document> output = counterCollection.find(filter);
        Integer newId = output.first().getInteger("counter");

        Document documento = new Document();
        documento.put("nombre", habilidad.getNombre());
        documento.put("descripcion", habilidad.getDescripcion());
        documento.put("SkillId", habilidad.getId());

        collection.insertOne(documento);

        counterCollection.updateOne(new Document("name","habilidades"), Updates.inc("counter",1));

        return new HabilidadEntity(documento.getInteger("SkillId"), documento.getString("nombre"), documento.getString("descripcion"));
    }

    private HabilidadEntity convertDocumentToHabilidad(Document doc) {
        Integer SkillId = doc.getInteger("SkillId");
        String nombre = doc.getString("nombre");
        String descripcion = doc.getString("descripcion");
        return new HabilidadEntity(SkillId, nombre, descripcion);
    }

    @Override
    public List<HabilidadEntity> getAll() {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Habilidades");

        List<HabilidadEntity> habilidades = new ArrayList<>();
        FindIterable<Document> output = collection.find();
        for (Document documento : output) {
            habilidades.add(convertDocumentToHabilidad(documento));
        }
        return habilidades;
    }

    @Override
    public List<HabilidadEntity> getById(Integer id) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Habilidades");

        List<HabilidadEntity> habilidades = new ArrayList<>();
        Document filter = new Document("skillId", Filters.eq(id));

        FindIterable<Document> output = collection.find(filter);
        for (Document documento : output) {
            habilidades.add(convertDocumentToHabilidad(documento));
        }
        return habilidades;
    }

    @Override
    public String updateNombreHabilidad(HabilidadEntity habilidad, Integer id) {
        if (id == null || habilidad == null || habilidad.getNombre() == null) {
            return "Invalid arguments. Update failed.";
        }

        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Habilidades");

        Document filter = new Document("skillId", Filters.eq(id));
        Document update = new Document("$set", new Document("nombre", habilidad.getNombre()));

        UpdateResult updateResult = collection.updateOne(filter, update);

        if (updateResult.getModifiedCount() == 1) {
            return "Se actualizó el nombre de la habilidad";
        } else {
            return "No se pudo actualizar el nombre de la habilidad";
        }
    }

    public void delete(Integer id) {

        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Habilidades");

        Document filter = new Document("skillId", Filters.eq(id));
        DeleteResult deleteResult = collection.deleteOne(filter);

        if (deleteResult.getDeletedCount() == 1) {
            System.out.println("Document deleted successfully!");
        } else {
            System.out.println("No document matched the filter.");
        }
    }

    @Override
    public List<HabilidadEntity> findAll() {
        return getAll();
    }

    public List<HabilidadEntity> getByEmailVoluntario(String email) {
        MongoDatabase database = mongoClient.getDatabase("Lab3TBD");
        MongoCollection<Document> collection = database.getCollection("Habilidades");

        List<HabilidadEntity> habilidades = new ArrayList<>();
        Document filter = new Document("email", Filters.eq(email));

        FindIterable<Document> output = collection.find(filter);
        for (Document documento : output) {
            habilidades.add(convertDocumentToHabilidad(documento));
        }
        return habilidades;
    }
}
