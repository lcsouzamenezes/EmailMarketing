###### Dependencias para executar a aplicação

1. JDK 1.8
2. Tomcat 8.X

###### Frameworks

1. Spring 4.X
2. Hibernate 5.X

###### Restful api documentation

Swagger
    http://localhost:8001/swagger-ui.html


Titulo: Cadastra usuário
Url:    http://localhost:8001/public/usuario/
Method: Post
Params: 
```json
{
  "username":"gabriel",
  "password":"12345",
  "email":"gabriel@hotmail.com"
}
```

Titulo:     Autentica usuario e senha informados e retorna token de acesso
Url:        http://localhost:8001/oauth/token?grant_type=password&username=gabriel&password=12345
Method:     Post
Headers:    Authorization:  Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0
            Accept:         application/json



