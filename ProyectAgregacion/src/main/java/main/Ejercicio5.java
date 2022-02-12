package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;

public class Ejercicio5 {

    //    [{$group: {
    //        _id: '$event-location',
    //                totalEvent: {
    //            $sum: 1
    //        }
    //    }}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION);


        col.aggregate(List.of(
                group("$event-location", sum("totalEvents", 1))
        )).into(new ArrayList<>()).forEach(System.out::println);

    }
}
