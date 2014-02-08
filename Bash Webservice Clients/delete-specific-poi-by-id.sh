#!/bin/bash

#http://stackoverflow.com/questions/9772036/pass-all-variables-from-one-shellscript-to-another
. ./template/template.sh



echo "Please provide the id you want to query for. "
echo ""

echo "Please provide:"

echo "id"
read id

echo ""

#echo $callUrl/"$id"

# todo get
curl -H "Accept: application/json" -# --progress-bar --request DELETE \
$callUrl/"$id"




echo ""
echo ""

