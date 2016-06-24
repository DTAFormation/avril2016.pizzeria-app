import { Pizza } from '../shared/model/pizza'

export class PanierController {

  constructor (PanierService, $localStorage) {
    // Chaque ligne contient un objet avec la structure { 'pizza': (objet pizza ici), 'quantite': (entier nb de pizzas de la pizza fournie) }
    this.$localStorage = $localStorage
    this.PanierService = PanierService
    this.contenu = this.PanierService.$localStorage.panier


    // TEMPORAIRE : contenu en dur, remplacer avec données en stockage local
    this.contenu = [
      { 'pizza': new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 },
      { 'pizza': new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 3 },
      { 'pizza': new Pizza({'id': 3, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 4 },
      { 'pizza': new Pizza({'id': 4, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 }
    ]
    this.$localStorage.panier = this.contenu
    /*
    this.$localStorage.panier = {}
    for (var i = 0; i < this.contenu.length; i++) {
      this.PanierService.addPizza(this.contenu[i].pizza)
      //this.$localStorage.panier[this.contenu[i].id] = this.contenu[i]
    }
    */
  }

  isEmpty () {
    return Object.keys(this.contenu).length === 0
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
