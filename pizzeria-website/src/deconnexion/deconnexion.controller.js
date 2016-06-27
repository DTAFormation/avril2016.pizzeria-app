export class DeconnexionController {
  constructor ($localStorage, $location) {
    $localStorage.client = null
    $location.path('/')
  }
}

DeconnexionController.$inject = ['$localStorage', '$location']
