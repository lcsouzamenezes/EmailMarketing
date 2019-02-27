
emailMarketing = angular.module("emailMarketing", ["ngRoute"]);

emailMarketing.config(function($routeProvider){
	$routeProvider.when("/", {
        templateUrl : "resources/app/dashboard/dashboard.html",
    }).when("/crudContato/:operacao/:idContato", {
        templateUrl : "resources/app/contato/crud_contato.html",
    }).when("/listarContatos/:operacao", {
        templateUrl : "resources/app/contato/listar_contatos.html",
    }).when("/crudGrupo/:operacao/:idGrupo", {
        templateUrl : "resources/app/grupo/crud_grupo.html",
    }).when("/listarGrupos", {
        templateUrl : "resources/app/grupo/listar_grupos.html",
    });
});

emailMarketing.run(function($rootScope) {
	$rootScope.appName = "Email Marketing";
});
