export class ConnexionController {

  constructor (ClientService, $localStorage, $location, $rootScope) {
    this.ClientService = ClientService
    this.$localStorage = $localStorage
    this.$location = $location
    this.$rootScope = $rootScope
  }

  connexionClient (form) {
    if (form.$invalid) return
    return this.ClientService.login(this.client)
      .then(client => {
        if (client) {
          this.$rootScope.$broadcast('EVENT_CONNECTED')
          this.$localStorage.client = client
          this.$location.path('/')
        }
      })
  }
}

ConnexionController.$inject = ['ClientService', '$localStorage', '$location', '$rootScope']
