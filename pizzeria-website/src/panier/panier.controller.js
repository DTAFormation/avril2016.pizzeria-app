// import { Pizza } from '../shared/model/pizza'

export class PanierController {

  constructor (PanierService) {
    // Chaque ligne contient un objet avec la structure { 'pizza': (objet pizza ici), 'quantite': (entier nb de pizzas de la pizza fournie) }
    this.PanierService = PanierService
    this.totalCart = this.PanierService.$localStorage.cartValue;
    this.contenu = this.PanierService.pizzaPanier

  // DEBUG - Décommenter/recommenter cette section pour activer/désactiver, ainsi que l'import au début du fichier.
  // Contenu en dur du panier défini lors de chaque rechargement de page, et qui écrase le contenu en stockage.
  // Utile pour obtenir un panier pré-rempli pour tester l'affichage et les opérations nécessitant un panier déjà rempli.
  // -----------------------------
  // this.contenu = {
  //   '1': { 'pizza': new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 },
  //   '2': { 'pizza': new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 3 },
  //   '3': { 'pizza': new Pizza({'id': 3, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 4 },
  //   '4': { 'pizza': new Pizza({'id': 4, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 }
  // }
  // this.PanierService.$localStorage.panier = this.contenu
  // -----------------------------
  // FIN DEBUG
  }

  isEmpty () {
    return this.size() === 0
  }

  addPizza (pizza) {
    this.PanierService.addPizza(pizza)
    this.totalCart+= pizza.prix
  }

  deletePizza (pizza) {
    this.PanierService.deletePizza(pizza)
  }

  incrementPizza (pizza) {
    this.PanierService.incrementPizza(pizza)
    this.totalCart+= pizza.prix
  }

  decrementPizza (pizza) {
    this.PanierService.decrementPizza(pizza)
    this.totalCart-= pizza.prix

  }

  deleteAllPizzas () {
    this.totalCart=0
    this.PanierService.deleteAllPizzas()
    this.contenu = this.PanierService.pizzaPanier
  }

  findAllPizzas () {
    return this.PanierService.findAllPizzas()
  }

  clear () {
    this.PanierService.deleteAllPizzas()
  }

  size () {
    return this.PanierService.size()
  }

  // remarque : juste une idée pour le multilangue (ne fonctionne pas)
  getTraductionPizza (pizza, langue) {
    // TEMPORAIRE ; remplacer par les données de traductions associées à la valeur langue
    var nomsPizzas = {
      'royale': 'Royale',
      'marguerita': 'Margherita'
    }

    // retourne soit le nom trouvé dans la base, soit le nom par défaut de la pizza.
    return nomsPizzas[pizza.code] | pizza.nom
  }

}

PanierController.$inject = ['PanierService']
