export class ConnexionController {

  constructor(ConnexionService,$localStorage) {
 this.$localStorage = $localStorage
 this.ConnexionService= ConnexionService
    // this.commandesService = commandesService;
    // this.$routeParams = $routeParams
    // this.findAllCommandes();
  }

connexionClient (form) {
    if (form.$invalid) return
    console.log('enter connexionClient controller', form)
    this.ConnexionService.getClient(this.client.id)
      .then(() => {
        console.log('this :', this)
        // this.$location.path('/')
      })
  }
}

ConnexionController.$inject = [ 'ConnexionService','$localStorage'];