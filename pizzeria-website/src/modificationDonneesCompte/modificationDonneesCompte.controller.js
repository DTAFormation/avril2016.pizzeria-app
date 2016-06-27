export class ModificationDonneesCompteController {

  constructor (ClientService, $localStorage, $location) {
    this.ClientService = ClientService
    this.$location = $location
    this.$localStorage = $localStorage
    this.client = this.$localStorage.client
  }

  updateClient (form) {
    if (form.$invalid) return
    console.log('enter update client controller', form)
    this.ClientService.updateClient(this.client)
      .then(() => {
        console.log('this :', this)
        this.$location.path('/')
      })
  }
}

ModificationDonneesCompteController.$inject = ['ClientService', '$localStorage', '$location']
