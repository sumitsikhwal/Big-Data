library(ggmap)
library(cluster)
library(fpc)
tip=read.csv("D:\\in Dallas\\Classes Sum15\\BigData\\project\\tip.txt")
tip.features=tip
trimmed=subset(tip.features,select = c(X23.813900,X90.398598))

B = matrix( c(23.7042, 23.6989, 23.7175, 23.7028, 23.8092, 90.4297,90.4353,90.4711,90.4061,90.4089), nrow=5, ncol=2) 

classify <- kmeans(trimmed,B,iter.max=100)
dA<-cbind(trimmed,classify$cluster)

#dA<-clusplot(trimmed, classify$cluster, color=TRUE, shade=TRUE, labels=2, lines=0)

#df <- data.frame(matrix(unlist(dA), ncol=2, byrow=TRUE),stringsAsFactors=FALSE)

 map <- get_map(location  =  c(lon = 90.4, lat = 23.75), zoom = 10, scale = 2, color = "bw")
# 
 p<-ggmap(map)
# 
 p<-p + geom_point(data=dA, aes(y=X23.813900, x=X90.398598), color=classify$cluster,  size=3, alpha=0.7)+scale_fill_manual(values=classify$cluster)
 p