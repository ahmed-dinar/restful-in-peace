#!/bin/sh

#registration

curl --request POST \
  --url http://localhost:8080/register \
  --header 'content-type: application/json' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI0OTczNTF9.oUXP6a3kWhGXDNiJsA2rwmZuutm7T5uT_lNgAyX1BkE' \
  --data '{
 "first_name":"John",
 "last_name":"Cena",
 "email":"john@rokomari.com",
 "mobile":"1233",
 "password":"attitudeadjustment"
}'


#login

curl --request POST \
  --url http://localhost:8080/login \
  --header 'content-type: application/json' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI0OTczNTF9.oUXP6a3kWhGXDNiJsA2rwmZuutm7T5uT_lNgAyX1BkE' \
  --data '{
 "email":"john@rokomari.com",
 "password":"attitudeadjustment"
}'



