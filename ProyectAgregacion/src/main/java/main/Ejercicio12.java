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

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.gt;

public class Ejercicio12 {

    //    [{$unwind: {
    //        path: '$reviews',
    //                preserveNullAndEmptyArrays: false
    //    }}, {$group: {
    //        _id: '$_id',
    //                media: {
    //            $avg: '$reviews.raiting'
    //        }
    //    }}, {$match: {
    //        media: {
    //            $gt: 4
    //        }
    //    }}, {$count: 'Numero de items'}]

    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_ITEM);

        col.aggregate(List.of(
                unwind("$reviews", new UnwindOptions().preserveNullAndEmptyArrays(false)),
                group("$_id", avg("media", "$reviews.raiting")),
                match(gt("media", 4)),
                count()
        )).into(new ArrayList<>()).forEach(System.out::println);
    }
}
