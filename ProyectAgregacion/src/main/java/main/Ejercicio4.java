package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Constantes.Constantes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejercicio4 {


    //    [{$match: {
    //        'event-location': {
    //            $regex: 'Latina'
    //        }
    //    }}, {$project: {
    //        _id: 0,
    //                month: {
    //            $month: {
    //                $toDate: '$dtstart'
    //            }
    //        }
    //    }}, {$match: {
    //        $expr: {
    //            $eq: [
    //            '$month',
    //                    1
    //  ]
    //        }
    //    }}, {$count: 'cantidad'}]
    public static void main(String[] args) {

        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION);

        col.aggregate(Arrays.asList(new Document("$match",
                                new Document("event-location",
                                        new Document("$regex", "Latina"))),
                        new Document("$project",
                                new Document("_id", 0L)
                                        .append("month",
                                                new Document("$month",
                                                        new Document("$toDate", "$dtstart")))),
                        new Document("$match",
                                new Document("$expr",
                                        new Document("$eq", Arrays.asList("$month", 1L)))),
                        new Document("$count", "cantidad")))
                .into(new ArrayList<>())
                .forEach(System.out::println);


    }
}
