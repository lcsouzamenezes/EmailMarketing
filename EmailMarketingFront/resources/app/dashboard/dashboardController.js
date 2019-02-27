
emailMarketing.controller("dashboardController", function($scope, $location, $window){

	$scope.usuario = {};

    function init(){
        $scope.usuario = JSON.parse(localStorage.getItem("usuario"));
    };

    init();

});
