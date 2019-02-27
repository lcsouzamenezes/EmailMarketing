
emailMarketing.controller("loginController", function($scope, $http, $location, $window, loginFactory){

	$scope.signInModel = {
		username: "",
		password: ""
	};

	$scope.signUpModel = {
		email: "",
		username: "",
		password: "",
		confirmPassword: ""
	};

	$scope.erro = {
		hasErro: false,
		mesage: ""
	};

	$scope.signIn = function(){
		loginFactory.autenticarUsuario($scope.signInModel).then(function successCallback(response) {
			loginFactory.setUsuarioLocalStorage($scope.signInModel);
			$window.location.href = "http://localhost:8080/home.html";
  		}, function errorCallback(response) {
			$scope.erro.hasErro = true;
			$scope.erro.message = response.data.erro;
  		});
	};

	$scope.signUp = function(){
		loginFactory.cadastrarUsuario($scope.signUpModel).then(function successCallback(response) {
			loginFactory.setUsuarioLocalStorage($scope.signUpModel);
			$window.location.href = "http://localhost:8080/home.html";
  		}, function errorCallback(response) {
			$scope.erro.hasErro = true;
			$scope.erro.message = response.data.erro;
  		});
	};

});
