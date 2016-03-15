'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:FishinstanceCtrl
 * @description
 * # FishinstanceCtrl
 * Controller of the fishboneApp
 */
angular.module('fishboneApp')
  .controller('FishinstanceCtrl', function ($scope) {
    $scope.highchartsNG = {
        options: {
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                }
            },
            plotOptions: {
                pie: {
                    innerSize: 150,
                    depth: 45
                }
            }
        },
        title: {
            text: 'Prometheuns Dockback Host - 10.99.0.67'
        },
        subtitle: {
            text: '3D donut in Highcharts'
        },
        series: [{
            name: 'Image virtual size',
            data: [
                ['DockerImg_1', 420],
                ['DockerImg_2', 310],
                ['DockerImg_3', 210],
                ['DockerImg_4', 301]
            ]
        }]
    }
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
