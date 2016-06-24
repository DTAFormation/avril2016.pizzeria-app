import { Pizza } from '../shared/model/pizza'

export class PanierController {

  constructor (PanierService,$localStorage) {
    // Chaque ligne contient un objet avec la structure { 'pizza': (objet pizza ici), 'quantite': (entier nb de pizzas de la pizza fournie) }
    // TEMPORAIRE : contenu en dur, remplacer avec données en stockage local
    this.$localStorage = $localStorage
    this.PanierService = PanierService
    this.contenu = this.PanierService.$localStorage.panier
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
