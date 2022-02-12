package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.*;

public class Ejercicio2 {
    //
    //[{$match: {
    //        'event-location': {
    //            $regex: 'Latina'
    //        }
    //    }}, {$project: {
    //        _id: 0,
    //                title: 1,
    //                '@type': 1,
    //                'event-location': 1
    //    }}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION);

        col.aggregate(List.of(
                match(regex("event-location", "Latina")),
                project(fields(
                        include("title", "@type", "event-location"),
                        exclude("_id"))))
        ).into(new ArrayList<>()).forEach(System.out::println);
    }
}
