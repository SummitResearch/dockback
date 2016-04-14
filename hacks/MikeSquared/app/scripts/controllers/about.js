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
      //var restHosts = Restangular.one('host','5704034ebe173d32d5b782ea');
      //var hosts = restHosts.get('5704034ebe173d32d5b782ea');
  });
