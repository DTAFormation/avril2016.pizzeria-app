export class ModificationDonneesCompteController {

  constructor (ClientService, $localStorage, $location) {
    this.ClientService = ClientService
    this.$location = $location
    this.$localStorage = $localStorage
    this.client = this.$localStorage.client
  }

  updateClient (form) {
    if (form.$invalid) return
    this.ClientService.updateClient(this.client)
      .then(() => {
        this.$location.path('/')
      })
  }
}

ModificationDonneesCompteController.$inject = ['ClientService', '$localStorage', '$location']
