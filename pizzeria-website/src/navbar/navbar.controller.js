export class NavbarController {
  constructor ($location, $localStorage) {
    this.$localStorage = $localStorage
    this.isActive = function (viewLocation) {
      return !$location.path().indexOf(viewLocation)
    }
    if ($localStorage.client) {
      this.logged = true
    } else {
      this.logged = false
    }
  }
}

NavbarController.$inject = ['$location', '$localStorage']
