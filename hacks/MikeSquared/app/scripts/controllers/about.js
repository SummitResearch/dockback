'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the fishboneApp
 */
angular.module('fishboneApp')
  .controller('AboutCtrl', function ($scope, Restangular) {
      var restHosts = Restangular.all('host');
      restHosts.getList().then(function(hosts){
          $scope.theHosts = hosts;
          $scope.theHost = hosts[0].id;
      });
      Restangular.all('host/570cf4d9be173d54726e954d/container/570cf513be173d54726e9554/checkpoint').getList().then(function(checkpoint){
         $scope.theCheckpointId = checkpoint[0].id;
          console.log($scope.theCheckpointId);
      });

      //var restHosts = Restangular.one('host','5704034ebe173d32d5b782ea');
      //var hosts = restHosts.get('5704034ebe173d32d5b782ea');
  });
