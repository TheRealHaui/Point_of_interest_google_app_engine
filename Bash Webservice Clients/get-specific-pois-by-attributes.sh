#!/bin/bash

#http://stackoverflow.com/questions/9772036/pass-all-variables-from-one-shellscript-to-another
. ./template/template.sh



echo "Please provide the attributes you want to query for. "
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
#echo $callUrl"/?name=1asdfasdfasdf"

# todo get
curl -H "Accept: application/json" -# --progress-bar --request GET \
$callUrl"/?name=$poiname&description=$desc&category=$category&creator=$creator&latitude=$lat&longitude=$long"




echo ""
echo ""

