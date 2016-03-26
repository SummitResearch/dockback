'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:FishinstanceCtrl
 * @description
 * # FishinstanceCtrl
 * Controller of the fishboneApp
 */
angular.module('fishboneApp')
  .controller('FishinstanceCtrl', function ($scope, $timeout) {
      this.donutdata = [
          ['Container_1', 420],
          ['Container_2', 310],
          ['Container_3', 210],
          ['Container_4', 301]
      ];

      $scope.showSlider = false;

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
                    allowPointSelect: true,
                    innerSize: 150,
                    depth: 45,
                    events:{
                        click:function(event){
                            //alert(event + " " + this.y);
                            $scope.showSlider = !$scope.showSlider;
                            //if ($scope.showSlider) {
                                $timeout(function () {
                                    $scope.$broadcast('rzSliderForceRender');
                                });
                            //}
                        }
                    }
                }
            }
        },
        title: {
            text: 'MY DOCKER CONTAINERS'
        },
        subtitle: {
            text: 'Host: 10.99.0.67'
        },
        series: [{
            allowPointSelection: true,
            name: 'Container size',
            data: this.donutdata
        }]
    }

      //Vertical sliders
      $scope.verticalSlider1 = {
          value: 0,
          options: {
              floor: 0,
              ceil: 10,
              vertical: true,
              showTicks: true
          }
      };
  });
