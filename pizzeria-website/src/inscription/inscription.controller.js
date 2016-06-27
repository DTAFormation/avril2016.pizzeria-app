export class InscriptionController {

  constructor (ClientService, $location) {
    this.ClientService = ClientService
    this.$location = $location
  }


  saveClient (form) {
    if (form.$invalid) return
    this.ClientService.saveClient(this.client)
      .then(() => {
        this.$location.path('/')
      })
  }
}

InscriptionController.$inject = ['ClientService', '$location']
