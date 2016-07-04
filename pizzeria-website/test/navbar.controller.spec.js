describe('Test du NavbarController', function () {

  beforeEach(angular.mock.module('pizzeria-website'))
  var rootScope
  var ctrl
  var localStorage
  var componentController

  beforeEach(angular.mock.inject(function ($rootScope, $controller, $localStorage, $componentController) {
    const scope = $rootScope.$new()
    rootScope = $rootScope
    ctrl = $controller
    localStorage = $localStorage
    componentController = $componentController
  }))

  it('should confirm that the view is active', function () {

  })

  it('should display links associated with account management', function () {

  })

  it('should display the default navbar', function () {

  })

})
