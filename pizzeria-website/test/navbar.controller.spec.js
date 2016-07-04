describe('Test du NavbarController', function () {

  beforeEach(angular.mock.module('pizzeria-website'))
  var rootScope
  var ctrl
  // var location
  /*, $navbarController*/

  beforeEach(angular.mock.inject(function ($rootScope, $componentController, $location) {
    const scope = $rootScope.$new()
    rootScope = scope
    // location = $location
    ctrl = $componentController('navbarComponent', {$location, $scope: scope})
  }))

  it('should confirm that the view is active', function () {
    console.log('iciii!!!!!!!!!!!!!!!!!!!!!!!!!!')
    console.log('location : ', ctrl.$location)
    // let navbarCtrl = ctrl
    var viewLocation = '/home'
    
    console.log('boolean', ctrl.isActive(viewLocation))
    expect(ctrl.$location).toEqual('http://localhost:9876/#/home')
    expect(ctrl.isActive(viewLocation)).toEqual(true)

  })

  it('should display links associated with account management', function () {

  })

  it('should display the default navbar', function () {

  })
})
