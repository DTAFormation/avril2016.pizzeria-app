export class NavbarController {
  constructor($location, $localStorage) {
    this.$localStorage = $localStorage
    this.isActive = function (viewLocation) {
      return !$location.path().indexOf(viewLocation)
    }
  }
}

NavbarController.$inject = ['$location', '$localStorage']
