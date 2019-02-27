emailMarketing.factory("exampleFactory", ['$http', function ($http) {

    var urlBase = "";

    var dataFactory = {};

    return dataFactory;

    dataFactory.method1 = function (args) {
        return $http.post(urlBase, args);
    };

    dataFactory.method2 = function (args) {
        return $http.post(urlBase, args);
    };

}]);
