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
      $scope.activeTab = "aboutInst"
      var restHosts = Restangular.all('host');
      restHosts.getList().then(function(hosts){
          $scope.theHosts = hosts;
          $scope.theHost = hosts[0].id;
          console.log($scope.theHost);
      });

      //restHosts.all('container',$scope.theHost).getList().then(function(containers){
      //   console.log(containers.plain());
      //});

      restHosts.all('570cf4d9be173d54726e954d/container/570cf513be173d54726e9555/checkpoint').getList().then(function(checkpoint){
         $scope.theCheckpointId = checkpoint[0].id;
          console.log($scope.theCheckpointId);
          console.log(checkpoint.plain());
      });

      //var host = restHosts.get('5704034ebe173d32d5b782ea');
      //host.getList().then(function(hosts){
      //    console.log(hosts.plain());
      //});
  });
