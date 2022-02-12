package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.size;
import static com.mongodb.client.model.Projections.exclude;

public class Ejercicio14 {

    //    [{$lookup: {
//        from: 'User',
//                localField: '_id',
//                foreignField: '_id',
//                as: 'have'
//    }}, {$match: {
//        have: {
//            $size: 0
//        }
//    }}, {$project: {
//        have: 0
//    }}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_CUSTOMER);

        col.aggregate(List.of(
                lookup("User", "_id", "_id", "have"),
                match(size("have", 0)),
                project(exclude("have"))
        )).into(new ArrayList<>()).forEach(System.out::println);

    }
}
