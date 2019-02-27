
emailMarketing.controller("grupoController", function($scope, $location, $routeParams){

    $scope.operacao = $routeParams.operacao;

    $scope.idGrupo = $routeParams.idGrupo == "null" ? null : Number($routeParams.idGrupo);

    $scope.grupoModel = {

    };

    $scope.grupos = [];

    $scope.contatos = [];

    $scope.contatoSelecionado = {};

    $scope.contatosAdicionados = [];
	
	$scope.contatosRemovidos = [];

    $scope.erro = {
        hasErro: false,
        msg: ""
    };

    var listarGrupos = function(){
        var grupos = [];
        grupos.push({id: 1, nome: "Exemplo 1"});
        grupos.push({id: 2, nome: "Exemplo 2"});
        grupos.push({id: 3, nome: "Exemplo 3"});
        grupos.push({id: 4, nome: "Exemplo 4"});
        grupos.push({id: 5, nome: "Exemplo 5"});

        return grupos;
    };

    $scope.buscarGrupo = function(){
        var grupo = {id: 1, nome: "Exemplo 1"};
        return grupo;
    };

    var listarContatos = function(){
        var contatos = [];
        contatos.push({id: 1, nome: "gabriel", email: "gabriel@gmail.com"});
        contatos.push({id: 2, nome: "felipe", email: "felipe@gmail.com"});
        contatos.push({id: 3, nome: "carlos", email: "carlos@gmail.com"});
        contatos.push({id: 4, nome: "diego", email: "diego@gmail.com"});
        contatos.push({id: 5, nome: "giulia", email: "giulia@gmail.com"});

        return contatos;
    };

    $scope.contatos = listarContatos();

    $scope.adicionarContato = function(){
        if($scope.contatosAdicionados.length > 0){
            for(var i = 0; i < $scope.contatosAdicionados.length; i++){
                if($scope.contatosAdicionados[i] != null && $scope.contatosAdicionados[i].id == $scope.contatoSelecionado.id){
                    $scope.erro.hasErro = true;
                    $scope.erro.msg = "Contato já está na lista.";
                    return;
                }
            }
			$scope.contatosAdicionados.push($scope.contatoSelecionado);
        }
        else {
            $scope.contatosAdicionados.push($scope.contatoSelecionado);
        }
    };
	
	$scope.removerContato = function(idContato){
		console.log(idContato);
		for(var i = 0; i < $scope.contatosAdicionados.length; i++){
			if($scope.contatosAdicionados[i] != null && $scope.contatosAdicionados[i].id == idContato){
				$scope.contatosAdicionados[i] = null;
			}
		}
		$("#tr_contato_"+idContato).remove();
	};

    var init = function(){
        if($scope.operacao == undefined || $scope.operacao == null){
            $scope.grupos = listarGrupos();
        }
        if($scope.operacao == "Editar"){
            $scope.grupoModel = buscarGrupo();
        }
    };

    init();

});
