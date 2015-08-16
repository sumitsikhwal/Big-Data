 def timediff(time1: String, time2: String, intervalInMinutes: Int) : Boolean = {
	 val format = new java.text.SimpleDateFormat("HH:mm:ss")
 	val date1 = format.parse(time1);
	val date2 = format.parse(time2);
	val difference = date2.getTime() - date1.getTime(); 
	(difference>0) && (difference < (intervalInMinutes*60*1000))
 }
 
def trimQuotes(string : String): String = {
    var length = string.length()
    string.substring(1, length-1);
  }
  
// Call recommendation based on the previous call record

def callRecommendations(userid: String, time: String, interval: Int, maxNumSuggestions: Int): Array[String] ={
 var callrecords = sc.textFile("/vxk143830/callrecords.csv")
 var filteredRDD = callrecords.map(x => x.split("\t")).filter(i => trimQuotes(i(0))==userid)
 filteredRDD.filter(i => timediff(time, trimQuotes(i(2)), interval)).map(_(1)).distinct.take(maxNumSuggestions)
}
  
callRecommendations("AAH03JAC3AAAdBxAg3", "10:40:00", 15, 5)