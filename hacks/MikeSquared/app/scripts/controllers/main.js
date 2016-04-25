'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the fishboneApp
 */
angular.module('fishboneApp')
  .controller('MainCtrl', function ($scope) {
      $scope.activeTab = "mainInst"
      this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
