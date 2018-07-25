#!/bin/sh

# Update and delete may not works, created id may different
# Also need to insert a user in `user_role` table with role 'admin'


#registration

curl --request POST \
  --url http://localhost:8080/register \
  --header 'content-type: application/json' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk' \
  --data '{
 "first_name":"John",
 "last_name":"Cena",
 "email":"john@rokomari.com",
 "mobile":"1233",
 "password":"attitudeadjustment"
}
'

#login

curl --request POST \
  --url http://localhost:8080/login \
  --header 'content-type: application/json' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk' \
  --data '{
 "email":"john@rokomari.com",
"password":"attitudeadjustment"
}
'


# Create Doctor

curl --request POST \
  --url http://localhost:8080/api/insert/doctor/new \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk' \
  --data '{
 "name":"Dr. Starnge",
 "dept":"child_speacialist",
 "joining":"11-12-2016"
}
'

# Create Patient

curl --request POST \
  --url http://localhost:8080/api/insert/patient/new \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk' \
  --data '{
 "name":"Thanos",
 "mobile":"01011-000011",
 "age":"57",
 "gender":"male",
 "occupation":"Software Developer",
 "symptom_summary":" "
}
'

# Doctors

curl --request GET \
  --url http://localhost:8080/api/doctors \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk'
  
# A Doctor

curl --request GET \
  --url http://localhost:8080/api/doctors \
  --header 'content-type: application/json' \
  --header 'doctor_id: 7' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk'

# Patients

curl --request GET \
  --url http://localhost:8080/api/patients \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk'
  
# A patient

curl --request GET \
  --url http://localhost:8080/api/patients \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'patient_id: 8' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk'
  
# Update Patient

curl --request PUT \
  --url http://localhost:8080/api/update/patients \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'patient_id: 8' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk' \
  --data '{
 "name":"Thanos",
 "mobile":"01011-000011",
 "age":"57",
 "gender":"male",
 "occupation":"Software Developer",
 "symptom_summary":" headache, pain"
}
'


# Update Doctor 

curl --request PUT \
  --url http://localhost:8080/api/update/doctors \
  --header 'content-type: application/json' \
  --header 'doctor_id: 7' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk' \
  --data '{
 "name":"Dr. Starnge",
 "dept":"orthopedics",
 "joining":"11-12-2016"
}
'


# Delete Patient

curl --request DELETE \
  --url http://localhost:8080/api/delete/patients \
  --header 'content-type: application/json' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'patient_id: 8' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk'
  

# Delete Doctor

curl --request DELETE \
  --url http://localhost:8080/api/delete/doctors \
  --header 'content-type: application/json' \
  --header 'doctor_id: 7' \
  --header 'jwt_token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQHJva29tYXJpLmNvbSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzI1MzUyODgsImV4cCI6MTUzNTEyNzI4OH0.pu6dbSp5JbVK3vE7AmO0B0Ue2QOIxC4_3b3kp_SXMOM' \
  --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJyb2tvbWFyaS5jb20iLCJpYXQiOjE1MzI1MzUxNTF9.GchNgm3ZZaiQaO7cIjtjQhTfshnWU5OaVWl9u7Pd9Gk'

  































