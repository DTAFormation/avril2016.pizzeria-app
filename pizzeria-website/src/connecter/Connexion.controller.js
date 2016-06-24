export class ConnexionController {

  constructor(ConnexionService, $localStorage, $location) {
    this.ConnexionService = ConnexionService
    this.$localStorage = $localStorage
    this.$location = $location
  }

  connexionClient(form) {
    if (form.$invalid) return
    this.ConnexionService.login(this.client)
      .then(client => {
        if (client) {
          this.$localStorage.client = client
          this.$location.path('/')
          console.log('connecté')
        } else {
          console.log('echec connexion')
        }
      })
  }
}

ConnexionController.$inject = ['ConnexionService', '$localStorage', '$location'];