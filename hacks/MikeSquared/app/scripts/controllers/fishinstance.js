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
      this.first = [];
      $scope.donutdata = [
          ['Container _1', 420],
          ['Container_2', 310],
          ['Container_3', 210],
          ['Container_4', 301]
      ];

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
            //data: this.first
            data: (function(){
               var data = [];
                var i;
                for(i = 0; i < $scope.donutdata.length; i++){
                    data.push($scope.donutdata[i]);
                }
                return data;
            }())
        }]
    };

      //$scope.highchartsNG.series[0].addPoint(this.donutdata[0]);

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
              showTicks: true,
              showTickValues: true,
              enforceStep: false,
              ticksTooltip: function(value){

                  if ($scope.checkpointDetails){
                      var x;
                      for(x in $scope.checkpointDetails){
                          if (x === value.toString()){
                              return 'Checkpoint creation time: ' + convertToDate($scope.checkpointDetails[x].timestamp);
                          }
                      }
                  }
              },
              onStart: function(sliderId,modelValue, highValue){
                  console.log(modelValue);
                  console.log(highValue);
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
          });
          var oneCont = restHosts.one('570cf4d9be173d54726e954d','container');
          var containers = oneCont.get();//console.log(containers);
          restHosts.all('570cf4d9be173d54726e954d/container/570cf513be173d54726e9555/checkpoint').getList().then(function (checkpoint) {
              var x;
              //console.log(checkpoint.plain());
              var checkpointsDetails = checkpoint.plain();
              $scope.checkpointDetails = checkpointsDetails;
              var points = [];

              if (checkpointsDetails){
                  for (var x=0; x < checkpointsDetails.length; x++){
                     points.push(convertToDate(checkpointsDetails[x].timestamp));
                  }
              }
              //$rootScope.$broadcast('reCalcViewDimensions');
              $scope.verticalSlider1.options.showTicks = 1;
              $scope.verticalSlider1.options.showTickValues = true;
              $scope.verticalSlider1.options.stepsArray = points;
              //console.log(points[0]);
              $scope.verticalSlider1.value = points[0];
              //$scope.verticalSlider1.options.ceil = points[points.length - 1];
              $scope.verticalSlider1.maxValue = points.length - 1;
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
                      return $scope.checkpointDetails[value];
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
