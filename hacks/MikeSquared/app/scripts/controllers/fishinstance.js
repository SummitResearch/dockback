'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:FishinstanceCtrl
 * @description
 * # FishinstanceCtrl
 * Controller of the fishboneApp
 */
angular.module('fishboneApp').factory('Host', function($resource) {
    return $resource('https://localhost:8443/host'); // Note the full endpoint address
});

angular.module('fishboneApp')
  .controller('FishinstanceCtrl', function ($scope, $timeout, Restangular, Host, $http) {
      this.donutdata = [
          ['Container_1', 420],
          ['Container_2', 310],
          ['Container_3', 210],
          ['Container_4', 301]
      ];

      $scope.showSlider = false;
      $scope.currentSlice = null;
      $scope.currentSelected = null;

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
                            if ($scope.currentSlice === null){
                                $scope.currentSlice = event.point.name;
                                $scope.currentSelected = this;
                                this.select(true);
                                swapSlider();
                                //alert('Null' + ' ' + $scope.showSlider + ' ' + this.selected + ' ' + event.point.name);
                            }else{
                                if ($scope.currentSlice === event.point.name){
                                    //alert('Equal' + $scope.currentSlice + ' ' + $scope.showSlider + ' ' + this.selected + ' ' + event.point.name);
                                    if (this.selected === true){
                                        this.select(false);
                                        if ($scope.showSlider){
                                            swapSlider();
                                            //alert('showSlider is true and got swapped');
                                        }
                                    } else {
                                        this.select(true);
                                        if (!$scope.showSlider){
                                            swapSlider();
                                        }
                                    }
                                } else {
                                    $scope.currentSelected.select(false);
                                    $scope.currentSelected = this;
                                    $scope.currentSlice = event.point.name;
                                    this.select(true);
                                    if (!$scope.showSlider){
                                        swapSlider();
                                    }
                                    //alert('Not Equal' + $scope.currentSlice + ' ' + $scope.showSlider + ' ' + this.selected);
                                }

                            }

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

      //Convenience method to swap and show slider
      function swapSlider(){
          $scope.showSlider = !$scope.showSlider;
          $timeout(function () {
              $rootScope.$broadcast('reCalcViewDimensions');
              $rootScope.$broadcast('rzSliderForceRender');
          });
      }

      this.stepsValues = ['56f862a28ff1d218c84785c9',
          '56f862a28ff1d218c8478777',
          '56f862a28ff1d218c8478888',
          '56f862a28ff1d218c8478555',
          '56f862a28ff1d218c8478222',
          '56f862a28ff1d218c8478999'];

      //Vertical sliders
      $scope.verticalSlider1 = {
          value: '56f862a28ff1d218c84785c9',
          minValue: 0,
          maxValue: 5,
          options: {
              stepsArray: this.stepsValues,
              vertical: true,
              showTicks: true,
              showTickValues: true,
              ticksValuesTooltip: function(v){
                  return 'Select ' + v + ' to restore';
              }
          }
      };

      //Play with the rest calls
      $scope.hosts = "123";
      //var restHosts = Restangular.one('host','5704034ebe173d32d5b782ea');
      //var hosts = restHosts.get('5704034ebe173d32d5b782ea');
      //var restHosts = Restangular.all('host');
      //var rAhosts = restHosts.getList().$object;
      //this.restHosts.getList().then(function(hosts){
      //    this.hosts = hosts;
      //});
      //console.log(rAhosts);
      $http.defaults.headers.common = {'Authorization' : 'Basic YWRtaW46QXdlc29tZTEyMyE='};
      $http.defaults.headers.common = {'Accept' : 'application/json'};
      //$http.defaults.headers.common = {'rejectUnauthorized' : false };
      var hosts = Host.query(function(){
          console.log(hosts);
      });
      if (hosts){
          $scope.hosts = hosts;
      }
      //if (rAhosts){
      //    $scope.hosts = rAhosts;
      //}

      //console.log(Restangular.getRequestedUrl);
  });
