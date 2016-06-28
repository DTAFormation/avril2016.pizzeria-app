export class NavbarController {
  constructor ($location) {
    this.isActive = function (viewLocation) {
      return !$location.path().indexOf(viewLocation)
    }
  }
}

NavbarController.$inject = ['$location']
