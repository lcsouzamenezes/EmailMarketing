emailMarketing.factory("loginFactory", ['$http', function ($http) {

    var oauthUrl = "http://localhost:8001/oauth/token";

    var urlApp = "http://localhost:8001";

    var dataFactory = {};

    dataFactory.autenticarUsuario = function (signInModel) {
        var url = oauthUrl + "?grant_type=password&username=param1&password=param2";
        url = url.replace("param1", signInModel.username);
        url = url.replace("param2", signInModel.password);
        return $http.post(url, signInModel, {
			'Authorization': 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'
		});
    };

    dataFactory.cadastrarUsuario = function (signUp) {
        return $http.post(urlApp + "/public/usuario/", signUp);
    };

    dataFactory.getUsuarioLocalStorage = function(){
        var usuario = localStorage.getItem("usuario");
        return JSON.parse(usuario);
    };

    dataFactory.setUsuarioLocalStorage = function(usuario){
        localStorage.setItem("usuario", JSON.stringify(usuario));
    };

    return dataFactory;

}]);
