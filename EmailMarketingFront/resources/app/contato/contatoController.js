
emailMarketing.controller("contatoController", function($scope, $location, $routeParams){
    // Cadastrar / Editar / Listar
    $scope.operacao = $routeParams.operacao;

    $scope.idContato = $routeParams.idContato == "null" ? null : Number($routeParams.idContato);

    $scope.contatoModel = {
        nome: "",
        email: ""
    };

    $scope.contatos = [];

    $scope.salvar = function(){
        console.log("Salvando contato: " + JSON.stringify($scope.contatoModel));
        $scope.contatoModel = {};
    };

    $scope.editar = function(idContato){
        console.log("Editando contato: " + idContato);
    };

    $scope.deletar = function(idContato){
        console.log("Removendo contato: " + idContato);
    };

    $scope.listar = function(){
        var contatos = [];
        contatos.push({id: 1, nome: "gabriel", email: "gabriel@gmail.com"});
        contatos.push({id: 2, nome: "felipe", email: "felipe@gmail.com"});
        contatos.push({id: 3, nome: "carlos", email: "carlos@gmail.com"});
        contatos.push({id: 4, nome: "diego", email: "diego@gmail.com"});
        contatos.push({id: 5, nome: "giulia", email: "giulia@gmail.com"});

        return contatos;
    };

    $scope.buscarContato = function(){
        var contato = {id: 1, nome: "gabriel", email: "gabriel@gmail.com"};
        return contato;
    };

    $scope.executarOperacao = function(){
        if($scope.operacao == "Cadastrar"){
			$scope.salvar();
        }
        else{
			$scope.editar();
        }
    };

    var init = function(){
        if($scope.operacao == "Listar"){
            $scope.contatos = $scope.listar();
        }
        if($scope.operacao == "Editar"){
            $scope.contatoModel = $scope.buscarContato();
        }
    };

    init();

});
