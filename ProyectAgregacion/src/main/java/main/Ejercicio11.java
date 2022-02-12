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
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.descending;

public class Ejercicio11 {


    //    [{$unwind: {
    //        path: '$purchases'
    //    }}, {$group: {
    //        _id: '$_id',
    //                Npurchases: {
    //            $sum: 1
    //        }
    //    }}, {$sort: {
    //        Nourchases: -1
    //    }}, {$limit: 1}, {$project: {
    //        _id: 1
    //    }}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_CUSTOMER);

        col.aggregate(List.of(
                unwind("$purchases"),
                group("$_id", sum("Npurchases", 1)),
                sort(descending("Npurchases")),
                limit(1),
                project(include("_id"))
        )).into(new ArrayList<>()).forEach(System.out::println);

    }
}
