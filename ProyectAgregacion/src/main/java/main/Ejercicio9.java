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
import static com.mongodb.client.model.Aggregates.unwind;

public class Ejercicio9 {

    //[{
    //        $unwind: {
    //            path: '$purchases'
    //        }
    //    }, {
    //        $group: {
    //            _id: '$name',
    //                    Ncompracas: {
    //                $sum: 1
    //            }
    //        }
    //    }]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_CUSTOMER);

        col.aggregate(List.of(
                unwind("$purchases"),
                group("$name", sum("Ncompras", 1))
        )).into(new ArrayList<>()).forEach(System.out::println);
    }
}
