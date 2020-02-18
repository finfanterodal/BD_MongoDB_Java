package ad_mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
/*
 MongoClient
 MongoDatabase
 MongoCollection
 */

/*
 1) inserir este  documento : "p4",c1","pro3",5,"02/02/2019"
 2) actualizar un par clave valor por clave do documento : exemplo actualizar o campo codpro do pedido  p3 a o valor pro4
 3)incrementar un par clave valor por clave. exemplo: aumentar en 7 a cantidade correspondente ao pedido p2 .
 4) amosar o documento correspondente ao pedido p3
 5) amosar o codigo do cliente, o codigo do producto e a cantidade correspondentes ao pedido p1
 6) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2
 7) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2 e inferior a 5
 8) amosar   o codigo do cliente e  o codigo do producto correspondentes a todos os pedidos.
 9) aumentar  no seu dobre a cantidade correspondente ao pedido p4 .
 */
/**
 *
 * @author oracle
 */
public class AD_MongoDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Conectarse a MONGODB
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        /*
      
         //1.inserir este  documento : "p4",c1","pro3",5,"02/02/2019"
         //CONSULTA: db.pedidos.insert({"_id":p4,"codcli":"c1","codpro":"pro3","cantidade":5,"data":"02/02/2019"})
         BasicDBObject object = new BasicDBObject();
         object.put("_id", "p4");
         object.put("codcli", "c1");
         object.put("codprod", "pro3");
         object.put("cantidade", 5);
         object.put("data", "02/02/2019");
         collection.insertOne(object);
      

         //2.exemplo actualizar o campo codpro do pedido  p3 a o valor pro4
         //CONSULTA:db.pedidos.update({"_id":p3},{$set:{"codpro":pro4}})
         BasicDBObject query = new BasicDBObject();
         query.put("_id", "p3");
         BasicDBObject newDocument = new BasicDBObject();
         newDocument.put("codpro", "pro4");
         BasicDBObject updateObject = new BasicDBObject();
         updateObject.put("$set", newDocument);
         collection.updateOne(query, updateObject);
        
         
         //3.exemplo: aumentar en 7 a cantidade correspondente ao pedido p2 .
         //CONSULTA:db.books.update({"_id":"p2"},{$inc:{"cantidade":7}})
         Double cantidade = 0d;
         BasicDBObject query2 = new BasicDBObject();
         query2.put("_id", "p2");
         MongoCursor<Document> cursor = collection.find(query2).iterator();
         try {
         while (cursor.hasNext()) {
         Document temp_person_doc = cursor.next();
         cantidade = temp_person_doc.getDouble("cantidade");
    
         }
         } catch (ClassCastException e) {
         System.out.println(e.getMessage());
         } finally {
         cursor.close();
         }

         cantidade = cantidade + 7d;
         BasicDBObject newDocument = new BasicDBObject();
         newDocument.put("cantidade", cantidade);
         BasicDBObject updateObject = new BasicDBObject();
         updateObject.put("$set", newDocument);
         collection.updateOne(query2, updateObject);
        
        
         //4. amosar o documento correspondente ao pedido p3
         //CONSULTA: db.pedidos.find({"_id":"p3"})
         BasicDBObject query3 = new BasicDBObject();
         query3.put("_id", "p3");
         Document doc2=(Document) collection.find(query3).first();
         System.out.println("_id:"+doc2.getString("_id")+" codcli: "+doc2.getString("codcli")+" codpro: "+doc2.getString("codpro")+" cantidade: "+doc2.getDouble("cantidade")+" data: "+doc2.getString("data"));
           
    
         // 5) amosar o codigo do cliente, o codigo do producto 
         //e a cantidade correspondentes ao pedido p1
         //CONSULTA: db.pedidos.find({"_id":"p1"},{"codcli":"","codpro":"","cantidade":""})
         BasicDBObject query4 = new BasicDBObject();
         query4.put("_id", "p3");
         Document doc3=(Document) collection.find(query4).first();
         System.out.println("_id:"+doc3.getString("_id")+" codcli: "+doc3.getString("codcli")+" codpro: "+doc3.getString("codpro")+" cantidade: "+doc3.getDouble("cantidade"));
         
         */

        //6) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos
        //que teñan no campo cantidade un valor superior a 2
        //CONSULTA: db.pedidos.find({"cantidade":{$gt:2}},{codcli:1,codpro:1,_id:0})
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("cantidade", new BasicDBObject("$gt", 2));
        MongoCursor<Document> cursor = (MongoCursor) collection.find(getQuery).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
         Document temp_person_doc = (Document) cursor.next();
         //cantidade = temp_person_doc.getDouble("cantidade")
        }

    }

}
