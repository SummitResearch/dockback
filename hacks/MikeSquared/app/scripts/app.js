'use strict';

/**
 * @ngdoc overview
 * @name fishboneApp
 * @description
 * # fishboneApp
 *
 * Main module of the application.
 */
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
      'restangular'
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
      })
      .otherwise({
        redirectTo: '/'
      });
      var newBaseUrl = "https://localhost:8443";
      //RestangularProvider.setDefaultHeaders({'Authorization': 'Basic YWRtaW46QXdlc29tZTEyMyE='});
      RestangularProvider.setBaseUrl(newBaseUrl);

  });
