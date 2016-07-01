export class NavbarController {
  constructor ($location, $localStorage, $rootScope) {
    this.$localStorage = $localStorage
    this.isActive = function (viewLocation) {
      // console.log('viewLocation is ', viewLocation, '\n$location is ', $location,
      //   '\n$location path is ', $location.path(), '\nComparison result is ', $location.path().indexOf(viewLocation))
      
      // Recherche l'url de viewLocation dans $location.path()
      // return !$location.path().indexOf(viewLocation)
      return $location.path().indexOf(viewLocation) !== -1
    }

    $rootScope.$on('EVENT_CONNECTED', () => {
      console.log('event connected recu !!!!!')
      this.logged = true
    })

    $rootScope.$on('EVENT_DISCONNECTED', () => {
      console.log('event disonnected recu !!!!!')
      this.logged = false
    })

    if ($localStorage.client) {
      this.logged = true
    } else {
      this.logged = false
    }
  }
}

NavbarController.$inject = ['$location', '$localStorage', '$rootScope']
