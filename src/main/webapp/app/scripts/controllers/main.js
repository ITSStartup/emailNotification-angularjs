'use strict';

angular.module('webappApp')
  .controller('MainCtrl',['$scope','EmailService', function ($scope,EmailService) {
    
    $scope.emailService = new EmailService();
    $scope.emailService.email = '';
    console.log($scope.emailService);
    $scope.send = function(){

    	$scope.emailService.$save(function(){
    		$scope.emailService = new EmailService();
    	});
    };

  }]);
