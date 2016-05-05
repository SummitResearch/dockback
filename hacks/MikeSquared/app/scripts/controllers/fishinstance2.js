'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:FishinstanceCtrl2
 * @description
 * # FishinstanceCtrl2
 * Controller of the fishboneApp
 */



angular.module('fishboneApp')
  .controller('FishinstanceCtrl2', function ($rootScope, $scope, $timeout, Restangular) {
      this.donutdata = [
          ['Container _1', 420],
          ['Container_2', 310],
          ['Container_3', 210],
          ['Container_4', 301]
      ];

      $scope.showSlider2 = false;
      $scope.currentSlice2 = null;
      $scope.currentSelected2 = null;

      $scope.highchartsNG2 = {
        options: {
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                },
                backgroundColor: 'transparent'
            },
            tooltip: {
                formatter: function () {
                    return '<b>Container:</b> ' + this.key+'<br/><b>Size:</b> '+formatSizeUnits(this.y);
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    innerSize: 150,
                    depth: 45,
                    events:{
                        click:function(event){
                            if ($scope.currentSlice2 === null){
                                $scope.currentSlice2 = event.point.name;
                                $scope.currentSelected2 = this;
                                this.select(true);
                                swapSlider();
                                //alert('Null' + ' ' + $scope.showSlider + ' ' + this.selected + ' ' + event.point.name);
                            }else{
                                if ($scope.currentSlice2 === event.point.name){
                                    //alert('Equal' + $scope.currentSlice + ' ' + $scope.showSlider + ' ' + this.selected + ' ' + event.point.name);
                                    if (this.selected === true){
                                        this.select(false);
                                        if ($scope.showSlider2){
                                            swapSlider();
                                            //alert('showSlider is true and got swapped');
                                        }
                                    } else {
                                        this.select(true);
                                        if (!$scope.showSlider2){
                                            swapSlider();
                                        }
                                    }
                                } else {
                                    $scope.currentSelected2.select(false);
                                    $scope.currentSelected2 = this;
                                    $scope.currentSlice2 = event.point.name;
                                    this.select(true);
                                    if (!$scope.showSlider2){
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
            data: (function () {
                // generate an array of random data
                var data = [];


                Restangular.all('host/5706d3f9c51e2fd27904272b/container').getList().then(function(restData) {

                    angular.forEach(restData, function (container) {

                        Restangular.all('host/5706d3f9c51e2fd27904272b/image').getList().then(function (restData) {
                            angular.forEach(restData, function (image) {
                                if (image.dockerImageId == container.imageId) {
                                    data.push({
                                        name: container.names[0].replace(/\//,''),
                                        y: image.virtualSize
                                    })
                                }
                            })
                        });

                    });
                });

                return data;
            }())
        }]
    }

      //Convenience method to swap and show slider
      function swapSlider(){
          $scope.showSlider2 = !$scope.showSlider2;
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
      $scope.verticalSlider2 = {
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

      function formatSizeUnits(bytes){
          if      (bytes>=1073741824) {bytes=(bytes/1073741824).toFixed(2)+' GB';}
          else if (bytes>=1048576)    {bytes=(bytes/1048576).toFixed(2)+' MB';}
          else if (bytes>=1024)       {bytes=(bytes/1024).toFixed(2)+' KB';}
          else if (bytes>1)           {bytes=bytes+' bytes';}
          else if (bytes==1)          {bytes=bytes+' byte';}
          else                        {bytes='0 byte';}
          return bytes;
      };

  });
