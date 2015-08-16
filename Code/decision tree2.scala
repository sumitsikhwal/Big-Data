import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors

// Load and parse the data file.

val data = sc.textFile("/vxk143830/planRules2.csv")

val parsedData = data.map(i => i.split(",").map(_.toDouble)).map(i => LabeledPoint(i(0), Vectors.dense(i.tail)))

var trainingData = parsedData
// Train a DecisionTree model.
//  Empty categoricalFeaturesInfo indicates all features are continuous.
val numClasses = 6
val categoricalFeaturesInfo = Map[Int, Int]()
val impurity = "gini"
val maxDepth = 5
val maxBins = 32

val model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
  impurity, maxDepth, maxBins)
  
  
  
model.predict(Vectors.dense(Array("1".toDouble, "2".toDouble)))