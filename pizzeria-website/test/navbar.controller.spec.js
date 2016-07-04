describe('Test du NavbarController', function () {

  beforeEach(angular.mock.module('pizzeria-website'))
  var rootScope
  var ctrl
  var localStorage
  /*, $navbarController*/

  beforeEach(angular.mock.inject(function ($rootScope, $controller, $location, $localStorage) {
    const scope = $rootScope.$new()
    rootScope = scope
    localStorage = $localStorage
    ctrl = $controller('NavbarController', {$location: 'http://localhost:9966/#/home', localStorage: null, $scope: scope})
    // navbarController = $navbarController
  }))

  it('should confirm that the view is active', function () {
    let navbarCtrl = ctrl
    // navbarCtrl.localStorage = null
    // navbarCtrl.location = 'http://localhost:9966/#/home'
    // navbarCtrl.rootScope = rootScope

    var viewLocation = '/home'
    var path = navbarCtrl.location.path()
    expect(path).toBeDefined()
    expect(path).toEqual(viewLocation)
  })

  it('should display links associated with account management', function () {

  })

  it('should display the default navbar', function () {

  })
})
