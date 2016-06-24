export class CommandeListController {

  constructor(commandesService, $routeParams) {
    this.commandesService = commandesService;
    this.$routeParams = $routeParams
    this.findAllCommandes();
  }

  findAllCommandes() {
    const ctrl = this;
    return this.commandesService.findAllCommandesClient(this.$routeParams.id)
        .then(data => {
          ctrl.listeCommandes = [];
          data.forEach((item) => {
            ctrl.listeCommandes.push(item)
          })
        });
  }
}

CommandeListController.$inject = ['CommandesService', '$routeParams'];
