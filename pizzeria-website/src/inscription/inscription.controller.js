export class InscriptionController {

  constructor (inscriptionService, $location) {
    this.inscriptionService = inscriptionService
    this.$location = $location
  }

  //   findAllPizzas() {
  //     const ctrl = this
  //     return this.pizzasService.findAllPizzas()
  //         .then(data => {
  //           ctrl.listePizzas = []
  //           data.forEach((item) => {
  //             ctrl.listePizzas.push(item)
  //           })
  //         })
  //   }
  saveClient (form) {
    if (form.$invalid) return
    console.log('enter save client controller', form)
    this.inscriptionService.saveClient(this.client)
      .then(() => {
        console.log('this :', this)
        this.$location.path('/')
      })
  }
}

InscriptionController.$inject = ['InscriptionService', '$location']
