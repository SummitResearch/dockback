'use strict';

/**
 * @ngdoc function
 * @name fishboneApp.controller:FishinstanceCtrl
 * @description
 * # FishinstanceCtrl
 * Controller of the fishboneApp
 */

angular.module('fishboneApp')
  .controller('FishinstanceCtrl', function ($rootScope, $scope, $timeout, Restangular, $uibModal) {
      $scope.activeTab = "fishInst";
      this.points = [];

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
                },
                backgroundColor: 'transparent'
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
                                setStepValues();
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
                                            setStepValues();
                                        }
                                    }
                                } else {
                                    $scope.currentSelected.select(false);
                                    $scope.currentSelected = this;
                                    $scope.currentSlice = event.point.name;
                                    this.select(true);
                                    if (!$scope.showSlider){
                                        swapSlider();
                                        setStepValues();
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
            text: 'Host: 172.16.105.212'
        },
        series: [{
            allowPointSelection: true,
            name: 'Container size',
            data: (function () {
                // generate an array of containers
                var data = [];

                Restangular.all('host/570cf4d9be173d54726e954d/container').getList().then(function(restData) {

                    angular.forEach(restData, function (container) {

                        Restangular.all('host/570cf4d9be173d54726e954d/image').getList().then(function (restData) {
                            angular.forEach(restData, function (image) {
                                if (image.dockerImageId == container.imageId) {
                                    data.push({
                                        name: container.names[0].replace(/\//,''),
                                        y: getVirtualSize(image)
                                    })
                                }
                            })
                        });

                    });
                });
                return data;
            }())
        }]
    };

      //Helper to create the right format of the size
      function getVirtualSize(image){
          var bytes = image.virtualSize;
          if      (bytes>=1073741824) {bytes=(bytes/1073741824).toFixed(2);}
          else if (bytes>=1048576)    {bytes=(bytes/1048576).toFixed(2);}
          else if (bytes>=1024)       {bytes=(bytes/1024).toFixed(2);}
          else if (bytes>1)           {bytes=bytes;}
          else if (bytes==1)          {bytes=bytes;}
          else                        {bytes=0;}
          return parseFloat(bytes);
      }

      //Convenience method to swap and show slider
      function swapSlider(){
          $scope.showSlider = !$scope.showSlider;
          $timeout(function () {
              $scope.verticalSlider1.value = "Now";
              $rootScope.$broadcast('reCalcViewDimensions');
              $rootScope.$broadcast('rzSliderForceRender');
          });
      };


      //Vertical sliders
      $scope.verticalSlider1 = {
          value: 'Now',
          minValue: 0,
          //maxValue: this.maxValue,
          options: {
              stepsArray: [],
              vertical: true,
              //showTicks: true,
              showTickValues: true,
              enforceStep: false,
              ticksTooltip: function(value, label){

                  if ($scope.checkpointDetails){
                      var x;
                      for(x in $scope.checkpointDetails){
                          if (x === value.toString()){
                              console.log(label);
                              //label.tick-value = 'Checkpoint creation time: ' + convertToDate($scope.checkpointDetails[x].timestamp);
                              return 'Checkpoint creation time: ' + convertToDate($scope.checkpointDetails[x].timestamp);
                          }
                      }
                  }
              },
              onChange: function(sliderId, modelValue, highValue){
                  //This is where the logic to show the modal window
                  //and pass the value of the checkpoint details related to the
                  //checkpoint id
                  $scope.open(modelValue);
                  //console.log($scope.verticalSlider1.options.stepsArray[modelValue]);
              }
          }
      };

      function setStepValues() {
          var restHosts = Restangular.all('host');
          restHosts.getList().then(function (hosts) {
              $scope.theHosts = hosts;
              $scope.theHost = hosts[0].id;
              restHosts.all($scope.theHost).get('container').then(function(containers){
                  console.log(containers);
                  $scope.name = containers[1].names[0].replace(/\//,'');
                  restHosts.all($scope.theHost + '/container/' + containers[1].id + '/checkpoint').getList().then(function (checkpoint) {
                      var x;
                      //console.log(checkpoint.plain());
                      var checkpointsDetails = checkpoint.plain();
                      $scope.checkpointDetails = checkpointsDetails;
                      var points = [];

                      if (checkpointsDetails){
                          for (var x=0; x < checkpointsDetails.length; x++){
                              points.push(convertToDate(checkpointsDetails[x].timestamp));
                              //$scope.checkpointDetails[x].name = contValue;
                          }
                      }
                      //$rootScope.$broadcast('reCalcViewDimensions');
                      $scope.verticalSlider1.options.showTicks = 1;
                      $scope.verticalSlider1.options.showTickValues = true;
                      $scope.verticalSlider1.options.stepsArray = points;
                      //console.log(points[0]);
                      $scope.verticalSlider1.value = points[0];
                      $scope.verticalSlider1.maxValue = points.length - 1;
                  });
              });
          });


      }

      $scope.open = function (value) {

          var modalInstance = $uibModal.open({
              animation: true,
              templateUrl: '../../views/sliderModal.html',
              controller: 'ModalInstanceCtrl',
              //size: size,
              resolve: {
                  details: function () {
                      var deets = $scope.checkpointDetails[value];
                      deets.name = $scope.name;
                      return deets;
                  }
              }
          });

      };

      function convertToDate(longValue){
              //var date = new Date($scope.checkpointDetails[x].timestamp);
              var dateVal ="/Date(" + longValue + ")/";
              var date = new Date( parseFloat( dateVal.substr(6 )));
              return  (date.getMonth() + 1)
                  + "/"
                  + date.getDate()
                  + "/"
                  + date.getFullYear()
                  + " "
                  + date.getHours()
                  + ":"
                  + date.getMinutes()
                  + ":"
                  + date.getSeconds()
                  + ":"
                  + date.getMilliseconds()
      }


  });
