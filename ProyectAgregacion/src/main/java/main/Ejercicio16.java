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
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class Ejercicio16 {

    //QUERY 1 - The console that has released the most games
    // Result : Document{{_id=Nintendo DS, NgamesConsole=317}}

    //[{$group: {
    //        _id: '$Release.Console',
    //                NgamesConsole: {
    //            $sum: 1
    //        }
    //    }}, {$sort: {
    //        NgamesConsole: -1
    //    }}, {$limit: 1}]

    //QUERY 2 : average prices of games with an average score higher than 70 grouped by genre
    // Result: the first must be this :
    // Document{{_id=Action,Racing / Driving,Sports, mediaPrice=27.45}}
    //Document{{_id=Role-Playing (RPG),Simulation, mediaPrice=24.95}}
    //Document{{_id=Educational,Sports, mediaPrice=24.95}}
    //Document{{_id=Action,Role-Playing (RPG),Strategy, mediaPrice=24.95}}
    //Document{{_id=Adventure,Educational,Strategy, mediaPrice=23.95}}
    //Document{{_id=Action,Adventure,Role-Playing (RPG), mediaPrice=23.616666666666664}}
    //Document{{_id=Adventure,Role-Playing (RPG), mediaPrice=23.616666666666664}}
    //Document{{_id=Simulation, mediaPrice=22.586363636363636}}
    //Document{{_id=Role-Playing (RPG), mediaPrice=22.371052631578948}}
    //Document{{_id=Role-Playing (RPG),Strategy, mediaPrice=21.71923076923077}}
    //Document{{_id=Action,Role-Playing (RPG), mediaPrice=21.03695652173913}}
    //Document{{_id=Adventure,Simulation, mediaPrice=20.45}}
    //Document{{_id=Action,Simulation, mediaPrice=19.313636363636363}}
    //Document{{_id=Racing / Driving,Simulation,Sports, mediaPrice=19.15}}
    //Document{{_id=Action,Sports, mediaPrice=18.95}}
    //Document{{_id=Racing / Driving, mediaPrice=18.54259259259259}}
    //Document{{_id=Racing / Driving,Simulation, mediaPrice=18.45}}
    //Document{{_id=Action, mediaPrice=18.37396313364055}}
    //Document{{_id=Action,Strategy, mediaPrice=18.13181818181818}}
    //Document{{_id=Action,Racing / Driving,Sports,Strategy, mediaPrice=17.95}}
    //Document{{_id=Action,Racing / Driving,Role-Playing (RPG),Strategy, mediaPrice=17.95}}
    //Document{{_id=Racing / Driving,Simulation,Strategy, mediaPrice=17.95}}
    //Document{{_id=Action,Racing / Driving, mediaPrice=17.95}}
    //Document{{_id=Action,Role-Playing (RPG),Simulation, mediaPrice=17.95}}
    //Document{{_id=Action,Racing / Driving,Role-Playing (RPG), mediaPrice=17.616666666666664}}
    //Document{{_id=Action,Simulation,Sports, mediaPrice=17.45}}
    //Document{{_id=Action,Adventure, mediaPrice=17.378571428571426}}
    //Document{{_id=Adventure, mediaPrice=17.15}}
    //Document{{_id=Simulation,Strategy, mediaPrice=17.116666666666664}}
    //Document{{_id=Strategy, mediaPrice=17.05}}
    //Document{{_id=Educational, mediaPrice=15.95}}
    //Document{{_id=Adventure,Role-Playing (RPG),Strategy, mediaPrice=14.95}}
    //Document{{_id=Action,Adventure,Strategy, mediaPrice=14.95}}
    //Document{{_id=Action,Simulation,Strategy, mediaPrice=14.95}}
    //Document{{_id=Racing / Driving,Sports, mediaPrice=13.95}}
    //Document{{_id=Action,Adventure,Racing / Driving,Sports, mediaPrice=13.45}}
    //Document{{_id=Sports, mediaPrice=12.921698113207547}}
    //Document{{_id=Educational,Simulation, mediaPrice=8.95}}

    //[{$match: {
    //        'Metrics.Review Score': {
    //            $gt: 70
    //        }
    //    }}, {$group: {
    //        _id: '$Metadata.Genres',
    //                mediaPrice: {
    //            $avg: '$Metrics.Used Price'
    //        }
    //    }}, {$sort: {
    //        mediaPrice: -1
    //    }}]


    //QUERY 3  :Name of the games released by Ubisoft in 2008 for more than two players, sorted in descending order of score
    //Result:
    // Document{{Title=Tom Clancy's Rainbow Six: Vegas 2}}
    //Document{{Title=Tom Clancy's Rainbow Six: Vegas 2}}
    //Document{{Title=Rayman Raving Rabbids TV Party}}
    //Document{{Title=Haze}}
    //Document{{Title=Nitrobike}}

    //[{$match: {
    //        'Metadata.Publishers': {
    //            $eq: 'Ubisoft'
    //        },
    //        'Features.Max Players': {
    //            $gt: 1
    //        },
    //        'Release.Year': {
    //            $eq: '2008'
    //        }
    //    }}, {$sort: {
    //        'Metrics.Review Score': -1
    //    }}, {$project: {
    //        _id: 0,
    //                Title: 1
    //    }}]

    //QUERY 5 : the genre with the highest average sales
    //Result: Document{{_id=Educational,Sports, media=9.6}}

    //    [{$group: {
    //        _id: '$Metadata.Genres',
    //                media: {
    //            $avg: '$Metrics.Sales'
    //        }
    //    }}, {$sort: {
    //        media: -1
    //    }}, {$limit: 1}]


    //QUERY 5 : Average score for EA games by console
    // Result:
    //Document{{_id=PlayStation 3, media=74.6875}}
    //Document{{_id=Sony PSP, media=70.075}}
    //Document{{_id=X360, media=73.93617021276596}}
    //Document{{_id=Nintendo DS, media=60.041666666666664}}
    //Document{{_id=Nintendo Wii, media=66.81818181818181}}

        //    [{$match: {
    //        'Metadata.Publishers': {
    //            $eq: 'EA'
    //        }
    //    }}, {$group: {
    //        _id: '$Release.Console',
    //                media: {
    //            $avg: '$Metrics.Review Score'
    //        }
    //    }}]
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(Constantes.MONGODB);

        MongoDatabase db = mongo.getDatabase(Constantes.DATABASE);
        MongoCollection<Document> col = db.getCollection(Constantes.COLLECTION_VIDEOJUEGOS);

        System.out.println("************************QUERY 1 *********************************");
        col.aggregate(List.of(
                group("$Release.Console", sum("NgamesConsole", 1)),
                sort(descending("NgamesConsole")),
                limit(1)
        )).into(new ArrayList<>()).forEach(System.out::println);

        System.out.println("************************QUERY 2 *********************************");
        col.aggregate(List.of(
                match(gt("Metrics.Review Score", 70)),
                group("$Metadata.Genres", avg("mediaPrice", "$Metrics.Used Price")),
                sort(descending("mediaPrice"))
        )).into(new ArrayList<>()).forEach(System.out::println);

        System.out.println("************************QUERY 3 *********************************");

        col.aggregate(List.of(
                        match(and(eq("Metadata.Publishers", "Ubisoft")
                                , gt("Features.Max Players", 1)
                                , eq("Release.Year", "2008"))),
                        sort(descending("Metrics.Review Score")),
                        project(fields(exclude("_id")
                                , include("Title")))
                )
        ).into(new ArrayList<>()).forEach(System.out::println);

        System.out.println("************************QUERY 4 *********************************");
        col.aggregate(List.of(
                group("$Metadata.Genres", avg("media", "$Metrics.Sales")),
                sort(descending("media")),
                limit(1)
        )).into(new ArrayList<>()).forEach(System.out::println);

        System.out.println("************************QUERY 5 *********************************");
        col.aggregate(List.of(
                match(eq("Metadata.Publishers", "EA")),
                group("$Release.Console", avg("media", "$Metrics.Review Score"))
        )).into(new ArrayList<>()).forEach(System.out::println);
    }
}
