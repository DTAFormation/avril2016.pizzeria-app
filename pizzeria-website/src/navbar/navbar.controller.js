export class NavbarController {
  constructor ($location, $localStorage) {
    this.isActive = function (viewLocation) {
      return !$location.path().indexOf(viewLocation)
    }
    if ($localStorage.client) this.clientId = $localStorage.client.id
  }
}

NavbarController.$inject = ['$location', '$localStorage']
