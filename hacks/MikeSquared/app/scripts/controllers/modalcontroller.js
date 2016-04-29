/**
 * Created by michaelsbrown on 4/26/16.
 */
var myModalApp = angular.module('fishboneApp');

myModalApp.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, details) {

    $scope.details = details;
    console.log(details);

    $scope.ok = function () {
        $uibModalInstance.close(details);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

myModalApp.filter('timestampToDate', function () {
    return function (timestamp) {
        var dateVal ="/Date(" + timestamp + ")/";
        var date = new Date( parseFloat( dateVal.substr(6 )));
        var dateObject =  (date.getMonth() + 1)
                          +'/'+ date.getDate()
                          +'/'+ date.getFullYear()
                          + " " + date.getHours()
                          + ":" + date.getMinutes()
                          + ":" + date.getSeconds()
                          + ":" + date.getMilliseconds();
        return dateObject;
    };
});
