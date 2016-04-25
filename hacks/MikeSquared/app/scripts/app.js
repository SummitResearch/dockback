'use strict';

/**
 * @ngdoc overview
 * @name fishboneApp
 * @description
 * # fishboneApp
 *
 * Main module of the application.
 */

//import tooltip from "../../../bower_components/angular-ui-bootstrap/src/tooltip";

angular
  .module('fishboneApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
      'highcharts-ng',
      'rzModule',
      'ngResource',
      'restangular',
      'ui.bootstrap'
  ])
  .config(function ($routeProvider, RestangularProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/fishinstance', {
        templateUrl: 'views/fishinstance.html',
        controller: 'FishinstanceCtrl',
        controllerAs: 'fishinstance'
      }).when('/fishinstance2', {
            templateUrl: 'views/fishinstance2.html',
            controller: 'FishinstanceCtrl2',
            controllerAs: 'fishinstance2'
        })
      .otherwise({
        redirectTo: '/'
      });
      var newBaseUrl = "https://localhost:8443";
      //RestangularProvider.setDefaultHeaders({'Authorization': 'Basic YWRtaW46QXdlc29tZTEyMyE='});
      RestangularProvider.setBaseUrl(newBaseUrl);
  });
