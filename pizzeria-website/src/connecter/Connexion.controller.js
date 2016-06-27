export class ConnexionController {

  constructor (ClientService, $localStorage, $location) {
    this.ClientService = ClientService
    this.$localStorage = $localStorage
    this.$location = $location
  }

  connexionClient (form) {
    if (form.$invalid) return
    this.ClientService.login(this.client)
      .then(client => {
        if (client) {
          console.log('client recu par la BDD', client)
          this.$localStorage.client = client
          this.$location.path('/')
          console.log('connecté')
        } else {
          console.log('echec connexion')
        }
      })
  }
}

ConnexionController.$inject = ['ClientService', '$localStorage', '$location']
