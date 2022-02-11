package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static java.util.Arrays.asList;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class Ejercicio7 {

//    [{$group: {
//        _id: '$event-location',
//                totalEvent: {
//            $sum: 1
//        }
//    }}, {$sort: {
//        totalEvent: -1
//    }}, {$limit: 1}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION);


        col.aggregate(List.of(
                group("$event-location",sum("totalEvent",1)),
                sort(descending("totalEvent")),
                limit(1)
        )).into(new ArrayList<>()).stream().forEach(System.out::println);

    }
}
