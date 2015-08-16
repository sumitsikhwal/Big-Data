import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors	

val calllog = sc.textFile("/cxv140530/tip.txt")

val calllog1 = calllog.map(l => l.replace("\"",""))
val parsedData = calllog1.map{line => Vectors.dense(line.split(",").slice(5,7).map(_.toDouble))}

val iterationCount = 100			
val clusterCount = 5
val model = KMeans.train(parsedData, clusterCount, iterationCount)

val callsByGoup = calllog1.map {_.split(',').slice(5, 7).map(_.toDouble)}.groupBy{rdd => model.predict(Vectors.dense(rdd))}.collect()
var str=""
val list = scala.collection.mutable.MutableList[String]()

for ( i <- 1 to clusterCount )
{
 val cluster=callsByGoup(i-1)._2.iterator.toList.map(x => x.toList) 
  for(li <- cluster)
   {str=   i + "," +li(0)+","+li(1)
   list +=str }
}


val cost = model.computeCost(parsedData)

println("Cost: " + cost)


sc.parallelize(list,1).saveAsTextFile("/cxv140530/ProjectOutput/latlong.txt")

val list1 = scala.collection.mutable.MutableList[String]()
val clusterCenters = model.clusterCenters map (_.toArray)
var counter : Int = 1;
for(li<-clusterCenters ){
{ 
 str= counter + "," +li(0)+","+li(1)
   list1 +=str
   counter = counter + 1 
    
}
}
sc.parallelize(list1,1).saveAsTextFile("/cxv140530/ProjectOutput/latlong1.txt")