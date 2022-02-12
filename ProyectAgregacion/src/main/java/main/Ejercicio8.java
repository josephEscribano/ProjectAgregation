package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Projections.exclude;

public class Ejercicio8 {

    //    [{$group: {
    //        _id: '$event-location',
    //                totalEvent: {
    //            $sum: 1
    //        }
    //    }}, {$group: {
    //        _id: 'result',
    //                media: {
    //            $avg: '$totalEvent'
    //        }
    //    }}, {$project: {
    //        _id: 0
    //    }}]


    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION);

        col.aggregate(List.of(
                group("$event-location", sum("totalEvent", 1)),
                group("result", avg("media", "$totalEvent")),
                project(exclude("_id"))
        )).into(new ArrayList<>()).stream().forEach(System.out::println);
    }
}
