export class CommandeListController {

  constructor(commandesService, $routeParams, $location) {
    this.commandesService = commandesService;
    this.$routeParams = $routeParams
    this.$location = $location
    this.findAllCommandes()
  }

  findAllCommandes() {
    const ctrl = this;
    console.log("ctrl : ", ctrl)
    return this.commandesService.findAllCommandesClient(this.$routeParams.id)
      .then(data => {
        ctrl.listeCommandes = [];
        data.forEach((item) => {
          ctrl.listeCommandes.push(item)
        })
      })
  }
}

CommandeListController.$inject = ['CommandesService', '$routeParams', '$location'];
