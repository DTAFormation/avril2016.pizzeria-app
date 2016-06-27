export class ConnexionController {

  constructor (ClientService, $localStorage, $location) {
    this.ClientService = ClientService
    this.$localStorage = $localStorage
    this.$location = $location
  }

  connexionClient (form) {
    if (form.$invalid) return
    return this.ClientService.login(this.client)
      .then(client => {
        if (client) {
          this.$localStorage.client = client
          this.$location.path('/')
        }
      })
  }
}

ConnexionController.$inject = ['ClientService', '$localStorage', '$location']
