'use strict';

describe('Controller: FishinstanceCtrl', function () {

  // load the controller's module
  beforeEach(module('fishboneApp'));

  var FishinstanceCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FishinstanceCtrl = $controller('FishinstanceCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(FishinstanceCtrl.awesomeThings.length).toBe(3);
  });
});
