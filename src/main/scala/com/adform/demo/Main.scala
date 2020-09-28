package com.adform.demo

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.Sink
import com.mongodb.reactivestreams.client.MongoClients
import com.mongodb.{ConnectionString, MongoClientSettings}

object Main {

  def main(args: Array[String]): Unit = {

    val settings = MongoClientSettings
      .builder()
      .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
      .build()
    val db = MongoClients.create(settings).getDatabase("test_db")
    val collection = db.getCollection("test_collection")

    val publisher = collection.watch()

    val source = MongoSource(publisher)

    implicit val system: ActorSystem = ActorSystem()
    implicit val mat: ActorMaterializer = ActorMaterializer()

    source.runWith(Sink.foreach(println))

  }

}
