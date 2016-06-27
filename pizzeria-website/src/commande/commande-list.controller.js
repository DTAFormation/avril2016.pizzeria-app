export class CommandeListController {

  constructor(commandesService, $routeParams, $location, $localStorage) {
    this.commandesService = commandesService
    this.$routeParams = $routeParams
    this.$location = $location
    if (!$localStorage.client){
      $location.path('/connexion')
    }
    this.findAllCommandes()
  }

  findAllCommandes() {
    const ctrl = this
    return this.commandesService.findAllCommandesClient(this.$routeParams.id)
      .then(data => {
        ctrl.listeCommandes = []
        data.forEach((item) => {
          ctrl.listeCommandes.push(item)
        })
        return data
      })
  }
}

CommandeListController.$inject = ['CommandesService', '$routeParams', '$location', '$localStorage'];
