#!/bin/bash

echo "Script execution to test the DrinkDetection API"

echo "Adding a drink for user dev"
echo ""
curl --request POST \
  --url http://localhost:8080/drinkdetection/add \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/8.6.1' \
  --data '{
	"userName": "dev",
	"timeStamp": 1711275956,
	"volume": 420
}'

echo ""
echo "Receiving all drinks"
echo ""
curl --request GET \
  --url 'http://localhost:8080/drinkdetection/get?userName=dev' \
  --header 'User-Agent: insomnia/8.6.1'
