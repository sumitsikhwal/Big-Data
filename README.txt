Call Recommendation

Recommendation contains standalone scala functions namely timediff, trimQuotes and callRecommendations which has to be executed in order.
An example is provided how to get the callRecommendations
callRecommendations(userID, current time, time interval in minutes, max recommendations)
callRecommendations("AAH03JAC3AAAdBxAg3", "10:40:00", 15, 5)

If input has to be changed then update the callRecommendations function with the new input file which will be in the format 
"AAH03JAC6AAAcyuAbe"	"AAH03JAC3AAAdEJAG3"	"17:58:21"	"61"
with quotes that is the reason why trimQuotes is running

Decision tree

Decision tree is a classifier used to predict the user plan based on the location clusters aka zones and call clusters.
It is trained with the file called planRules2.csv. It will be created by the business users based on the research their marketing team has done.

The input can be changed. The format specified is
Plan number, zone number, call cluster number
3,1,3
5,1,2
3,1,1
1,2,3
3,2,2
5,2,1

Plan recommender

1 Nearest neighbour is used to recommend a plan based on the current user location and the average time he spent by calling others.
First the case class Coordinates has to be executed so that a class will be created to store the location values namely latitudes and longitues
next the dist function has to be executed this function is used to find the squared distance between two points.

Finally the predictPlan has to be executed.
For predicting the plan we need the latitude and longitude and average call duration estimate of the new user and the model (decision tree model which we create before).

print("Plan " + predictPlan(latitude, longitude, average call duration, model).toInt)
print("Plan " + predictPlan("23.813900","90.398598", "34", model).toInt) 


Team Members:

Vishal Kankanala	vxk143830
Nagarjuna Manchineni	nxm145830
Venkatesh Poosarla	vxp141230
Siva Teja Kambham	sxk143031
Aurko Ghosh		axg131530
Sumit K Sikhwal		sks130730
Chaitanya Vejendla	cxv140530


