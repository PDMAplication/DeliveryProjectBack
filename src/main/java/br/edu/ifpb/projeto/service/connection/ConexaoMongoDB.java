package br.edu.ifpb.projeto.service.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConexaoMongoDB {
    public static void main(String[] args) {
        MongoClient cliente = MongoClients.create("mongodb+srv://projetodelivery:projetodelivery2022@delivery.ko3lm97.mongodb.net/?retryWrites=true&w=majority");

        MongoDatabase db = cliente.getDatabase("delivery");

        MongoCollection collection = db.getCollection("usuario");

        Document sampleDoc = new Document("_id", "1").append("name", "Teste");

        collection.insertOne(sampleDoc);
       // collection.find().first();
    }
}
