package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UnwindOptions;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.gt;

public class Ejercicio13 {

    //[{$unwind: {
    //        path: '$purchases',
    //                preserveNullAndEmptyArrays: false
    //    }}, {$unwind: {
    //        path: '$purchases.review'
    //    }}, {$group: {
    //        _id: '$name',
    //                number: {
    //            $sum: 1
    //        }
    //    }}, {$match: {
    //        number: {
    //            $gt: 3
    //        }
    //    }}]

    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_CUSTOMER);

        col.aggregate(List.of(
                unwind("$purchases", new UnwindOptions().preserveNullAndEmptyArrays(false)),
                unwind("$purchases.review"),
                group("$name", sum("number", 1)),
                match(gt("number", 3))
        )).into(new ArrayList<>()).forEach(System.out::println);
    }
}
