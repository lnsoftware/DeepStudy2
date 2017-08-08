
curl -X POST -H "Content-Type: application/json" 'http://localhost:8080/jsonPost' -d '{"id":2,"name":"foo","mobile":"13656635451"}'

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" 'http://localhost:8080/formPost' -d 'id=1&name=foo&mobile=13612345678'