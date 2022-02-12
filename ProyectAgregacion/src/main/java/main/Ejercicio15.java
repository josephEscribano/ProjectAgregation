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

public class Ejercicio15 {

    //    [{$unwind: {
//        path: '$purchases'
//    }}, {$lookup: {
//        from: 'Items',
//                localField: 'purchases.id_purchase',
//                foreignField: 'purchases.id_purchase',
//                as: 'gastos'
//    }}, {$unwind: {
//        path: '$gastos'
//    }}, {$group: {
//        _id: '$name',
//                sumPrice: {
//            $sum: '$gastos.price'
//        }
//    }}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_CUSTOMER);

        col.aggregate(List.of(
                unwind("$purchases"),
                lookup("Items", "purchases.id_purchase", "purchases.id_purchase", "gastos"),
                unwind("$gastos"),
                group("$name", sum("sumPrice", "$gastos.price"))
        )).into(new ArrayList<>()).forEach(System.out::println);
    }
}
