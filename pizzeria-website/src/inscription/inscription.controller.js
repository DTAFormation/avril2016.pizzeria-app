export class InscriptionController {

  constructor (ClientService, $location) {
    this.ClientService = ClientService
    this.$location = $location
  }


  saveClient (form) {
    if (form.$invalid) return
    console.log('enter save client controller', form)
    this.ClientService.saveClient(this.client)
      .then(() => {
        console.log('this :', this)
        this.$location.path('/')
      })
  }
}

InscriptionController.$inject = ['ClientService', '$location']
