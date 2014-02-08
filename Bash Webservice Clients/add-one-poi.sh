#!/bin/bash

#http://stackoverflow.com/questions/9772036/pass-all-variables-from-one-shellscript-to-another
. ./template/template.sh



echo "Adding of a poi. "
echo ""

echo "Please provide:"

echo "description"
read desc

echo "category"
read cat

echo "creator"
read creator

echo "latitude"
read lat


echo "longitude"
read long

echo "name"
read poiname




#echo $callUrl


#curl -X POST -H "Content-Type: application/json" -d \
#'{"description":"$desc","category":"$cat","creator":"$creator","latitude":"$lat","longitude":"$long","name":"$poiname"}' \
#$callUrl


#curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d \
#'{"description":"$desc","category":"$cat","creator":"$creator","latitude":"$lat","longitude":"$long","name":"$poiname"}' \
#$callUrl


filename="postdata"$(($(date +%s%N)/1000000))".txt"


echo "{"description":"$desc","category":"$cat","creator":"$creator","latitude":"$lat","longitude":"$long","name":"$poiname"}" > $filename

echo ""

#Why ever you do it this way and not with the options above.
curl -X POST -d @./$filename $callUrl --header "Content-Type:application/json"


rm -r $filename


echo ""
echo ""

