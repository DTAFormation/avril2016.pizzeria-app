describe('Test du NavbarController', function () {
  var ctrl
  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $componentController, $location) {
    const scope = $rootScope.$new()
    // location = $location
    ctrl = $componentController('navbarComponent', {$location, $scope: scope})
  }))

  it('should confirm that the view is active', function () {
    
  })

  it('should display links associated with account management', function () {
    
  })

  it('should display the default navbar', function () {

  })
})
