export class DeconnexionController {
  constructor ($localStorage, $location, $rootScope) {
    this.$rootScope = $rootScope
    this.$rootScope.$broadcast('EVENT_DISCONNECTED')
    $localStorage.client = null
    $location.path('/')
  }
}

DeconnexionController.$inject = ['$localStorage', '$location', '$rootScope']
