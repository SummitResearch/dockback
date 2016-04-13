'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:FishinstanceCtrl
 * @description
 * # FishinstanceCtrl
 * Controller of the fishboneApp
 */
//angular.module('fishboneApp')
//    .factory('Containers', function($resource) {
//
//        var urlBase = 'http://192.168.174.129:2375';
//
//        return $resource(urlBase+'/containers/json'); // Note the full endpoint address
//});
//
//angular.module('fishboneApp').factory('Images', function($resource) {
//    return $resource('http://192.168.174.129:2375/images/json'); // Note the full endpoint address
//});

angular.module("fishboneApp", ["restangular"]).config(function(RestangularProvider) {
    //set the base url for api calls on our RESTful services
    var newBaseUrl = "";
    if (window.location.hostname == "localhost") {
        newBaseUrl = "http://192.168.174.129:2375";
    } else {
        var deployedAt = window.location.href.substring(0, window.location.href);
        newBaseUrl = deployedAt + "/";
    }
    RestangularProvider.setBaseUrl(newBaseUrl);
    RestangularProvider.setDefaultHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
    });
    RestangularProvider.setDefaultHttpFields({
        'withCredentials': true
    });
});



angular.module('fishboneApp')
  .controller('FishinstanceCtrl', function ($scope, $timeout, Restangular) {
      this.donutdata = [
          ['Container _1', 420],
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

      $scope.containers = {};

      Restangular.all('containers').getList().then(function(data){
          console.log(data);
          //do something with the list of students
      });

      //console.log(Restangular.all('containers'));

      //Containers.query(function(response) {
      //    $scope.containers.issues = response;
      //    angular.forEach($scope.containers, function(host) {
      //                console.log("Host is:" + host.Id);
      //            });
      //});

      //var hosts = Containers.query({}, function() {
      //
      //    $scope.hosts = hosts;
      //    angular.forEach(hosts, function(host) {
      //        console.log("Host is:" + host.Id);
      //    })
      //});

      //var Hosts = $resource('http://192.168.174.129:2375/containers/json');
      //
      //Hosts.query(function(hosts){
      //    angular.forEach(hosts, function(host) {
      //        console.log("Host is:" + host);
      //    })
      //});


      //if (hosts){
      //    angular.forEach(hosts, function(host) {
      //        console.log("Host name " + host);
      //        $scope.host = host;
      //    });
      //    console.log("Hosts " + hosts);
      //    //$scope.hosts = hosts;
      //}

  });
