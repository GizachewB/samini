Software Architecture Mini-project 1
--
-To run the application you can use a single command ` docker-compose up `

Exposed API's after run an application
--
-We have three exposed API's, the system includes Jwt authentication and Spring batch, here are our exposed API's, do step by step 

1, POST http://localhost:8080/api/auth/signup

`{
"email": "admin@miu.edu",
"password": "admin",
"firstName": "super",
"lastName": "admin",
"roles": [{
"role": "ADMIN"
}]
}`

2, POST http://localhost:8080/api/auth/login

`{
"username": "admin@miu.edu",
"password": "admin"
}`

3, GET  http://localhost:8080/batch/start

Team members
-
- Gizachew Birhane Mekonnen - 612779
- Alazar Sileshi Lemma - 612733
- Fikadu Shanko Filipos - 612721