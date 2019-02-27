emailMarketing.factory("usuarioFactory", ['$http', function ($http) {

    var dataFactory = {};

    dataFactory.buscarUsuario = function () {

        return null;
    };

    dataFactory.getUsuario = function () {
        var usuario = localStorage.getItem("usuario");
        return JSON.parse(usuario);
    };

    dataFactory.setUsuario = function (usuario) {
        var usuarioStr = JSON.stringify(usuario);
        localStorage.setItem("usuario", usuarioStr);
    };

    return dataFactory;

}]);
